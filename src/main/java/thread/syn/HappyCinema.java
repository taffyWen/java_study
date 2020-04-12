package thread.syn;

/**
 * synchronized 
 * @author wen
 *
 */
public class HappyCinema {
	public static void main(String[] args) {
		Cinema cinema = new Cinema(2, "麦宝影院");
		new Thread(new Customer(cinema,1),"wen").start();
		new Thread(new Customer(cinema,2),"mable").start();
	}

}

//顾客
class Customer implements Runnable{

	Cinema cinema ; //= new Cinema(10, "麦宝影院");
	int seats;
	
	public Customer(Cinema cinema, int seats) {
		super();
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized(cinema) {
			
			boolean flag = cinema.bookTickets(seats);
			if(flag) {
				System.out.println("出票成功" + Thread.currentThread().getName() + "位置-->" + seats);
			}else {
				System.out.println("出票失败");
			}
		}
	}
	
	
}

//影院
class Cinema{
	int available;//可用的位置
	String name;//名字
	public Cinema(int available, String name) {
		super();
		this.available = available;
		this.name = name;
	}
	
	
	//购票
	public boolean bookTickets(int seats) {
		System.out.println("可用的位置为：" + available);
		
		if(seats > available) {
			return false;
		}
		available -= seats;
		return true;
	}
}

