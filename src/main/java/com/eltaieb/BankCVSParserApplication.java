package com.eltaieb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.eltaieb.entity.BankEntity;
import com.eltaieb.exception.BonifyException;
import com.eltaieb.model.BankModel;
import com.eltaieb.service.api.BankService;
import com.eltaieb.service.util.Utils;
import lombok.extern.java.Log;

@Log
@SpringBootApplication
public class BankCVSParserApplication {

	public BankCVSParserApplication(BankService bankService) {
		super();
		this.bankService = bankService;
	}

	BankService bankService;

	private static final String BANK_CSV_FILE_PATH = "/banks.csv";

	public static void main(String[] args) {
		SpringApplication.run(BankCVSParserApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return (args) -> {
			importFileAndSaveContentInDataBase();

		};
	}

	
	private void importFileAndSaveContentInDataBase() throws IOException, BonifyException {
		List<BankModel> modelList = loadBanksCVS();
		modelList.stream().forEach(bm -> {
			bankService.addBankEntity(bm);
		});
		String bankIdentifier = "10040000";
		Optional<BankEntity> bankEntityOptional = bankService.findBankByIdentifier(bankIdentifier);
		if (bankEntityOptional.isPresent()) {
			BankEntity bankEntity = bankEntityOptional.get();
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(String.format(" the related bank of %s is identified by  %s ", bankEntity.getBankName(),
					bankIdentifier));
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(
					"----------------------------------------------------------------------------------------------");
		} else {
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(String.format(" No such bank registered with identifier %s ", bankIdentifier));
			System.out.println(
					"----------------------------------------------------------------------------------------------");
			System.out.println(
					"----------------------------------------------------------------------------------------------");
		}
	}

	private List<BankModel> loadBanksCVS() throws IOException {
		List<BankModel> result = new ArrayList<>();
		try (Reader reader = new BufferedReader(
				new InputStreamReader(BankCVSParserApplication.class.getResourceAsStream(BANK_CSV_FILE_PATH)));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
			int counter = 0;
			for (CSVRecord csvRecord : csvParser) {
				// skip first record in the cvs file
				// which is
				// name;bank_identifier
				if (++counter == 1) {
					continue;
				}
				String bankName = csvRecord.get(0);
				String bankIdentifier = csvRecord.get(1);
				if (Utils.isNotEmpty(bankName) && Utils.isNotEmpty(bankIdentifier)) {
					result.add(new BankModel(bankName, bankIdentifier));
				} else {
					log.log(java.util.logging.Level.WARNING,
							"Record in index " + (counter - 1) + " is not properly added");
				}

			}
		}
		return result;
	}
}

