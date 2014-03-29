package de.anhquan.financisto.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TransactionParser {
	
	/**
	 * Parse CSV-MT940 Format file
	 * 
	 * CSV-MT940 file format (exported from Sparkasse Bank)
	 * Header:   "Auftragskonto";"Buchungstag";"Valutadatum";"Buchungstext";"Verwendungszweck";"Beguenstigter/Zahlungspflichtiger";"Kontonummer";"BLZ";"Betrag";"Waehrung";"Info"
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<Transaction> parse(String filePath) throws IOException{
		System.out.println("Parsing file '"+filePath+ "' started ...");
		List<Transaction> transactions = new ArrayList<Transaction>();
		File fileDir = new File(filePath);
		 
		BufferedReader br = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
		
		String strLine;
		// read header
		br.readLine();
		
		// Read Transaction Line By Line
		int lineNo = 0;
		while ((strLine = br.readLine()) != null) {
			lineNo++;
			System.out.println("   line "+lineNo+": "+strLine);
			transactions.add(new Transaction(strLine));			
		}
		br.close();
		
		System.out.println("Parsing completed. "+lineNo+ " transactions have been parsed.");
		return transactions;
	}
}
