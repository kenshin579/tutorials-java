package com.concretepage.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "company-info", namespace = "com.concretepage")
public class Company {
	@JacksonXmlProperty(localName = "id", isAttribute = true)
	private Integer id;
	@JacksonXmlProperty(localName = "company-name")
	private String companyName;
	@JacksonXmlProperty(localName = "ceo-name")
	private String ceoName;
	@JacksonXmlProperty(localName = "no-emp")
	private Integer noEmp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public Integer getNoEmp() {
		return noEmp;
	}

	public void setNoEmp(Integer noEmp) {
		this.noEmp = noEmp;
	}
} 
