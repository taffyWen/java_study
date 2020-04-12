package jvm.classLoader;

import java.io.*;

/**
 * 自定义类加载器
 */
public class MyTest06 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtension = ".class";

    //将系统类加载器作为当前类加载器的父类加载器
    public MyTest06(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    //显式指定该类加载器的父加载器
    public MyTest06(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    public String toString() {
        return  "[" + this.classLoaderName + "]";
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] data = this.loadClassData(className);
        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream inputStream = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        this.classLoaderName = this.classLoaderName.replaceAll(".", "/");
        try {
            inputStream = new FileInputStream(new File(name + this.fileExtension));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = inputStream.read())){
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
