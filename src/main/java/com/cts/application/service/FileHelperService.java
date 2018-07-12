package com.cts.application.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.application.utils.FileConstants;

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

	public File getSource(ClassLoader classLoader) {
		return new File(classLoader.getResource(FileConstants.RESOURCE_PATH).getPath());
	}

	public String getSourceType(File source) {
		String sourceType = null;
		if (source != null) {
			if (source.isDirectory())
				sourceType = FileConstants.DIRECTORY;
			else if (source.isFile())
				sourceType = FileConstants.FILE;
		}

		return sourceType;
	}

	public void processFiles(List<File> fileList) throws IOException {

		for (File file : fileList) {
			String contentType = Files.probeContentType(file.toPath());
			if (contentType != null) {
				if (contentType.equalsIgnoreCase(FileConstants.MIME_TYPE_CSV)) {
					fileProcessorService.processCSVFile(file);
				} else if (contentType.equalsIgnoreCase(FileConstants.MIME_TYPE_XML)) {
					fileProcessorService.processXMLFile(file);
				}
			}
		}

	}

}
