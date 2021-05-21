package demoTest;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Demo01 {


    public static void change(Integer integer){
        integer = null;
    }

    public static void main(String[] args) {
        Integer integer = new Integer(0);


        Map map = new HashMap();
        map.put(integer,"我是0");
        System.out.println(map);
        //change(integer);
        integer = null;
        System.out.println(integer);
        System.out.println(map);
    }
}
