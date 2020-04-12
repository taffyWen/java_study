package getConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 读取jdbc配置文件
 * @author wen
 *
 */
public class ConfigManager {

	private static ConfigManager configManager;
	private static Properties pro;//此处并没有实例化，只是声明
	
	//在构造工具类时，进行配置文件的读取。即：构造方法中，读取配置文件
	private ConfigManager(){
		pro=new Properties();//实例化对象
		InputStream is=ConfigManager.class.getClassLoader().getResourceAsStream("conn.properties");
		try {
			//读取配置文件（配置文件就是键值对，键=值）
			//System.out.println("123");测试是否出错，其实抛出的异常已经说明此处了
			pro.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//通过单例模式设置实例化的个数
	public static ConfigManager getInstance(){
		if(configManager==null){
			configManager=new ConfigManager();
		}
		
		return configManager;
	}
	
	//通过key获取对应的value
	public String getString(String key){
		return pro.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(ConfigManager.getInstance().getString("username"));
	}
}
