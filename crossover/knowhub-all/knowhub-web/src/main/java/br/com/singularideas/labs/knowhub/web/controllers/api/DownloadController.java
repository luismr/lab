package br.com.singularideas.labs.knowhub.web.controllers.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.singularideas.labs.knowhub.common.data.Item;
import br.com.singularideas.labs.knowhub.model.dao.ItemDAO;
import br.com.singularideas.labs.knowhub.web.ApiRestException;

@Controller
public class DownloadController {

	@Autowired
	private ItemDAO itemDAO;
	
	@RequestMapping(value="/api/download/item/{itemId}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("itemId") long itemId) {
		try {
			Item item = itemDAO.getById(itemId);
			
			File file = new File("/tmp/books/item-" + item.getId() + ".pdf");
			
			if (! file.exists()){
				String errorMessage = "Sorry. The file you are looking for does not exist";
				throw new ApiRestException(errorMessage);
			}
			
			String mimeType= URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType==null){
				mimeType = "application/octet-stream";
			}
			
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
			response.setContentLength((int)file.length());
			
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			throw new ApiRestException("Cannot download file item " + itemId, e);
		}
	}
}
