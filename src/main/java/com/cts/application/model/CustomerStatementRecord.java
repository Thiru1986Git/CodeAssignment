package com.cts.application.model;

import java.math.BigDecimal;

public class CustomerStatementRecord {

	private Integer transactionRef;
	private String accountNumber;
	private String description;
	private BigDecimal startBalance;
	private BigDecimal mutation;
	private BigDecimal endBalance;

	public CustomerStatementRecord() {

	}

	public Integer getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(Integer transactionRef) {
		this.transactionRef = transactionRef;
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

		return "Customer Statement Record: [" + "Transaction Reference = " + transactionRef + ", Account Number = "
				+ accountNumber + ", Description = " + description + ", Start Balance = " + startBalance
				+ ", Mutation = " + mutation + ", End Balance = " + endBalance + "]";

	}

}