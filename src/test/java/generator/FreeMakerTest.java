package generator;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMakerTest {

	@Test
	public void test() throws Exception {
		//1.创建一个configuration对象
		 Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);		
		 //2.设置模板存在的目录
		 cfg.setDirectoryForTemplateLoading(new File("templates"));	 
		//3.创建一个数据模型 
		/* HashMap<Object,Object> map = new HashMap<>();
		 map.put("name", "johhny");
		 map.put("job", "maneger");
		 map.put("salary", 200000000);*/
		 Dog dog=new Dog();
		//4.获取到模板
		 Template template = cfg.getTemplate("dog.txt");
		//5.合并输出
		 template.process(dog, new FileWriter(new File("dog.txt")));
	}
}
