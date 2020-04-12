package designpatterns.e_decorator.people;

public class DecorateTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.say();

        Amplifier amplifier = new Amplifier(person);
        amplifier.say();
    }
}
