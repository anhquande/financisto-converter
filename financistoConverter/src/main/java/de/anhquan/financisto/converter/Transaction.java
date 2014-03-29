package de.anhquan.financisto.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private static final String DEFALUT_SEPARATOR = ";";
	String account;
	String bookedDate;
	Date valueDate;
	String bookedText;
	String reason;
	
	/**
	 * The person you send money to
	 */
	Beneficiary beneficiary;
	
	/**
	 * in cent
	 */
	long amount;
	String currency;
	String info;
	
	public Transaction(String strLine){
		parse(strLine,DEFALUT_SEPARATOR);
	}
	
	public Transaction(String strLine, String separator){
		parse(strLine, separator);
	}
	
	public void parse(String strLine, String separator){
		String[] fields = strLine.split(separator);
		account = trim(fields[0]);
		bookedDate = trim(fields[1]);
		
		SimpleDateFormat df = new SimpleDateFormat("dd.mm.yy");
		try {
			valueDate = df.parse(trim(fields[2]));
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		bookedText = trim(fields[3]);
		reason = trim(fields[4]);
		beneficiary = new Beneficiary(trim(fields[5]), trim(fields[6]), trim(fields[7]));
		String cent = fields[8].replaceAll(",", "");
		try{
			amount = Long.parseLong(trim(cent));
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		currency = fields[9];
		info = fields[10];
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getBookedText() {
		return bookedText;
	}

	public void setBookedText(String bookedText) {
		this.bookedText = bookedText;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Remove the first and the last quote symbol
	 * @param s
	 * @return
	 */
	private String trim(String s){
		if (s == null)
			return "";
		return s.replaceAll("^\"|\"$", "");
	}
	
}
