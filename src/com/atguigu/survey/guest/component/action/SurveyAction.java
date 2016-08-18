package com.atguigu.survey.guest.component.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.survey.base.i.UserAware;
import com.atguigu.survey.base.m.BaseAction;
import com.atguigu.survey.guest.component.service.i.BagService;
import com.atguigu.survey.guest.component.service.i.SurveyService;
import com.atguigu.survey.guest.entity.Bag;
import com.atguigu.survey.guest.entity.BagSetNotNullException;
import com.atguigu.survey.guest.entity.Survey;
import com.atguigu.survey.guest.entity.User;
import com.atguigu.survey.guest.model.Page;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalNames;
import com.atguigu.survey.utils.ValidateUtils;

@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware {

	// *******************************成员变量区*************************************
	
	private static final long serialVersionUID = -2018295513477578782L;

	private File logo;
	private String logoContentType;
	private String logoFileName;

	@Autowired
	private SurveyService surveyService;
	@Autowired
	private BagService bagService;
	
	private User user;
	private String currentPageNo;
	private List<Bag> bagList = new ArrayList<Bag>();

	// *******************************Action方法区*************************************
	
	public String myEngagedSurvey(){
		
		//获取到page
		Page page = surveyService.getMyEngageSurveyWithPage(user.getUserId(), currentPageNo, "4");
		
		//把pege放在request中
		reqMap.put(GlobalNames.PAGE, page);
		
		return "toMyEngagedSurvey";
	}
	


	public String findAllAvailableSurvey(){
		
		//获取一页可以参与的Survey
		Page page = surveyService.findAllAvailableSurvey(currentPageNo,5);
		
		//把page放在request域中
		reqMap.put(GlobalNames.PAGE, page);
		
		//前往页面显示
		return "toAllAvailableSurveyPage";
	}
	
	public String toIndexAction(){
		
		//1.查询出top10_completed集合
		List<Survey> top10CompletedList = surveyService.getTopCompletedSurvey();
		List<Survey> top10HotList = surveyService.getTopHotSurvey();
		//2.把最新完成10放在request中
		reqMap.put(GlobalNames.TOP10_COMPLETED, top10CompletedList);
		//3.跳转页面
		return "toIndexPage";
	}
	
	public String saveBagOrder(){
		boolean flag = ValidateUtils.validateNotRepeat(bagList);
		if(!flag){
			addActionError("排序数据有重复！");
		}else{
			bagService.saveBagOrder(bagList);
		}
		
		return "toGlobalMess";
	}
	
	public void prepareAdjustBagOrder(){
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String adjustBagOrder(){
		return "toBagOrderPage";
	}
	
	public String completed(){
		
		//1.根据要完成的id调用service完成调查方法
		boolean isCompleted = surveyService.completedSurvey(surveyId);
		
		if(!isCompleted){
			//如果未完成，提示错误消息
			addActionError("调查还有空的包裹请完善");
		}
		
		return "toCompletedPage";
	}

	public void prepareDesign(){
		//为什么一定放在这个地方？
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String design(){
		return "toDesignPage";
	}
	
	public void prepareUpdate(){
		this.t = surveyService.getEntityById(surveyId);
	}
	
	public String update(){
		
		this.input = "/guestPages/survey_edit.jsp";
		
		String virtualPath = "/surveyLogos";

		String realPath = this.servletContext.getRealPath(virtualPath);

		String logoPath = DataProcessUtils.resizeImages(logo, realPath);

		// 仅在logoPath有效的前提下才进行设置，否则保持默认值
		if (DataProcessUtils.validateString(logoPath)) {
			this.t.setLogoPath(logoPath);
		}
		
		surveyService.updateEntity(t);
		
		return "toMyUncompletedAction";
	}

	public void prepareEditSurvey(){
		this.t = surveyService.getEntityById(surveyId);
	}

	public String editSurvey() {
		
		return "toEditSurvey";
	}

	public String myCompletedSurvey() {
		String pageSize = "4";

		Page<Survey> completedPage = surveyService.getCompletedPage(
				currentPageNo, pageSize, user);
		reqMap.put(GlobalNames.PAGE, completedPage);
		return "toCompletedListPage";
	}

	/*
	 * 当删除survey时，由于其级联的包裹和问题导致删除不了
	 *  异常： Cannot delete or update a parent row: 
	 *  		a foreign key constraint fails 
	 *  所以在删除时我们要判断其下是否有包裹和问题 
	 */
	
	public String remove() {
		try {
			surveyService.deleteSurvey(t);
		} catch (Exception e) {
			throw new BagSetNotNullException();
		}
		return "toGlobalMess";
	}

	public String myUncompletedSurvey() {
		String pageSize = "4";

		Page<Survey> uncompletedPage = surveyService.getUncompletedPage(
				currentPageNo, pageSize, user);

		reqMap.put(GlobalNames.PAGE, uncompletedPage);
		return "toUncompletedListPage";
	}

	public String save() {
		
		this.input = "/guestPages/survey_create.jsp";
		// 遗留问题1：没有关联到User
		// 关联的方式：survey.setUser(user);
		// User的来源：Session域中——在实现UserAware接口后可以由LoginInterceptor拦截器主动注入
		// User user = (User) session.getAttribute(GlobalNames.LOGIN_USER);
		this.t.setUser(user);

		// 1.收集创建调查时需要提供的数据
		// ①调查的标题：已经被Struts2的模型驱动机制注入到栈顶的t对象值中

		// ②将来页面上显示图片时需要用到的图片路径：需要将原始的图片压缩后获取其可保存的路径值
		String virtualPath = "/surveyLogos";

		String realPath = this.servletContext.getRealPath(virtualPath);

		String logoPath = DataProcessUtils.resizeImages(logo, realPath);

		// 仅在logoPath有效的前提下才进行设置，否则保持默认值
		if (DataProcessUtils.validateString(logoPath)) {
			this.t.setLogoPath(logoPath);
		}

		// 2.将收集了必要数据的Survey对象保存到数据库中
		this.surveyService.saveEntity(t);

		return "toMyUncompletedAction";
	}

	// *******************************getXxx()、setXxx()方法区*************************************
	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	public List<Bag> getBagList() {
		return bagList;
	}

	public void setBagList(List<Bag> bagList) {
		this.bagList = bagList;
	}

}
