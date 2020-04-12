package duotai;
/**
 * 1.父类的静态方法/变量
 * 2.子类的静态方法/变量
 * 3.父类的成员变量，父类的构造方法
 * 4.子类的成员变量，子类的构造方法
 * @author wen
 *
 */
public class InitClass {
	public static void main(String[] args) {
		new Circle();
	}
	
	Circle[] ci = new Circle[5];
}
class Draw {
    
    public Draw(String type) {
        System.out.println(type+" draw constructor");
    }
}
class Shape {
    private Draw draw = new Draw("shape");
     
    public Shape(){
        System.out.println("shape constructor");
    }
}
class Circle extends Shape {
	
    private Draw draw = new Draw("circle");
    public Circle() {
        System.out.println("circle constructor");
    }
}
