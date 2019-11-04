package com.advenoh;

/**
 * https://coderunch.wordpress.com/2018/03/12/how-to-write-unit-test-cases-for-an-abstract-class/
 */
public abstract class MyAbstractClass {

	private String defaultId;
	private boolean doFallBack;

	//Non-default constructor to initialize member variables
	public MyAbstractClass(String defaultId, boolean doFallBack) {
		this.defaultId = defaultId;
		this.doFallBack = doFallBack;
	}

	//Setter
	public void setDefaultId(String defaultId) {
		this.defaultId = defaultId;
	}

	//Setter
	public void setDoFallBack(boolean doFallBack) {
		this.doFallBack = doFallBack;
	}

	public String doWork(String overriddenId) {
		String returnValue;
		if (overriddenId != null && !overriddenId.equals(defaultId)) {
			returnValue = doWorkInForcefulWay(overriddenId);
			if (returnValue == null && doFallBack) {
				returnValue = doWorkInDefaultWay(defaultId);
			}
		} else {
			returnValue = doWorkInDefaultWay(defaultId);
		}
		return returnValue;
	}

	//Abstract method
	protected abstract String doWorkInForcefulWay(String id);

	//Abstract method
	protected abstract String doWorkInDefaultWay(String id);
}