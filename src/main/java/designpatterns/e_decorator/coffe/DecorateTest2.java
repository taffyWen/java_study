package designpatterns.e_decorator.coffe;

/**
 * 模拟咖啡
 * 1. 抽象组件：需要装饰的抽象对象（接口或抽象父类）
 * 2. 具体组件：需要装饰的对象
 * 3. 抽象装饰类：包含了对对象组件的引用以及装饰者共有的方法
 * 4. 具体装饰者 ：被装饰者的对象
 */
public class DecorateTest2 {

    public static void main(String[] args) {
        Drink coffee = new Coffee();
        Drink suger = new Suger(coffee);
        System.out.println(suger.info() + "------------" + suger.cost());

        Drink milk = new Milk(coffee);
        System.out.println(milk.info() + "------------" + milk.cost());

        milk = new Milk(suger);
        System.out.println(milk.info() + "------------" + milk.cost());

    }
}
