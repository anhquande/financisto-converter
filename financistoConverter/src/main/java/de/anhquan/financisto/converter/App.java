package de.anhquan.financisto.converter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Convert a CSV-MT940 file format exported from your Bank into a CSV or QIF file
 * compatible with Financisto (http://financisto.com). The converted file is now
 * ready to be import into Financisto database.
 * 
 * Please note that at the time of this writing (29.03.2014) Financisto CSV/QIF import
 * function does not import all the fields.
 * 
 * @author anhquan
 * 
 */
public class App {

	public static void main(String[] args) {
		
		String inputFile = null;
		String csvOutputFile = null;
		
		// create the command line parser
		Options options = new Options();
		options.addOption("i","input", true, "CSV Input File");
		options.addOption("o","output-csv", true, "CSV Output File");
		options.addOption("h","help", false, "Show help");

		// ** now lets parse the input
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException pe) {
			showHelp(options);
			return;
		}

		if (cmd.hasOption("i")) {
			inputFile = cmd.getOptionValue("i");
		}
		
		if (cmd.hasOption("o")) {
			csvOutputFile  = cmd.getOptionValue("o");
		}
		
		if (cmd.hasOption("h")){
			showHelp(options);
			return;
		}

		CSVConverter app = new CSVConverter();
		app.run(inputFile, csvOutputFile);
	}

	private static void showHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Financisto Converter", options);
	}
}
