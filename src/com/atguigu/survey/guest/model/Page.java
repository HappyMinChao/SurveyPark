package com.atguigu.survey.guest.model;

import java.util.List;

/*
 * page 类不需要保存， 所以不需要OID
 */
public class Page<T> {
	//1.当前页，当前页可以判断是否是首页，尾页
	private Integer currentPageNo = 1;
	//5.每页大小 , 默认值为4
	private Integer pageSize=4;
	
	//4.总记录数  通过查询的到
	private Integer totalRecord;
	//6.总页数  通过计算的到
	private Integer totalPage;
	//7.页面中的记录集合，通过查询的到
	private List<T> list;
	
	//通过  当前页， 页面大小  ， 总记录数 构建page实例
	public Page(String currentPageNoStr,String pageSizeStr,Integer totalRecord) {
		this.totalRecord = totalRecord;
		//先设置pageSize, 在设置currentPageNo,因为setCurrentPageNo时用到了pageSize
		setPageSize(pageSizeStr);
		setCurrentPageNo(currentPageNoStr);
		this.totalPage = getTotalPage();
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}

	//获取当前页
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	//设置当前页
	public void setCurrentPageNo(String currentPageNoStr) {
		try {
			int currentPageNo = Integer.parseInt(currentPageNoStr);
			
			//最小值为1
			if(currentPageNo<1){
				this.currentPageNo = 1;
			}//最大值为totalPage
			else  if(currentPageNo > getTotalPage()){
				this.currentPageNo = getTotalPage();
			}else{
				this.currentPageNo = currentPageNo;
			}
		} catch (NumberFormatException e) { 
		}
	}

	//获取当前页大小
	public Integer getPageSize() {
		return pageSize;
	}
	//设置当前页大小
	public void setPageSize(String pageSizeStr) {
		try {
			int pageSize = Integer.parseInt(pageSizeStr);
			
			if(pageSize > 0){
				this.pageSize = pageSize;
			}
		} catch (NumberFormatException e) {
			// 转换不成功，打印堆栈信息
			e.printStackTrace();
		}
	}
	
	//================计算得到的属性====================
	//判断是否有下一页
	public boolean isHasDownPage() {
		return currentPageNo < getTotalPage();
	}
	
	//通过计算的到downPageNo，所以没有必要进行设置，在获取时得到即可
	public Integer getDownPageNo() {
		if(currentPageNo+1 <= getTotalPage()){
			return currentPageNo+1;
		}
		return getTotalPage();
	}
	
	//判断是否有上一页
	public boolean isHasUpPage() {
		return currentPageNo > 1;
	}
	
	////通过计算的到upPageNo，没有必要提供set方法
	public Integer getUpPageNo() {
		if(currentPageNo-1 <= 0){
			return 1;
		}
		return currentPageNo-1;
	}


	

	/*
	 * 	totalRecord / pageSize = totalPage
	 */
	
	public Integer getTotalPage() {
		
		//能够整除
		if(getTotalRecord() % pageSize == 0){
			return getTotalRecord() / pageSize;
		}
		//不能整除
		return (getTotalRecord() / pageSize)+1;
		
	}

	//================查询得到的属性====================
	public Integer getTotalRecord() {
		//通过查询的到，没必要提供set方法
		return totalRecord;
	}
	
	public List<T> getList() {
		//通过查询得到
		return list;
	}

}
