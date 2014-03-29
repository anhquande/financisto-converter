package de.anhquan.financisto.converter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class CSVConverter {
	PaymentRuleManager prManager;
	private Configuration config;

	public CSVConverter() {
		prManager = new PaymentRuleManager();
		config = new Configuration("config.properties");
	}

	public void run(String inputFile, String csvOutputFile) {
		
		if (StringUtils.isEmpty(inputFile))
			inputFile = config.get(Configuration.DEFAULT_INPUT_FILE);
		if (StringUtils.isEmpty(csvOutputFile))
			csvOutputFile = config.get(Configuration.DEFAULT_CSV_OUTPUT_FILE);
		
		try {
			List<Transaction> transactions = TransactionParser
					.parse(inputFile);

			if (!prManager.syncPaymentRule(transactions,
					config.get(Configuration.RULE_FILE))) {
				System.out.println("Exit now.");
				return;
			}

			TransactionExporter.export2CSV(transactions, csvOutputFile,
					prManager, new CSVRecordModifier() {
						public void modify(CSVRecord original) {
							original.setAccount(config
									.get(Configuration.FINANCISTO_ACCOUNT));
							original.setProject(config
									.get(Configuration.FINANCISTO_PROJECT));
							original.setLocation("");
						}
					});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
