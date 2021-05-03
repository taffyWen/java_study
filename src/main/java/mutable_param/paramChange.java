package mutable_param;

/**
 * @Author wen
 * @create 2020/3/22 9:29
 * java 中  基本数据类型 + String ====>不可变 ，作为参数传入其他方法，原值不会被更改
 *  其他的都是可变的。
 */
public class paramChange {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 7, 9};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        change(arr);
        System.out.println("\n------------更改后--------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("=================");
        StringBuffer stringBuffer = new StringBuffer("hello ");
        System.out.println("str1之前--->" + stringBuffer.hashCode());
        testPassPara(stringBuffer);
        System.out.println("stringBuffer--->" + stringBuffer);


        String str2 = new String("haha");
        System.out.println("str2---》" + str2.hashCode());
        String str3 = "haha";
        System.out.println("str3之前---》" + str3.hashCode());
        testString(str3);
        System.out.println("str3之后---》" + str3.hashCode());

    }

    public static void change(int[] arr){
        arr[0] = 100;
    }

    public static void testPassPara(StringBuffer stringBuffer){

        /**
         * 因为是赋值操作，stringBuffer 的指向更改了
         */
        System.out.println("之前---》" + stringBuffer.hashCode());
        stringBuffer = new StringBuffer(" world");
        System.out.println("之后--》" + stringBuffer.hashCode());

    }

    public static void testString(String str){
        System.out.println("str之前---》" + str.hashCode());
        str = "haha";
        System.out.println("str之后---》" + str.hashCode());

    }
}
