package com.cts.application.utils;

//The class is used for constants used in the application in order to minimize creation of String instances.
public class FileConstants {

	public static final String RESOURCE_PATH = "Files";
	public static final String DIRECTORY = "directory";
	public static final String FILE = "file";
	// The list of content types for csv is extensive and is not limited to the
	// one mentioned here.
	// However, it is assumed to be application/vnd.ms-excel based on the mime
	// type resulted for records.csv in my local desktop
	public static final String MIME_TYPE_CSV = "application/vnd.ms-excel";
	// The list of content types for xml is extensive and is not limited to the
	// one mentioned here.
	// However, it is assumed to be text/xml based on the mime type resulted for
	// records.xml in my local desktop
	public static final String MIME_TYPE_XML = "text/xml";
	public static final String COMMA = ",";
	public static final String UNDERSCORE = "_";
	public static final String EMPTY_STRING = "";
	public static final String FAILED_REPORT_PATH = "FailedReport";
	public static final String DOT = ".";
	public static final String NEW_LINE = "\n";
	// Cannot write to maven resources/target folder (Access is denied). OS
	// specific temp directory is used as the output folder for failed report
	// instead.
	public static final String OUTPUT_DIR = "java.io.tmpdir";
	public static final String TOTAL_PROCESSED_RECORDS = "Total records processed: ";
	public static final String FAILED_RECORDS = "Number of failed records: ";
	public static final String DUPLICATE_TRANS_REF = "Duplicate record is found with Transaction Reference: ";
	public static final String WRONG_END_BALANCE = "Wrong end balance is found for the record with Transaction Reference: ";
	public static final String WRONG_END_BALANCE_ALSO = "Record already failed. Wrong end balance is also found for the record with Transaction Reference: ";
	public static final String RECORD_NUMBER = "Record #";
	public static final String EMPTY_SPACE = " ";
	public static final String LOG_PREFIX = "CUSTOMER_STATMENT ";
	public static final String START_FILE_PROCESS = "Processing started for file: ";
	public static final String END_FILE_PROCESS = "Processing ended for file: ";
	public static final String TIME_TO_PROCESS_FILE = "Time taken to process file: ";
	public static final String IS = "is";
	public static final String SECONDS = "seconds";
	public static final String NO_FILE_FOUND = "No file is found to process";
	public static final String UNSUPPORTED_RESOURCE = "Unsupported resource";
	public static final String TOTAL_FILES_FOUND = "Total files found: ";
	public static final String TOTAL_FILES_PROCESSED = "Total files processed: ";
}
