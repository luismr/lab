package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {AboriginalConfig.class} )
public class FileManagerServiceTest {

	private static final String WORKDIR = "/tmp/filemanager";

	@Autowired
	private FileManagerService fileManagerService;
	
	@Before
	public void setUp() {
		if (fileManagerService.exists(WORKDIR)) {
			fileManagerService.deleteFolder(WORKDIR);
		}
		
		fileManagerService.createFolder(WORKDIR);
	}
	
	@After
	public void tearDown() {
		if (fileManagerService.exists(WORKDIR)) {
			fileManagerService.deleteFolder(WORKDIR);
		}
	}
	
	@Test
	public void testCreateFolder() {
		assertFalse(fileManagerService.exists(WORKDIR + "/filemanager-test"));
		fileManagerService.createFolder(WORKDIR + "/filemanager-test");
		assertTrue(fileManagerService.exists(WORKDIR + "/filemanager-test"));
		fileManagerService.deleteFolder(WORKDIR + "/filemanager-test");
		assertFalse(fileManagerService.exists(WORKDIR + "/filemanager-test"));
	}

	@Test
	public void testDeleteString() {
		assertFalse(fileManagerService.exists(WORKDIR + "/filemanager-test.txt"));
		fileManagerService.touch(WORKDIR + "/filemanager-test.txt");
		assertTrue(fileManagerService.exists(WORKDIR + "/filemanager-test.txt"));
		fileManagerService.delete(WORKDIR + "/filemanager-test.txt");
		assertFalse(fileManagerService.exists(WORKDIR + "/filemanager-test.txt"));
	}

	@Test
	public void testDeleteStringBoolean() {
		assertFalse(fileManagerService.exists(WORKDIR + "/filemanager-test"));
		fileManagerService.createFolder(WORKDIR + "/filemanager-test");
		assertTrue(fileManagerService.exists(WORKDIR + "/filemanager-test"));
		fileManagerService.touch(WORKDIR + "/filemanager-test/1.txt");
		assertTrue(fileManagerService.exists(WORKDIR + "/filemanager-test/1.txt"));
		fileManagerService.touch(WORKDIR + "/filemanager-test/2.txt");
		assertTrue(fileManagerService.exists(WORKDIR + "/filemanager-test/2.txt"));
		fileManagerService.touch(WORKDIR + "/filemanager-test/3.txt");
		assertTrue(fileManagerService.exists(WORKDIR + "/filemanager-test/3.txt"));
		fileManagerService.delete(WORKDIR + "/filemanager-test", true);
		assertFalse(fileManagerService.exists(WORKDIR + "/filemanager-test"));
	}

}
