package designpatterns.e_decorator.coffe;

//具体装饰者
public class Milk extends Decorate {
    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return super.cost() * 4;
    }

    @Override
    public String info() {
        return super.info() + "加入了牛奶";
    }
}
