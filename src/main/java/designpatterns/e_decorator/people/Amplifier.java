package designpatterns.e_decorator.people;

public class  Amplifier implements Say {
    private Person person;

    public Amplifier(Person person) {
        this.person = person;
    }

    @Override
    public void say() {
        System.out.println("人的声音为：" + person.getVoice() * 100);
        System.out.println("噪音。。。");
    }
}
