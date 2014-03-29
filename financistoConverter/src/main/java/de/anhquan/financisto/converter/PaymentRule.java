package de.anhquan.financisto.converter;


public class PaymentRule {

	Beneficiary beneficiary;
	PaymentCategory category;

	public PaymentRule(String strLine){
		String[] fields = strLine.split(";");
		beneficiary = new Beneficiary(fields[0], fields[1], fields[2]);
		category = new PaymentCategory(fields[3]);
	}
	
	public PaymentRule(Beneficiary beneficiary, PaymentCategory category){
		setBeneficiary(beneficiary);
		setCategory(category);		
	}
	
	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public PaymentCategory getCategory() {
		return category;
	}

	public void setCategory(PaymentCategory category) {
		this.category = category;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		if (beneficiary==null){
			str.append(";;;");
		}
		else{
			str.append(beneficiary.getName());
			str.append(";");
			str.append(beneficiary.getAccountNumber());
			str.append(";");
			str.append(beneficiary.getBankCode());
			str.append(";");
		}
		
		if (category == null)
			str.append("");
		else
			str.append(category);
		
		return str.toString();
	}

}
