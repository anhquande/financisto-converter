package de.anhquan.financisto.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Financisto compatible QIF format
 * @author anhquan
 *
 */
public class QifRecord {

	private static final String CRLF = "\r\n";

	Date date;
	
	/**
	 * in cent
	 */
	long amount;
	
	PaymentCategory category;
	
	String payee;
	
	String memo;

	public QifRecord(Date date, long amount, PaymentCategory category, String payee){
		this.date = date;
		this.amount = amount;
		this.category = category;
		this.payee = payee;
	}
		
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}

	/**
	 * @return the category
	 */
	public PaymentCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(PaymentCategory category) {
		this.category = category;
	}

	/**
	 * @return the payee
	 */
	public String getPayee() {
		return payee;
	}

	/**
	 * @param payee the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
		str.append("D"+df.format(date)+CRLF);
		long a = Math.abs(amount);
		long d = a / 100;
		long r = a - d*100;
		String strAmount = d + "." + r;
		if (amount<0){					
			strAmount = "-"+strAmount;
		}
		str.append("T"+strAmount+CRLF);		
		str.append("L"+category+CRLF);
		str.append("P"+payee+CRLF);
		str.append("M"+memo+CRLF);
		str.append("^"+CRLF);
		
		return str.toString();
	}
	
}
