package com.cts.application.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.application.utils.FileConstants;

//File Helper Service
@Service
public class FileHelperService {

	@Autowired
	private FileProcessorService fileProcessorService;

	public List<File> collectFiles(String path) throws IOException {

		List<File> fileList = null;

		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
			fileList = paths.filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
		}

		return fileList;

	}

	/**
	 * Creates file for the given path
	 * @param path
	 * @return
	 */
	public File getResource(String path) {
		ClassLoader classLoader = getClassLoader();
		return new File(classLoader.getResource(path).getPath());
	}

	/**
	 * This method returns ClassLoader
	 * @return
	 */
	public ClassLoader getClassLoader() {
		return getClass().getClassLoader();
	}

	/**
	 * This method determines the resource type
	 * @param source
	 * @return
	 */
	public String getResourceType(File resource) {
		String resourceType = null;
		if (resource != null) {
			if (resource.isDirectory())
				resourceType = FileConstants.DIRECTORY;
			else if (resource.isFile())
				resourceType = FileConstants.FILE;
		}

		return resourceType;
	}

	/**
	 * The method determines the Content Type of a file and executes the corresponding logic to process the file
	 * @param fileList
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public int processFiles(List<File> fileList) throws IOException, JAXBException {

		int i = 0;
		for (File file : fileList) {
			String contentType = Files.probeContentType(file.toPath());
			if (contentType != null) {
				if (contentType.equalsIgnoreCase(FileConstants.MIME_TYPE_CSV)) {
					i++;
					fileProcessorService.processCSVFile(file);
				} else if (contentType.equalsIgnoreCase(FileConstants.MIME_TYPE_XML)) {
					i++;
					fileProcessorService.processXMLFile(file);
				}
			}
		}
		return i;
	}

	/**
	 * Method to return full file name - for example "records.csv"
	 * @param file
	 * @return
	 */
	public String getFullFileName(File file) {
		return file.getName();
	}

	/**
	 * Method to return file base name - for example "records"
	 * @param fileName
	 * @return
	 */
	public String getFileBaseName(String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

	/**
	 * Method to return file extension - for example "csv"/"xml"
	 * @param fileName
	 * @return
	 */
	public String getFileExtension(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	/**
	 * Method to create a file for the given file base name and file extension
	 * @param fileBaseName
	 * @param fileExtension
	 * @return
	 */
	public File createFailedReportFile(String fileBaseName, String fileExtension) {
		return new File(System.getProperty(FileConstants.OUTPUT_DIR) + fileBaseName + FileConstants.UNDERSCORE
				+ System.currentTimeMillis() + FileConstants.DOT + fileExtension);
	}

}
