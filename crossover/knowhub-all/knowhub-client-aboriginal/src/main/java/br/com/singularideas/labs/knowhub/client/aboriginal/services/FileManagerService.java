package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import java.io.File;

import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;

@Service
public class FileManagerService {

	public void createFolder(final String path) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(path), "path must be valid");

		File folder = new File(path);
		if (!folder.exists()) {
			if (!folder.mkdirs()) {
				throw new AboriginalException(String.format("cannot create folder [%s]", path));
			}
		}
	}

	public void delete(final String path) {
		delete(path, false);
	}
	
	public void delete(final String path, boolean recursive) {
		delete(new File(path), recursive);
	}

	public void delete(final File path) {
		delete(path, false);
	}

	public void delete(final File file, final boolean recursive) {
		Preconditions.checkArgument(file != null, "file must be valid");
		
		if (! file.exists()) {
			throw new AboriginalException(String.format("File [%s] not exists!", file.getAbsolutePath()));
		}
		
		if (file.isDirectory()) {
			if (recursive) {
				deleteFolder(file.getAbsolutePath());
			} else {
				throw new AboriginalException(String.format("Folder [%s] cannot be deleted because this call wasn't recursive!", file.getAbsolutePath()));				
			}
		} else {
			if (! file.delete()) {
				throw new AboriginalException(String.format("File [%s] cannot be deleted!", file.getAbsolutePath()));				
			}
		}
	}

	public void deleteFolder(final String path) {
		deleteFolder(path, true);
	}
	
	public void deleteFolder(final String path, final boolean self) { 
		Preconditions.checkArgument(!Strings.isNullOrEmpty(path), "path must be valid");

		File folder = new File(path);
		
		if (folder.isDirectory()) {
			for (File file : folder.listFiles()) {
				delete(file, true);
			}
			
			if (self) {
				if (! folder.delete()) {
					throw new AboriginalException(String.format("Cannot delete folder [%s]!", path));
				}
			}
		} else {
			throw new AboriginalException(String.format("Path [%s] is not a folder!", path));
		}
	}
	
	public void deleteAllFilesFromFolder(final String path) {
		deleteFolder(path, false);
	}
	
	public boolean exists(final String path) {
		File file = new File(path);
		return file.exists();
	}

	public void deleteLastFilesByTimePassedFromCertainTime(final String path, final long begin, final long delay) {
		File dir = new File(path);
		
		if (dir.exists()) {
			File[] files = dir.listFiles();
			
			if (files.length > 0) {
				long now = System.currentTimeMillis();
				
				for (File file : files) {
					if (file.lastModified() > begin) {
						if ((now - file.lastModified()) >= delay) {
							delete(file);
						}					
					}
				}
			}
		}

	}

}
