package jvm.generation;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试常量池是在永久代还是堆空间
 */
public class Demo01 {

    public static void main(String[] args) {

        String str = "hello";
        List list = new ArrayList();
        while (true){
            str += str;
            list.add(str.intern());
        }
    }
}
