package de.anhquan.financisto.converter;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Beneficiary {

	String name = "";
	String accountNumber = "";
	String bankCode = "";

	public Beneficiary(String name, String accountName, String bankCode) {
		setName(name);
		setAccountNumber(accountName);
		setBankCode(bankCode);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Beneficiary other = (Beneficiary) obj;
		if (name.compareTo(other.getName()) != 0)
			return false;
		if (accountNumber.compareTo(other.getAccountNumber()) != 0)
			return false;
		if (bankCode.compareTo(other.getBankCode()) != 0)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(name).append(accountNumber).append(bankCode)
				.toHashCode();
	}
}
