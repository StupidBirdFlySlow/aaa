package design;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.hunau.base.model.Materials;
import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.Steps;
import edu.hunau.base.service.MenuDetailsService;

public class CaipuTest extends BaseTest{

	
	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService service;
	
	@Test
	public void addCaipuTest(){
		MenuDetails caipu=new MenuDetails();
		caipu.setCpname("²ËÆ×Ìí¼Ó²âÊÔ");
		
		Materials m1=new Materials();
		m1.setClmc("²ËÆ×Ìí¼Ó²âÊÔ²ÄÁÏÒ»");
		m1.setMenuDetails(caipu);
		Materials m2=new Materials();
		m2.setClmc("²ËÆ×Ìí¼Ó²âÊÔ²ÄÁÏ¶ş");
		m2.setMenuDetails(caipu);
		
		Steps s1=new Steps();
		s1.setStep_desc("²ËÆ×Ìí¼Ó²âÊÔ²½ÖèÃèÊöÒ»");
		s1.setMenuDetails(caipu);
		
		Steps s2=new Steps();
		s2.setStep_desc("²ËÆ×Ìí¼Ó²âÊÔ²½ÖèÃèÊö¶ş");
		s2.setMenuDetails(caipu);
		
		List<Materials> ms=new ArrayList<Materials>();
		ms.add(m1);
		ms.add(m2);
		
		List<Steps> ss=new ArrayList<Steps>();
		ss.add(s1);
		ss.add(s2);
		
		caipu.setMaterials(ms);
		caipu.setSteps(ss);
		
		service.addMenuDetails(caipu);
		
	}
	
	@Test
	public void getMostHotRecipesOfWeek(){
		List<MenuDetails> list = service.getMostHotRecipesOfThisWeek(new Date(), 4, 1);
		for(MenuDetails entity :list){
			System.out.println(entity.getCpname());
		}
	}
}
