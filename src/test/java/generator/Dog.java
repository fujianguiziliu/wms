package generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dog {
 
	private List<String> list = new ArrayList<>();
	private HashMap<String,String> map=new HashMap<>();
	
	public Dog (){
		list.add("1");
		list.add("2");
		list.add("3");
		map.put("001", "jack");
		map.put("002", "ally");
		map.put("003", "johhny");
		
	}
}
