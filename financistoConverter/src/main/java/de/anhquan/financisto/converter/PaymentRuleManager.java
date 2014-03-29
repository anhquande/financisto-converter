package de.anhquan.financisto.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PaymentRuleManager {

	private static final String CR_LF = "\r\n";
	List<PaymentRule> rules;

	public PaymentRuleManager() {
		rules = new ArrayList<PaymentRule>();
	}

	public void add(PaymentRule rule) {
		if (rule == null)
			return;

		rules.add(rule);
	}
	
	public boolean addUnique(PaymentRule rule){
		if (rule == null)
			return false;

		PaymentRule foundRule = get(rule.getBeneficiary());
		
		if (foundRule == null){
			rules.add(rule);
			return true;
		}
		
		return false;
	}
	
	public PaymentCategory findCategory(Beneficiary payee){
		PaymentRule rule = get(payee);
		if (rule == null)
			return new PaymentCategory("Unknown_Category");
		
		return rule.getCategory();
	}

	public PaymentRule get(Beneficiary payee) {
		if (payee == null)
			return null;

		for (PaymentRule rule : rules) {
			if (rule.getBeneficiary().equals(payee)) {
				return rule;
			}
		}

		return null;
	}

	/**
	 * Read rules from file
	 * 
	 * @param filePath
	 * @throws IOException 
	 */
	public void readFromFile(String filePath) throws IOException {
		System.out.println("Reading rules from file '"+filePath + "' started ...");
		File fileDir = new File(filePath);
		 
		BufferedReader br = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
		
		rules = new ArrayList<PaymentRule>();
				
		// Read Transaction Line By Line	
		String strLine;
		int lineNo = 0;
		while ((strLine = br.readLine()) != null) {
			lineNo++;
			System.out.println("   line "+lineNo+": "+strLine);
			rules.add(new PaymentRule(strLine));
		}
		
		br.close();
		System.out.println("Reading rules completed. "+lineNo+ " rules have been loaded.");
	}

	public void write(Writer writer) {
		for (PaymentRule rule : rules) {
			try {
				String str = rule.toString();
				writer.write(str);
				writer.write(CR_LF);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Update the payment rule file from the list of transaction
	 * 
	 * @param transactions
	 * @param filePath payment rule file
	 * @return
	 * @throws IOException
	 */
	public boolean syncPaymentRule(List<Transaction> transactions, String filePath) throws IOException{
		readFromFile(filePath);
		
		int newRuleCount = 0;
		for (Transaction transaction : transactions) {
			boolean isSuccessful = addUnique(new PaymentRule(transaction.getBeneficiary(), new PaymentCategory("UNKNOWN_CATEGORY")));
			if (isSuccessful)
				newRuleCount++;
		}
		
		PrintWriter writer = new PrintWriter(filePath, "UTF-8");
		write(writer);
		writer.close();
		
		if (newRuleCount>0){
			System.out.println("There are "+newRuleCount+ " new rules. Please update the rule file '"+filePath+"'");
		}
		
		return newRuleCount==0;
	}
	
}
