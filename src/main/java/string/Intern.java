package string;

public class Intern {


    public static void main(String[] args) {

        String s1 = new String("a"); // 创建2个对象，一个常量池中，一个堆中
        s1.intern();

        String s2 = "a";
        System.out.println(s1==s2);


        // 在堆中生成字符串"aa" , 常量池中还不存在 "aa"
        String s3 = new String("a") + new String("a");
        s3.intern();  // 1.7以后，常量池不存在，就去堆上面找，存在，就存储相关引用并返回。
                                                    // 不存在，就复制当前字符串到常量池中，并返回字符串常量池中引用

        String s4 = "aa";

        System.out.println(s3 == s4);
    }
}
