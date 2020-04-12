package designpatterns.e_decorator.coffe;

//具体组件
public class Coffee implements Drink{
    private String name = "原味咖啡";
    @Override
    public double cost() {
        return 10;
    }

    @Override
    public String info() {
        return name;
    }
}
