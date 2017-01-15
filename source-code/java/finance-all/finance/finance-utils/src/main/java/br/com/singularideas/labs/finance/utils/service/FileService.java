package br.com.singularideas.labs.finance.utils.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.singularideas.labs.finance.utils.FileServiceException;

@Service
public class FileService {

	public File saveStringAsFile(final String payload, final String filename) {
		File file = new File(filename);

		if (file.exists()) {
			throw new FileServiceException(file.getAbsolutePath() + " already exists!");
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(payload);
		} catch (IOException e) {
			throw new FileServiceException(e.getMessage(), e);
		}

		return file;

	}

	public String loadFileAsString(final String filename) {
		if (filename == null) {
			throw new FileServiceException("filename cannot be null!");
		}
		
		return loadFileAsString(new File(filename));
	}
	
	public String loadFileAsString(final File file) {
		if (file == null) {
			throw new FileServiceException("file cannot be null!");
		} else if ( ! file.exists() ) {
			throw new FileServiceException(file.getAbsolutePath() + " does not exists!");
		}
		
		String contents = null;
		try (Scanner scanner = new Scanner(file)) {
			contents = scanner.useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			throw new FileServiceException(file.getAbsolutePath() + " does not exists!");
		}
		
		return contents;
	}

}
