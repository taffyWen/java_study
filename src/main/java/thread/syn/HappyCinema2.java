package thread.syn;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema2 {
	public static void main(String[] args) {
		List<Integer> available = new ArrayList<Integer>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(5);
		List<Integer> seats = new ArrayList<Integer>();
		seats.add(1);
		seats.add(2);
		List<Integer> seats2 = new ArrayList<Integer>();
		seats2.add(3);
		seats2.add(5);
		
		MableCinema cinema = new MableCinema(available, "麦宝影院");
		new Thread(new HappyCustomer(cinema,seats),"wen").start();
		new Thread(new HappyCustomer(cinema,seats2),"mable").start();
	}

}

//顾客
class HappyCustomer implements Runnable{

	MableCinema cinema ; //= new Cinema(10, "麦宝影院");
	List<Integer> seats;
	
	public HappyCustomer(MableCinema cinema, List<Integer> seats) {
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
class MableCinema{
	List<Integer> available;//可用的位置
	String name;//名字
	public MableCinema(List<Integer> available, String name) {
		super();
		this.available = available;
		this.name = name;
	}
	
	
	//购票
	public boolean bookTickets(List<Integer> seats) {
		System.out.println("可用的位置为：" + available);
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(available);
		//相减
		copy.removeAll(seats);
		if(available.size() - seats.size() != copy.size()) {
			return false;
		}
		//成功
		available = copy;
		return true;
	}
}

