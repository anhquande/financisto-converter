package de.anhquan.financisto.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Financisto Compatible CSV Format
 * @author anhquan
 *
 */
public class CSVRecord {

	public static final String DELIMITER = ";";
	public static final String CRLF = "\r\n";
	
	Date dateTime;
	
	String date;
	
	String time;
	
	String account;
	/**
	 * in cent
	 */
	long amount;
	
	String currency = "EUR";
	
	long originalAmount;
	
	String originalCurrency;
	
	String category;
	
	String parent;
	
	String payee;
	
	String location;
	
	String project;
	
	String note;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
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
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the originalAmount
	 */
	public long getOriginalAmount() {
		return originalAmount;
	}

	/**
	 * @param originalAmount the originalAmount to set
	 */
	public void setOriginalAmount(long originalAmount) {
		this.originalAmount = originalAmount;
	}

	/**
	 * @return the originalCurrency
	 */
	public String getOriginalCurrency() {
		return originalCurrency;
	}

	/**
	 * @param originalCurrency the originalCurrency to set
	 */
	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
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

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		//2014-03-28;10:38:51;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		this.dateTime = dateTime;
		this.date = df.format(dateTime);
		this.time="10:38:51";
	}

	//date;time;account;amount;currency;original amount;original currency;category;parent;payee;location;project;note
	//2014-03-28;10:38:51;Anh Quan Cash;-100.00;EUR;"";"";Telefonist;Ausgabe:Fixed Cost:Salary;Miriam;Dai Duong;Den;
	public String toString(){
		StringBuilder str = new StringBuilder();
		long a = Math.abs(amount);
		long d = a / 100;
		long r = a - d*100;
		String strAmount = d + "." + r;
		if (amount<0){					
			strAmount = "-"+strAmount;
		}
		str.append(date+DELIMITER);
		str.append(time+DELIMITER);
		str.append(account+DELIMITER);
		str.append(strAmount+DELIMITER);
		str.append(currency+DELIMITER);
		str.append("\"\""+DELIMITER);
		str.append("\"\""+DELIMITER);
		str.append(category+DELIMITER);
		str.append(parent+DELIMITER);
		str.append(payee+DELIMITER);
		str.append(location+DELIMITER);
		str.append(project+DELIMITER);
		str.append("\""+note+"\"");
		str.append(CRLF);
		
		return str.toString();
	}
	
	public void setPaymentCategory(PaymentCategory paymentCategory) {
		String cat = paymentCategory.getName();
		int p =cat.lastIndexOf(":");
		if (p==-1){
			category = cat;
			parent = "";			
		}
		else{
			category = cat.substring(p);
			parent = cat.substring(0, p);			
		}
	}
}
