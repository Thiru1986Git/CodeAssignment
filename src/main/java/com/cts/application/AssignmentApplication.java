package com.cts.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.application.exceptions.FileExceptionWrapper;
import com.cts.application.service.FileHelperService;
import com.cts.application.utils.FileConstants;

//Main Spring Boot application class for the assignment
@SpringBootApplication(scanBasePackages = { "com.cts.application" })
public class AssignmentApplication implements CommandLineRunner {

	@Autowired
	private FileHelperService fileHelperService;

	private static final Logger log = LoggerFactory.getLogger(AssignmentApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	public void run(String... args) throws Exception {

		//Load Class path resource
		File resource = fileHelperService.getResource(FileConstants.RESOURCE_PATH);

		//Determine Source Type - Directory or File
		String sourceType = fileHelperService.getResourceType(resource);

		if (sourceType == null) {
			log.error(FileConstants.LOG_PREFIX + FileConstants.UNSUPPORTED_RESOURCE + FileConstants.EMPTY_SPACE
					+ resource.getName());
			throw new FileExceptionWrapper(FileConstants.UNSUPPORTED_RESOURCE);
		}

		List<File> fileList = null;

		//Create File list for processing
		if (sourceType.equals(FileConstants.DIRECTORY)) {
			fileList = fileHelperService.collectFiles(resource.getPath());
		} else if (sourceType.equals(FileConstants.FILE)) {
			fileList = new ArrayList<File>();
			fileList.add(resource);
		}

		log.info(FileConstants.LOG_PREFIX + FileConstants.NO_FILE_FOUND);

		//Process XML/CSV files present in the file list and generate XML/CSV reports for failed records in the files
		int i = fileHelperService.processFiles(fileList);
		log.info(FileConstants.LOG_PREFIX + FileConstants.TOTAL_FILES_FOUND + fileList.size());
		log.info(FileConstants.LOG_PREFIX + FileConstants.TOTAL_FILES_PROCESSED + i);
	}

}
