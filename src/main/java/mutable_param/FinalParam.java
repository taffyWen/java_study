package mutable_param;


/**
 * 测试被final修饰的变量
 */
public class FinalParam {

    public static void main(String[] args) {
        final StringBuffer stringBuffer = new StringBuffer("hello");
        System.out.println("stringBuffer之前--》" + stringBuffer.hashCode());
        stringBuffer.append(" world");
        System.out.println("stringBuffer之后--》" + stringBuffer.hashCode());
        System.out.println(stringBuffer);


        /**
         * 重新赋值，编译报错，被final修饰，指引用不可变性
         */
        //stringBuffer = new StringBuffer("ahhah");


        final StringBuffer stringBuffer2 ;
        stringBuffer2 = new StringBuffer("------");
        System.out.println(stringBuffer2);
    }
}
