package com.cts.application.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.cts.application.model.csv.CustomerStatementRecord;
import com.cts.application.model.xml.Record;
import com.cts.application.model.xml.Records;
import com.cts.application.utils.FileConstants;

//This is a service class to process files - XML/CSV is currently supported.
@Service
public class FileProcessorService {

	@Autowired
	private RecordHelperService recordHelperService;

	@Autowired
	private FileHelperService fileHelperService;

	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;

	private static final Logger log = LoggerFactory.getLogger(FileProcessorService.class);

	/**
	 * This method has the logic to process XML file using JAXB
	 * Marshalling/UnMarshalling
	 * 
	 * @param inputFile
	 * @throws JAXBException
	 * @throws IOException
	 * @throws XmlMappingException
	 */
	public void processXMLFile(File inputFile) throws JAXBException, XmlMappingException, IOException {
		Unmarshaller unmarshaller = (Unmarshaller) jaxb2Marshaller;
		Records inputRecords = (Records) unmarshaller
				.unmarshal(new StreamSource(new FileInputStream(inputFile.getPath())));

		String inputFileName = fileHelperService.getFullFileName(inputFile);

		long startTime = System.currentTimeMillis();
		log.debug(FileConstants.LOG_PREFIX + FileConstants.START_FILE_PROCESS + inputFileName);

		List<Record> failedRecords = processXMLRecords(inputRecords);
		log.debug(FileConstants.LOG_PREFIX + FileConstants.FAILED_RECORDS + failedRecords.size());

		if (failedRecords.size() > 0) {
			String baseName = fileHelperService.getFileBaseName(inputFileName);
			String extension = fileHelperService.getFileExtension(inputFileName);
			Records outputRecords = new Records();
			outputRecords.setRecords(failedRecords);
			Marshaller marshaller = (Marshaller) jaxb2Marshaller;
			marshaller.marshal(outputRecords, new StreamResult(
					new FileOutputStream(fileHelperService.createFailedReportFile(baseName, extension))));

		}

		log.debug(FileConstants.LOG_PREFIX + FileConstants.END_FILE_PROCESS + inputFileName);
		log.debug(FileConstants.LOG_PREFIX + FileConstants.TIME_TO_PROCESS_FILE + inputFileName
				+ FileConstants.EMPTY_SPACE + FileConstants.IS + FileConstants.EMPTY_SPACE
				+ TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime) + FileConstants.EMPTY_SPACE
				+ FileConstants.SECONDS);
	}

	/**
	 * This method has the logic to validate, process individual records present
	 * in XML file and generate failed records[if any]
	 * 
	 * @param records
	 * @return
	 */
	protected List<Record> processXMLRecords(Records records) {

		List<Record> failedRecords = new ArrayList<Record>();
		List<Integer> transactionRefs = new ArrayList<Integer>();
		int i = 0;
		if (records != null && records.getRecords() != null) {
			for (Record record : records.getRecords()) {
				boolean isRecordFailed = false;
				i++;
				if (record != null) {
					if (record.getReference() != null) {
						if (transactionRefs.contains(record.getReference())) {
							log.error(FileConstants.LOG_PREFIX + FileConstants.RECORD_NUMBER + i
									+ FileConstants.EMPTY_SPACE + FileConstants.DUPLICATE_TRANS_REF
									+ record.getReference());
							failedRecords.add(record);
							isRecordFailed = true;
						}
						transactionRefs.add(record.getReference());
					}
					if (record.getStartBalance() != null && record.getMutation() != null
							&& record.getEndBalance() != null) {
						if ((record.getStartBalance().add(record.getMutation())).doubleValue() != record.getEndBalance()
								.doubleValue()) {
							if (!isRecordFailed) {
								log.error(FileConstants.LOG_PREFIX + FileConstants.RECORD_NUMBER + i
										+ FileConstants.EMPTY_SPACE + FileConstants.WRONG_END_BALANCE
										+ record.getReference());
								failedRecords.add(record);
							} else {
								log.error(FileConstants.LOG_PREFIX + FileConstants.RECORD_NUMBER + i
										+ FileConstants.EMPTY_SPACE + FileConstants.WRONG_END_BALANCE_ALSO
										+ record.getReference());
							}
						}
					}
				}
			}
			log.debug(FileConstants.LOG_PREFIX + FileConstants.TOTAL_PROCESSED_RECORDS + records.getRecords().size());
		}
		return failedRecords;
	}

	/**
	 * This method has the logic to process CSV file
	 * 
	 * @param inputFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void processCSVFile(File inputFile) throws FileNotFoundException, IOException {

		try (InputStream inputStream = new FileInputStream(inputFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

			String inputFileName = fileHelperService.getFullFileName(inputFile);

			long startTime = System.currentTimeMillis();
			log.debug(FileConstants.LOG_PREFIX + FileConstants.START_FILE_PROCESS + inputFileName);

			List<String> failedRecords = processRecords(br);

			if (failedRecords.size() > 0) {

				log.debug(FileConstants.LOG_PREFIX + FileConstants.FAILED_RECORDS + (failedRecords.size() - 1));

				String baseName = fileHelperService.getFileBaseName(inputFileName);
				String extension = fileHelperService.getFileExtension(inputFileName);

				try (OutputStream outputStream = new FileOutputStream(
						fileHelperService.createFailedReportFile(baseName, extension));
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream))) {
					generateFailedReport(bw, failedRecords);
				}
			} else {
				log.debug(FileConstants.LOG_PREFIX + FileConstants.FAILED_RECORDS + 0);
			}
			log.debug(FileConstants.LOG_PREFIX + FileConstants.END_FILE_PROCESS + inputFileName);
			log.debug(FileConstants.LOG_PREFIX + FileConstants.TIME_TO_PROCESS_FILE + inputFileName
					+ FileConstants.EMPTY_SPACE + FileConstants.IS + FileConstants.EMPTY_SPACE
					+ TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime)
					+ FileConstants.EMPTY_SPACE + FileConstants.SECONDS);
		}
	}

	/**
	 * This method has the logic to validate, process individual records present
	 * in CSV file and generate failed records[if any]
	 * 
	 * @param br
	 * @return
	 * @throws IOException
	 */
	private List<String> processRecords(BufferedReader br) throws IOException {

		String line = "";
		int i = 0;
		String header = null;
		List<Integer> transactionRefs = new ArrayList<Integer>();
		List<String> failedRecords = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			if (i++ == 0) {
				header = line;
				continue;
			}

			String[] fields = line.split(FileConstants.COMMA);

			CustomerStatementRecord record = null;

			if (fields != null && fields.length == 6) {
				boolean isRecordFailed = false;
				record = new CustomerStatementRecord();
				if (recordHelperService.isFieldValid(fields[0])) {
					Integer transactionRef = Integer.parseInt(fields[0]);
					if (transactionRefs.contains(transactionRef)) {
						if (failedRecords.size() == 0)
							failedRecords.add(header);
						log.error(FileConstants.LOG_PREFIX + FileConstants.RECORD_NUMBER + i + FileConstants.EMPTY_SPACE
								+ FileConstants.DUPLICATE_TRANS_REF + transactionRef);
						failedRecords.add(line);
						isRecordFailed = true;
					}
					transactionRefs.add(transactionRef);
					record.setTransactionRef(transactionRef);
				}
				// NOT REQUIRED for the current assignment. Added those for
				// future requirements.
				// if (recordHelperService.isFieldValid(fields[1])) {
				// record.setAccountNumber(fields[1]);
				// }
				// if (recordHelperService.isFieldValid(fields[2])) {
				// record.setDescription(fields[2]);
				// }
				if (recordHelperService.isFieldValid(fields[3])) {
					BigDecimal startBalance = new BigDecimal(fields[3]);
					record.setStartBalance(startBalance);
				}
				if (recordHelperService.isFieldValid(fields[4])) {
					BigDecimal mutation = new BigDecimal(fields[4]);
					record.setMutation(mutation);
				}
				if (recordHelperService.isFieldValid(fields[5])) {
					BigDecimal endBalance = new BigDecimal(fields[5]);
					if ((record.getStartBalance().add(record.getMutation())).doubleValue() != endBalance
							.doubleValue()) {
						if (failedRecords.size() == 0)
							failedRecords.add(header);
						if (!isRecordFailed) {
							log.error(FileConstants.LOG_PREFIX + FileConstants.RECORD_NUMBER + i
									+ FileConstants.EMPTY_SPACE + FileConstants.WRONG_END_BALANCE
									+ record.getTransactionRef());
							failedRecords.add(line);
						} else {
							log.error(FileConstants.LOG_PREFIX + FileConstants.RECORD_NUMBER + i
									+ FileConstants.EMPTY_SPACE + FileConstants.WRONG_END_BALANCE_ALSO
									+ record.getTransactionRef());
						}
					}
				}
			}
		}

		log.debug(FileConstants.LOG_PREFIX + FileConstants.TOTAL_PROCESSED_RECORDS + (i - 1));
		return failedRecords;
	}

	/**
	 * This method generates XML or CSV file as the report for failed records
	 * 
	 * @param bw
	 * @param failedRecords
	 * @throws IOException
	 */
	private void generateFailedReport(BufferedWriter bw, List<String> failedRecords) throws IOException {
		for (String failedRecord : failedRecords) {
			bw.write(failedRecord + FileConstants.NEW_LINE);
		}
	}
}
