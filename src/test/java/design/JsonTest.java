package design;

import org.junit.Test;

import edu.hunau.base.model.User;
import edu.hunau.base.utils.JsonUtils;

public class JsonTest {

	@Test
	public void testJson(){
		User user=new User();
		user.setUsername("haoren");
		user.setPwd("250");
		try {
			String obj2json = JsonUtils.obj2json(user);
			System.out.println(obj2json);
			User user2 = JsonUtils.json2pojo(obj2json, User.class);
			System.out.println(user2.getUsername()+":"+user2.getPwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
