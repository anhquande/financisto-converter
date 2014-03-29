package de.anhquan.financisto.converter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TransactionExporter {
	private static final String FINANCISTO_CSV_HEADER = "date;time;account;amount;currency;original amount;original currency;category;parent;payee;location;project;note\r\n";

	/**
	 * Export transactions to Financisto CSV file
	 * 
	 * @param transactions
	 * @param outputFilePath
	 * @throws IOException
	 */
	public static void export2CSV(List<Transaction> transactions,
			String outputFilePath, PaymentRuleManager prManager, CSVRecordModifier modifier)
			throws IOException {
		PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8");
		writer.append(FINANCISTO_CSV_HEADER);
		for (Transaction transaction : transactions) {
			Beneficiary beneficiary = transaction.getBeneficiary();
			CSVRecord rec = new CSVRecord();
			rec.setDateTime(transaction.getValueDate());
			rec.setAmount(transaction.getAmount());
			rec.setNote(transaction.getReason());
			rec.setPayee(beneficiary.getName());
			rec.setPaymentCategory(prManager.findCategory(beneficiary));

			modifier.modify(rec);
			writer.append(rec.toString());
		}
		writer.close();
	}

	/**
	 * Export transactions to Financisto QIF file
	 * 
	 * @param transactions
	 * @param templateFilePath
	 * @param outputFilePath
	 * @throws IOException
	 */
	public static void export2Qif(List<Transaction> transactions,
			String outputFilePath, PaymentRuleManager prManager)
			throws IOException {
		PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8");
		for (Transaction transaction : transactions) {
			Beneficiary beneficiary = transaction.getBeneficiary();
			QifRecord qif = new QifRecord(transaction.getValueDate(),
					transaction.getAmount(),
					prManager.findCategory(beneficiary), beneficiary.getName());
			qif.setMemo(transaction.getReason());
			writer.append(qif.toString());
		}
		writer.close();
	}
}
