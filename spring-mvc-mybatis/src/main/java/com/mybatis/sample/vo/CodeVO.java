package com.mybatis.sample.vo;

public class CodeVO {

	private String cdDo;
	private String cdUp;
	private String cdDn;
	private String cdKor;
	private String cdEng;
	private String cdDesc;
	private String useYn;
	private String sortSeq;
	private String modId;
	private String modDt;
	private String creId;
	private String creDt;

	public String getCdDo() {
		return cdDo;
	}

	public void setCdDo(String cdDo) {
		this.cdDo = cdDo;
	}

	public String getCdUp() {
		return cdUp;
	}

	public void setCdUp(String cdUp) {
		this.cdUp = cdUp;
	}

	public String getCdDn() {
		return cdDn;
	}

	public void setCdDn(String cdDn) {
		this.cdDn = cdDn;
	}

	public String getCdKor() {
		return cdKor;
	}

	public void setCdKor(String cdKor) {
		this.cdKor = cdKor;
	}

	public String getCdEng() {
		return cdEng;
	}

	public void setCdEng(String cdEng) {
		this.cdEng = cdEng;
	}

	public String getCdDesc() {
		return cdDesc;
	}

	public void setCdDesc(String cdDesc) {
		this.cdDesc = cdDesc;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getSortSeq() {
		return sortSeq;
	}

	public void setSortSeq(String sortSeq) {
		this.sortSeq = sortSeq;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getCreId() {
		return creId;
	}

	public void setCreId(String creId) {
		this.creId = creId;
	}

	public String getCreDt() {
		return creDt;
	}

	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}

	@Override
	public String toString() {
		return "CodeVO [cdDo=" + cdDo + ", cdUp=" + cdUp + ", cdDn=" + cdDn + ", cdKor=" + cdKor + ", cdEng=" + cdEng
				+ ", cdDesc=" + cdDesc + ", useYn=" + useYn + ", sortSeq=" + sortSeq + ", modId=" + modId + ", modDt="
				+ modDt + ", creId=" + creId + ", creDt=" + creDt + "]";
	}

}
