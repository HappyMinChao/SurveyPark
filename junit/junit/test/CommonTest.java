package junit.test;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.survey.admin.component.service.i.AdminService;
import com.atguigu.survey.admin.component.service.i.AuthorityService;
import com.atguigu.survey.admin.component.service.i.LogService;
import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.component.service.i.RoleService;
import com.atguigu.survey.admin.entity.Admin;
import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.StatisticsService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.component.service.i.UserService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.ValidateUtils;

public class CommonTest {

	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserService userService = ioc.getBean(UserService.class);
	private SurveyService surveyService = ioc.getBean(SurveyService.class);
	private BagService bagService = ioc.getBean(BagService.class);
	private StatisticsService statisticsService = ioc.getBean(StatisticsService.class);
	private ResourceService resourceService = ioc.getBean(ResourceService.class);
	private AuthorityService authorityService = ioc.getBean(AuthorityService.class);
	private RoleService roleService = ioc.getBean(RoleService.class);
	private AdminService adminService = ioc.getBean(AdminService.class);
	private LogService logService = ioc.getBean(LogService.class);
	
	@Test
	public void test23(){
		//使用表明创建表示成功的， 还有AOP监听容器也是可以的
		//logService.createTableByName("2015_01");
		List<String> allLogsTable = logService.getAllLogsTable();
		for (String string : allLogsTable) {
			System.out.println(string);
			
		}
	}
	
	@Test
	public void test22(){
		Page page = adminService.getAdminListPage("1", "4");
		List<Admin> list = page.getList();
		for (Admin admin : list) {
			System.out.println(admin);
		}
	}
	
	@Test
	public void test21(){
		Page page = roleService.getRoleListPage("1", "4");
		List<Role> list = page.getList();
		for (Role role : list) {
			System.out.println(role);
		}
	}
	
	@Test
	public void test20(){
		Page page = authorityService.getAuthorityPage("-1", "2");
		List<Authority> list = page.getList();
		for (Authority authority : list) {
			System.out.println(authority);
		}
	}
	
	@Test
	public void test19(){
		Integer maxResPos = resourceService.getMaxResPos();
		System.out.println(maxResPos);
		Long maxResCode = resourceService.getMaxResCode(1);
		System.out.println(maxResCode << 1);
	}
	
	@Test
	public void test18(){
		surveyService.generateWorkBook(19);
	}
	
	@Test
	public void test17(){
		
		List<OptionStatisticsModel> qsmList = statisticsService.getOptionMatrixOsmList(24, 0,2);

		for(int i = 0; i<qsmList.size(); i++){
			System.out.println(qsmList.get(i));
		}
	}
	
	@Test
	public void test16(){
		
		List<OptionStatisticsModel> qsmList = statisticsService.getMatrixOsmList(13, 0);
		
		for(int i = 0; i<qsmList.size(); i++){
			System.out.println(qsmList.get(i));
		}
		
	}
	
	@Test
	public void test15(){
		QuestionStatisticsModel qsm = statisticsService.getQsm(2);
		System.out.println(qsm);
	}
	
	@Test
	public void test14(){
		
		Page page = surveyService.getMyEngageSurveyWithPage(1, "1", "3");
		List list = page.getList();
		for(int i = 0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	
	@Test
	public void test13(){
		Page page = surveyService.findAllAvailableSurvey("3", 10);
		List list = page.getList();
		for(int i = 0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
		
	}
	
	@Test
	public void test12(){
		Survey s = surveyService.getEntityById(17);
		System.out.println(s.getMinBagOrder() +" : " +s.getMaxBagOrder());
	}
	
	@Test
	public void test11(){
		Bag firstBag = bagService.getFirstBag(17);
		System.out.println(firstBag.getBagId()+" : "+firstBag.getBagName());
		
		Bag preBag = bagService.getPrevBag(17,9);
		System.out.println(preBag.getBagId()+" : "+preBag.getBagName());
		
		Bag nextBag = bagService.getNextBag(17,9);
		System.out.println(nextBag.getBagId()+" : "+nextBag.getBagName());
	}
	
	@Test
	public void test10(){
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(2);
		
		System.out.println(ValidateUtils.validateNotRepeat(list));
		
		list.remove(2);
		
		System.out.println(ValidateUtils.validateNotRepeat(list));
		
	}
	
	@Test
	public void test09() {
		User user = new User();
		user.setUserId(1);
		Page<Survey> page = surveyService.getUncompletedPage("null", "4", user);
		List<Survey> list = page.getList();
		for (Survey survey : list) {
			System.out.println(survey);
		}
	}
	
	@Test
	public void test08() {
		//测试压缩图片
		File file = new File("Koala.jpg");
		String realPath = "C:\\image";
		String path = DataProcessUtils.resizeImages(file, realPath);
		System.out.println(path);
	}
	
	@Test
	public void test07() {
		Date date = new Date();
		System.out.println(date.getTime());
	}
	
	@Test
	public void test06() throws ParseException {
		//测试剩余天数
		User user = new User();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2016-02-03 08:20:30");
		
		user.setPayStatus(true);
		user.setEndTime(date.getTime());
		
		int leftDays = user.getLeftDays();
		System.out.println(leftDays);
		
	}
	
	@Test
	public void test05() {
		User user = new User();
		user.setUserName("Tom2016");
		user.setUserPwd("123ttt456");
		user = userService.login(user);
		System.out.println(user);
	}
	
	@Test
	public void test04() throws NoSuchAlgorithmException {
		//测试MD5加密
		//1.原始字符串
		String src = "goodMorningOOOPPPP";
		
		//2.将原始字符串转换为字节数组
		byte[] bytes = src.getBytes();
		System.out.println("原始字节数组长度："+bytes.length);
		
		//3.创建MD5加密对象
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		//4.给加密对象传入字节数组
		byte[] targetBytes = digest.digest(bytes);
		System.out.println("加密后的字节数组长度："+targetBytes.length);
		
		//提供一个转换16进制数的字符数组
		char [] code = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		//提供一个保存每个字节字符的字符串
		StringBuilder sb = new StringBuilder();
		
		//5.将字节数组转化为字符串
		for (int i = 0; i < targetBytes.length; i++) {
			byte b = targetBytes[i];
			
			//①低四位转换
			int lowNumber = b & 0x0F;//0x0F→00001111
			
			//②高四位转换
			int highNumber = (b >> 4) & 0x0F;
			
			sb.append(code[lowNumber]).append(code[highNumber]);
		}
		
		System.out.println(sb);
	}
	
	@Test
	public void test03() {
		User user = new User();
		user.setUserName("Tom2016");
		user.setUserPwd("123456");
		boolean exists = userService.regist(user);
		System.out.println(exists?"用户名已存在":"用户名不存在");
	}
	
	@Test
	public void test02() {
		//测试SessionFactory
	}
	
	@Test
	public void test01() throws SQLException {
		//测试数据源
		DataSource dataSource = ioc.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		
		System.out.println(connection);
		
	}

}
