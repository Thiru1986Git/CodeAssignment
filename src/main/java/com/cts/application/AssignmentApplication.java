package com.cts.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.application.exceptions.FileExceptionWrapper;
import com.cts.application.service.FileHelperService;
import com.cts.application.utils.FileConstants;

@SpringBootApplication(scanBasePackages = { "com.cts" })
public class AssignmentApplication implements CommandLineRunner {

	@Autowired
	private FileHelperService fileHelperService;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	public void run(String... args) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();

		File source = fileHelperService.getSource(classLoader);

		String sourceType = fileHelperService.getSourceType(source);

		if (sourceType == null)
			throw new FileExceptionWrapper("Unsupported File type");

		List<File> fileList = null;

		if (sourceType.equals(FileConstants.DIRECTORY)) {

			fileList = fileHelperService.collectFiles(source.getPath());

		} else if (sourceType.equals(FileConstants.FILE)) {
			fileList = new ArrayList<File>();
			fileList.add(source);
		}

		if (fileList == null)
			throw new FileExceptionWrapper("No file is found to process");

		fileHelperService.processFiles(fileList);

		System.out.println("END");
	}

}
