package com.atguigu.survey.utils;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.atguigu.survey.admin.entity.Authority;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.admin.entity.Role;
import com.atguigu.survey.guest.model.OptionStatisticsModel;
import com.atguigu.survey.guest.model.QuestionStatisticsModel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DataProcessUtils {
	
	
	public static String calculateResCode(Set<Role> roleSet, int maxResPos){
		Long[] resCodes = new Long[maxResPos+1];
		for (Role role : roleSet) {
			//获取到所有的权限
			Set<Authority> authoritySet = role.getAuthoritySet();
			if(!ValidateUtils.validateCollection(authoritySet)) continue ;
			for (Authority authority : authoritySet) {
				//获取到所有的resourceSet
				Set<Resource> resourceSet = authority.getResourceSet();
				if(!ValidateUtils.validateCollection(resourceSet)) continue;
				for (Resource resource : resourceSet) {
					//此处采用的是逆向思维
					long resCode = resource.getResCode();
					Integer resPos = resource.getResPos();
					
					//从数组中获取adminResCode和当前的resCode进行或运算，然后保存在数组中
					Long long1 = resCodes[resPos];
					if(long1 == null ) long1 = 0L;
					long1 =  long1 | resCode;
					resCodes[resPos] = long1;
				}
				
			}//authoritySet
			
		}//roleSet
		String string = DataProcessUtils.convertArrToStr(resCodes);
		return string;
	}
	

	/**
	 * 生成指定长度的随机字符串
	 * @param length
	 * @return
	 */
	public static String generateRandomString(int length) {
		StringBuilder randomString = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			int randomNumber = getRangedRandomNumber();
			char suitableChar = (char) randomNumber;
			randomString.append(suitableChar);
		}
		
		return randomString.toString();
	}
	
	/**
	 * 生成固定范围的随机数
	 * 	0~9
	 *  a~z
	 *  A~Z
	 * @return
	 */
	public static int getRangedRandomNumber() {
		
		while(true) {
			int randomNumber = (int)(Math.random()*100);
			if(randomNumber < 48 || 
					(randomNumber>57 && randomNumber<65) || 
					(randomNumber>90 && randomNumber<97) || 
					randomNumber > 122 ) {
				continue;
			}else{
				return randomNumber;
			}
		}
		
	}
	
	/**
	 * 将捕获到的ActionName翻译成中文
	 * @param actionName
	 * @return
	 */
	public static String translateActionName(String actionName) {
	
		if(!ValidateUtils.stringValidate(actionName)) return null;
		
		actionName = actionName.toLowerCase();
		
		//SurveyAction_save
		//SurveyAction_surveyDesign
		
		actionName = actionName.replaceAll("export", "导出");
		actionName = actionName.replaceAll("user", "用户");
		actionName = actionName.replaceAll("survey", "调查");
		actionName = actionName.replaceAll("bag", "包裹");
		actionName = actionName.replaceAll("question", "问题");
		actionName = actionName.replaceAll("answer", "答案");
		actionName = actionName.replaceAll("statistics", "统计");
		actionName = actionName.replaceAll("show", "显示");
		actionName = actionName.replaceAll("list", "列表");
		actionName = actionName.replaceAll("action", "");
		actionName = actionName.replaceAll("page", "页面");
		actionName = actionName.replaceAll("remove", "删除");
		actionName = actionName.replaceAll("delete", "删除");
		actionName = actionName.replaceAll("move", "移动");
		actionName = actionName.replaceAll("copy", "复制");
		actionName = actionName.replaceAll("this", "这个");
		actionName = actionName.replaceAll("entry", "入口");
		actionName = actionName.replaceAll("edit", "编辑");
		actionName = actionName.replaceAll("update", "更新");
		actionName = actionName.replaceAll("save", "保存");
		actionName = actionName.replaceAll("add", "保存");
		actionName = actionName.replaceAll("create", "创建");
		actionName = actionName.replaceAll("get", "获取");
		actionName = actionName.replaceAll("find", "查找");
		actionName = actionName.replaceAll("all", "全部");
		actionName = actionName.replaceAll("uncompleted", "未完成的");
		actionName = actionName.replaceAll("completed", "已完成的");
		actionName = actionName.replaceAll("complete", "完成");
		actionName = actionName.replaceAll("available", "可用的");
		actionName = actionName.replaceAll("top", "前");
		actionName = actionName.replaceAll("text", "文本");
		actionName = actionName.replaceAll("other", "其他");
		actionName = actionName.replaceAll("login", "登录");
		actionName = actionName.replaceAll("logout", "退出登录");
		actionName = actionName.replaceAll("register", "注册");
		actionName = actionName.replaceAll("regist", "注册");
		actionName = actionName.replaceAll("result", "结果");
		actionName = actionName.replaceAll("matrix", "矩阵");
		actionName = actionName.replaceAll("normal", "常规");
		actionName = actionName.replaceAll("cell", "单元格");
		actionName = actionName.replaceAll("select", "下拉列表");
		actionName = actionName.replaceAll("engaged", "参与");
		actionName = actionName.replaceAll("engage", "参与");
		actionName = actionName.replaceAll("mycenter", "个人中心");
		actionName = actionName.replaceAll("pay", "充值");
		actionName = actionName.replaceAll("vip", "续费");
		actionName = actionName.replaceAll("my", "我的");
		actionName = actionName.replaceAll("design", "设计");
		actionName = actionName.replaceAll("type", "类型");
		actionName = actionName.replaceAll("chosen", "选择");
		actionName = actionName.replaceAll("order", "顺序");
		actionName = actionName.replaceAll("adjust", "调整");
		actionName = actionName.replaceAll("workbook", "工作表");
		actionName = actionName.replaceAll("authorities", "权限");
		actionName = actionName.replaceAll("authority", "权限");
		actionName = actionName.replaceAll("summary", "摘要");
		actionName = actionName.replaceAll("generate", "生成");
		actionName = actionName.replaceAll("chart", "图表");
		actionName = actionName.replaceAll("image", "图片");
		actionName = actionName.replaceAll("admins", "管理员");
		actionName = actionName.replaceAll("admin", "管理员");
		actionName = actionName.replaceAll("resources", "资源");
		actionName = actionName.replaceAll("resource", "资源");
		actionName = actionName.replaceAll("res", "资源");
		actionName = actionName.replaceAll("batch", "批量");
		actionName = actionName.replaceAll("roles", "角色");
		actionName = actionName.replaceAll("role", "角色");
		actionName = actionName.replaceAll("manager", "管理");
		actionName = actionName.replaceAll("to", "前往");
		actionName = actionName.replaceAll("or", "或");
		actionName = actionName.replaceAll("do", "执行");
		actionName = actionName.replaceAll("10", "十");
		actionName = actionName.replaceAll("calculation", "计算");
		actionName = actionName.replaceAll("logs", "日志");
		actionName = actionName.replaceAll("log", "日志");
		actionName = actionName.replaceAll("main", "主");
		actionName = actionName.replaceAll("export", "导出");
		actionName = actionName.replaceAll("option", "下拉列表形式的");
		actionName = actionName.replaceAll("choosen", "选择");
		actionName = actionName.replaceAll("choose", "选择");
		actionName = actionName.replaceAll("code", "码");
		
		return actionName;
	}

	
	public static JFreeChart generateChartByOsmList(
			List<OptionStatisticsModel> osmList) {
		
		//创建用于生成图标信息的数据集对象
		DefaultPieDataset dataset = new DefaultPieDataset();
				
		for (int i = 0; i < osmList.size(); i++) {
			
			OptionStatisticsModel osm = osmList.get(i);
			//设置图表展示区问题选项的名称
			String lable = osm.getLable();
			//设置展示区问题选项被选择的次数
			int count =  osm.getCount();
			dataset.setValue(lable, count);
		}


		//创建生成图标的chart对象
		JFreeChart chart = ChartFactory.createPieChart3D(null, dataset);
		
		if(chart.getTitle() != null){
			//设置“标题”部分字体、风格、字号
			chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));
		}

		//设置“图例”部分信息字体、风格、字号
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));
		
		//获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		//设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));
		
		//设置前景色半透明
		plot.setForegroundAlpha(0.6f);
		
		//设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));
		
		/*width = 800;
		height = 600;*/
			
		return chart;
	}
	
	/**
	 * 根据QuestionStatisticsModel生成JFreeChart对象
	 */

	public static JFreeChart generateChartByQsm(QuestionStatisticsModel qsm) {
		
		//创建用于生成图标信息的数据集对象
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		//将要通过图标显示的数据保存到dataset中  
		List<OptionStatisticsModel> osmList = qsm.getOsmList();
		
		for (int i = 0; i < osmList.size(); i++) {
			
			OptionStatisticsModel osm = osmList.get(i);
			//设置图表展示区问题选项的名称
			String lable = osm.getLable();
			//设置展示区问题选项被选择的次数
			int count =  osm.getCount();
			dataset.setValue(lable, count);
		}
		
		//问题共参与的人数
		int totalCount = qsm.getTotalCount();
		
		//创建生成图标的chart对象
		JFreeChart chart = ChartFactory.createPieChart3D(qsm.getQuestionName(), dataset);
		
		if(chart.getTitle() != null){
			//设置“标题”部分字体、风格、字号
			chart.getTitle().setFont(new Font("隶书", Font.BOLD, 50));
		}

		//设置“图例”部分信息字体、风格、字号
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 20));
		
		//获取代表当前图表绘图区的PiePlot对象
		PiePlot plot = (PiePlot) chart.getPlot();

		//设置标签字体、风格、字号
		plot.setLabelFont(new Font("微软雅黑", Font.ITALIC, 15));
		
		//设置前景色半透明
		plot.setForegroundAlpha(0.6f);
		
		//设置标签信息格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));
		
		/*width = 800;
		height = 600;*/
			
		return chart;
		
	}
	
	//为了把题型的选项方便记忆，使用我们定义一个静态的Map
	public static final Map<Integer, String> QUESTION_TYPE_MAP = new HashMap<>();
	
	//在静态代码块中初始化map
	static {
		QUESTION_TYPE_MAP.put(0, "单选题");
		QUESTION_TYPE_MAP.put(1, "多选题");
		QUESTION_TYPE_MAP.put(2, "下拉列表选择题");
		QUESTION_TYPE_MAP.put(3, "简答题");
		QUESTION_TYPE_MAP.put(4, "矩阵单选题");
		QUESTION_TYPE_MAP.put(5, "矩阵多选题");
		QUESTION_TYPE_MAP.put(6, "矩阵下拉列表选择题");
	}
	

	/**
	 * 
	 */
	
	
	/**
	 * 验证字符串是否有效
	 * @param source
	 * @return
	 */
	public static boolean validateString(String source) {
		return source != null && source.length() > 0;
	}
	
	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * @param sourceFile
	 * @param realPath /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 /surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(File sourceFile, String realPath) {
		
		OutputStream out = null;
		
		try {
			//1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(sourceFile);
			
			//2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();
			
			//3.计算目标图片的宽高值
			int targetWidth = 100;
			int targetHeight = sourceHeight/(sourceWidth/100);
			
			//4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			
			//5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
			
			//6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";
			
			//7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath+"/"+targetFileName);
			
			//8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			
			//9.JPEG编码
			encoder.encode(targetImage);
			
			//10.返回文件名
			return "/surveyLogos/"+targetFileName;
			
		} catch (Exception e) {
			
			return null;
		} finally {
			//10.关闭流
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * MD5加密
	 * @param source 明文
	 * @return 密文
	 * 
	 * 使用注意：
	 * 		注册：明文→密文→保存到数据库
	 * 		登录：明文→密文→将两个密文进行比较
	 */
	public static String md5(String source) {
		
		//1.提供一个保存每个字节字符的字符串
		StringBuilder sb = new StringBuilder();
		
		try {
			//2.将原始字符串转换为字节数组
			byte[] bytes = source.getBytes();
			
			//3.创建MD5加密对象
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			//4.给加密对象传入字节数组
			byte[] targetBytes = digest.digest(bytes);
			
			//5.提供一个转换16进制数的字符数组
			char [] code = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			
			//6.将字节数组转化为字符串
			for (int i = 0; i < targetBytes.length; i++) {
				byte b = targetBytes[i];
				
				//①低四位转换
				int lowNumber = b & 0x0F;//0x0F→00001111
				
				//②高四位转换
				int highNumber = (b >> 4) & 0x0F;
				
				sb.append(code[lowNumber]).append(code[highNumber]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	//参数为页面中传递的字符串， 需要把\r\n替换成 ,
	public static String processStrForSave(String source) {
		
		//验证源字符串是否有效
		if(!ValidateUtils.stringValidate(source)) return null;
		
		//String的对象是一个不可改变的字符序列，所以源字符串是不变的，我们需要获取替换方法的返回值
		return source.replaceAll("\r\n", ",");
	}
	
	//把数据库中的source转换成显示
	public static String processStrForShow(String source) {
		
		//验证源字符串是否有效
		if(!ValidateUtils.stringValidate(source)) return null;
		
		return source.replaceAll(",", "\r\n");
	}
	
	//把数据库中的数据 转换成数组
	public static String[] convertStrToArr(String source) {
		
		if(!ValidateUtils.stringValidate(source)) return null;
		
		return source.split(",");
		
	}

	/**
	 * 在提交包裹问题时 ， 有不同的提交按钮，用此方法获取到提交按钮的名字
	 * 
	 * submit_prev: 上一个包裹
	 * submit_next: 下一个包裹
	 * submit_done:	完成包裹
	 * submit_quit: 放弃包裹
	 */
	
	public static String getSubBtnName(Map<String, String[]> parametersMap) {
		/*
		 * paramtersMap参数提交形式    :
		 *      submit_prev: 上一个包裹
		 */
		//获取key集合
		Set<String> keySet = parametersMap.keySet();
		//返回以submit_开头的key的名字
		for (String key : keySet) {
			if(key.startsWith("submit_")) return key;
		}
		
		return null;
	}

	public static String convertArrToStr(Object[] sourceArr) {
		if(!ValidateUtils.arrayValidate(sourceArr)) return null;
		
		if(sourceArr.length == 1) return sourceArr[0].toString();
		
		StringBuilder sb = new StringBuilder();
		
		for (Object source : sourceArr) {
			
			sb.append(source).append(",");
			
		}
		
		//[2,3,5]→2,3,5,
		return sb.substring(0, sb.lastIndexOf(","));
	}

	/*
	 * 根据偏移量计算出表名
	 */
	
	public static String generateTableName(int offset) {
		
		String tableName = null;
		Calendar cal = Calendar.getInstance();
		
		/*
		 * -25	:  	year  : -2    -25/12  
		 * 			month : -1	-25%12
		 * 
		 * -20  :   year  : -1    -20/12  
		 * 			month : -8    -20%12
		 */
		
		int yearOffset = offset/12;
		int monthOffset = offset%12;
		
		int year = cal.get(cal.YEAR) + yearOffset;
		//获取出来的月为：0~6，加1后复合习惯
		int month = cal.get(cal.MONTH) +1+ monthOffset;
		if(month < 1){
			month = month + 12;
			year--;
		}
		if(month > 12){
			month = month -12;
			year++;
		}
		
		return "logs_"+year+"_"+month;
	}

	public static String generateSubSelect(List<String> tableNameList) {
		StringBuilder subSelect = new StringBuilder("");
		if(!ValidateUtils.validateCollection(tableNameList))return "";
		for (String tableName : tableNameList) {
			
			subSelect.append("SELECT * FROM ").append(tableName).append(" UNION ");
			
		}
		String result = subSelect.substring(0, subSelect.lastIndexOf(" UNION ")).toString();
		return result;
	}

	

	
	


}
