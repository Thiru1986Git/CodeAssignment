package com.cts.application.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

	@XmlAttribute(name = "reference")
	private Integer reference;
	@XmlElement(name = "accountNumber")
	private String accountNumber;
	@XmlElement(name = "description")
	private String description;
	@XmlElement(name = "startBalance")
	private BigDecimal startBalance;
	@XmlElement(name = "mutation")
	private BigDecimal mutation;
	@XmlElement(name = "endBalance")
	private BigDecimal endBalance;

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {

		return "Customer Statement Record: [" + "Transaction Reference = " + reference + ", Account Number = "
				+ accountNumber + ", Description = " + description + ", Start Balance = " + startBalance
				+ ", Mutation = " + mutation + ", End Balance = " + endBalance + "]";

	}
}
