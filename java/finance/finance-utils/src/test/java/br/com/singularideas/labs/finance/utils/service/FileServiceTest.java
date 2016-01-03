package br.com.singularideas.labs.finance.utils.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.finance.utils.FileServiceException;
import br.com.singularideas.labs.finance.utils.conf.UtilsConfig;
import br.com.singularideas.labs.finance.utils.service.FileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {UtilsConfig.class} )
public class FileServiceTest {

	private static final String FILE_1 = "/tmp/my-new-test-1.txt";
	private static final String FILE_2 = "/tmp/my-new-test-2.txt";
	private static final String PAYLOAD_1 = "great simple test for test 1!";
	private static final String PAYLOAD_2 = "great simple test for test 2!";
	
	@Autowired
	private FileService service;
	
	@Before
	public void setUp() {
		File file1 = new File(FILE_1);
		if (file1.exists()) {
			file1.delete();
		}
		
		File file2 = new File(FILE_2);
		if (file2.exists()) {
			file2.delete();
		}
	}
	
	@Test
	public void testSaveStringAsFile() {
		File file = service.saveStringAsFile(PAYLOAD_1, FILE_1);
		assertNotNull(file);
		assertTrue(file.exists());
		
		String payloadFromFile = service.loadFileAsString(FILE_1);
		assertNotNull(payloadFromFile);
		assertTrue(PAYLOAD_1.equals(payloadFromFile));
		
	}

	@Test
	public void testLoadFileAsString() {
		File file = service.saveStringAsFile(PAYLOAD_2, FILE_2);
		assertNotNull(file);
		assertTrue(file.exists());

		String payloadFromFile = service.loadFileAsString(file);
		assertNotNull(payloadFromFile);
		assertTrue(PAYLOAD_2.equals(payloadFromFile));
	}
	
	@Test(expected=FileServiceException.class)
	public void testLoadFileAsStringFileNull() {
		File file = null;
		String payloadFromFile = service.loadFileAsString(file);
		assertNotNull(payloadFromFile);		
	}

	@Test(expected=FileServiceException.class)
	public void testLoadFileAsStringFilenameNull() {
		String file = null;
		String payloadFromFile = service.loadFileAsString(file);
		assertNotNull(payloadFromFile);		
	}

	@Test(expected=FileServiceException.class)
	public void testLoadFileAsStringFileNotExists() {
		String payloadFromFile = service.loadFileAsString(FILE_1);
		assertNotNull(payloadFromFile);		
	}

	@Test(expected=FileServiceException.class)
	public void testSaveStringAsFileAlreadyExists() {
		File file1 = service.saveStringAsFile(PAYLOAD_1, FILE_1);
		assertNotNull(file1);
		assertTrue(file1.exists());
		
		File file2 = service.saveStringAsFile(PAYLOAD_2, FILE_1);
		assertNotNull(file2);
		assertTrue(file2.exists());
	}

}
