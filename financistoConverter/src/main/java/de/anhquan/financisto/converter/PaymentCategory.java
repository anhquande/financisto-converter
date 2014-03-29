package de.anhquan.financisto.converter;

public class PaymentCategory {
	String name;
	/*
	 * 1  = income
	 * -1 = outcome
	 */
	int type;
	
	public PaymentCategory(String name, int type){
		this.name = name;
		this.type = type;				
	}
	
	public PaymentCategory(String name){
		this.type = -1;
		if (name!=null)
			if (name.startsWith("Eingabe"))
				this.type = 1;
		
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	public String toString(){
		return name;
	}
	
	
}
