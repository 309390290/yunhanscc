package com.yunhan.scc.backto.web.entities.sendgoods;
/**
 * 
 * @author YangTao 回告发货次数 
 * 当作枚举使用，所以名称都大写了
 */
public class SENDOUTNUMBERS {
	private boolean FRIST; //第一次回告
	private boolean LAST;	//最后一次
	private boolean FRISTLAST;//即是第一次又是最后一次
	
	public boolean isFRIST() {
		return FRIST;
	}
	public void setFRIST(boolean fRIST) {
		FRIST = fRIST;
	}
	public boolean isLAST() {
		return LAST;
	}
	public void setLAST(boolean lAST) {
		LAST = lAST;
	}
	public boolean isFRISTLAST() {
		return FRISTLAST;
	}
	public void setFRISTLAST(boolean fRISTLAST) {
		FRISTLAST = fRISTLAST;
	}
	
}
