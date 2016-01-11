package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;

@Component
public class ProtectThread extends Thread {

	private static final long DELAY = 2500;

	private boolean shutdown = false;
	private long startTime = -1;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FileManagerService fileManagerService;

	public ProtectThread() {
		this.setName(this.getClass().getSimpleName());
		this.startTime = System.currentTimeMillis();
		this.shutdown = false;
	}

	public void shutdown() {
		shutdown = true;
	}

	@Override
	public void run() {
		try {
			while (true) {
				sleep(DELAY);
				
				handleScreenShotFolder();
				flushClipBoard();
				
				if (shutdown) {
					break;
				}
			}
		} catch (Exception e) {
			throw new AboriginalException(e.getMessage(), e);
		}

	}

	private void flushClipBoard() {
		try {
			clipboard().setContents(new Transferable() {
				public DataFlavor[] getTransferDataFlavors() {
					return new DataFlavor[0];
				}

				public boolean isDataFlavorSupported(DataFlavor flavor) {
					return false;
				}

				public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
					throw new UnsupportedFlavorException(flavor);
				}
			}, null);
		} catch (Exception e) {
		}

	}

	private static java.awt.datatransfer.Clipboard clipboard() {
		return Toolkit.getDefaultToolkit().getSystemClipboard();
	}

	private void handleScreenShotFolder() {
		switch (runtimeService.getOperationalSystem()) {
		case OperationalSystem.MAC:
			flushFullScreenShotsFolder();
			break;

		case OperationalSystem.WINDOWS:
			flushLastScreenShots();
			break;
		}
	}

	private void flushLastScreenShots() {
		fileManagerService.deleteLastFilesByTimePassedFromCertainTime(ProtectManager.HOME_SCREENSHOTS, startTime,
				DELAY);
	}

	private void flushFullScreenShotsFolder() {
		fileManagerService.deleteAllFilesFromFolder(ProtectManager.HOME_SCREENSHOTS);
	}

}
