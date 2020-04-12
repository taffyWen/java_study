package thread.syn;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 用一个 List来保存对象实例，用 Semaphore 实现限流器。关键的代码是 ObjPool 里面的 exec()
 * 方法，这个方法里面实现了限流的功能。在这个方法里面，我们首先调用 acquire() 方法 （与之匹配的是在 finally 里面调用 release()
 * 方法），假设对象池的大小是 10，信号量的计数 器初始化为 10，那么前 10 个线程调用 acquire() 方法，都能继续执行，相当于通过了信号灯，
 * 而其他线程则会阻塞在 acquire() 方法上。对于通过信号灯的线程，我们为每个线程分配了一个 对象 t（这个分配工作是通过
 * pool.remove(0) 实现的），分配完之后会执行一个回调函数 func，而函数的参数正是前面分配的对象 t
 * ；执行完回调函数之后，它们就会释放对象（这个释 放工作是通过 pool.add(t) 实现的），同时调用 release()
 * 方法来更新信号量的计数器。如果此 时信号量里计数器的值小于等于 0，那么说明有线程在等待，此时会自动唤醒等待的线程。
 * 
 * @author wen
 * 信号量
 */
public class SemaphoreDemo {

	public static void main(String[] args) {

		// 创建对象池
		ObjPool<Long, String> pool = new ObjPool<Long, String>(10, (long) 2);
		// 通过对象池获取 t，之后执行
		pool.exec(t -> {
			System.out.println(t);
			return t.toString();
		});
	}

}

class ObjPool<T, R> {
	final List<T> pool;
	// 用信号量实现限流器
	final Semaphore sem;

	// 构造函数
	ObjPool(int size, T t) {
		pool = new Vector<T>();
		for (int i = 0; i < size; i++) {
			pool.add(t);
		}
		sem = new Semaphore(size);
	}

	// 利用对象池的对象，调用 func
	R exec(Function<T, R> func) {
		T t = null;
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			t = pool.remove(0);
			return func.apply(t);
		} finally {
			pool.add(t);
			sem.release();
		}
	}

}
