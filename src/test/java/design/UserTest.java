package design;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.hunau.base.service.UserService;

public class UserTest extends BaseTest{

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Test
	public void checkEmail(){
		boolean checkEmail = userService.checkEmail("1902387280@qq.com");
		System.out.println(checkEmail);
	}
}
