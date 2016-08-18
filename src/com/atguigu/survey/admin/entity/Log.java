package com.atguigu.survey.admin.entity;

public class Log {

	private Integer logId;
	// 操作者
	private String operate;
	// 方法返回值
	private String methodReturn;
	// 方法的类型
	private String methodType;
	// 方法的名字
	private String methodName;
	// 方法的参数
	private String methodArgs;
	// 方法执行结果: 成功/失败,如果失败那么保存异常信息
	private String methodResultMesg;

	private String operateTime;

	public Log() {

	}

	public Log(Integer logId, String operate, String methodReturn,
			String methodType, String methodName, String methodArgs,
			String methodResultMesg, String operateTime) {
		super();
		this.logId = logId;
		this.operate = operate;
		this.methodReturn = methodReturn;
		this.methodType = methodType;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.methodResultMesg = methodResultMesg;
		this.operateTime = operateTime;
	}



	public Log(String operate, String methodReturn, String methodType,
			String methodName, String methodArgs, String methodResultMesg,
			String operateTime) {
		super();
		this.operate = operate;
		this.methodReturn = methodReturn;
		this.methodType = methodType;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.methodResultMesg = methodResultMesg;
		this.operateTime = operateTime;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getMethodReturn() {
		return methodReturn;
	}

	public void setMethodReturn(String methodReturn) {
		this.methodReturn = methodReturn;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(String methodArgs) {
		this.methodArgs = methodArgs;
	}

	public String getMethodResultMesg() {
		return methodResultMesg;
	}

	public void setMethodResultMesg(String methodResultMesg) {
		this.methodResultMesg = methodResultMesg;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", operate=" + operate
				+ ", methodReturn=" + methodReturn + ", methodType="
				+ methodType + ", methodName=" + methodName + ", methodArgs="
				+ methodArgs + ", methodResultMesg=" + methodResultMesg
				+ ", operateTime=" + operateTime + "]";
	}

}
