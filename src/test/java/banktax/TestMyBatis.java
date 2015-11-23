package banktax;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.services.iface.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(TestMyBatis.class);
	//private ApplicationContext ac = null;
	@Resource
	private IUserService userService=null ;
	
//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void test1() {
//		User user = userService.getUserById(1);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
//		logger.info(JSON.toJSONString(user));
	}
	/**
	 * 
	  * @Description: 测试查询
	  * @param   
	  * @return void  
	  * @throws
	 */
	@Test
	public void testSelect(){
		Cuser user=userService.selectByPrimaryKey("zheng");
		System.out.println(user.getLastLogTime());
		System.out.println(user.getId());
	}
	/**
	  * @Description:测试删除
	  * @param   
	  * @return void  
	  * @throws
	 */
	@Test
	public void testDelete(){
		userService.deleteById(6);
	}
	/**
	 * 
	  * @Description: 测试修改
	  * @param   
	  * @return void  
	  * @throws
	 */
	@Test
	public void testUpdate(){
		Cuser user=new Cuser();
		user.setUserName("zqiang");
		user.setMail("64561316@qq.com");
		user.setTaxSn("16213");
		user.setUserPass("123");
		user.setId((long) 30);
	//	userService.updateByIdSelective(user);
	}
	/**
	 * 测试新增记录
	  * @Description: TODO
	  * @param   
	  * @return void  
	  * @throws
	 */
	@Test
	public void testInsert(){
		Cuser user=new Cuser();
		user.setUserName("zsffsdfssdfdfsaa");
		user.setMail("6456131216@qq.com");
		user.setTaxSn("16213");
		user.setUserPass("123");
//		user.setLast_log_time("2015/8/03");
		userService.insertSelective(user);
	}
	
	
}
