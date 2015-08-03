/**
 * 
 */
package notifications.war.controller;

import java.util.List;

import notifications.api.NotificationTO;
import notifications.api.TokenTO;
import notifications.core.service.NotificationService;
import notifications.core.service.TokenService;
import notifications.war.NotificationWarException;
import notifications.war.controller.status.MessageStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Notification Controller
 * 
 * @author luismr
 *
 */
@Controller
@RequestMapping("/notification")
public class NotificationController extends AbstractController {

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * Send a New Notification
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param notificationTO
	 * @param env
	 * @return
	 * @throws NotificationWarException
	 */ 
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<MessageStatus> send(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey,
				@RequestBody NotificationTO notificationTO
			) throws NotificationWarException {
		
		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Token");
		} else if (notificationTO.getId() != null) {
			throw new NotificationWarException("Notification ID must be null");
		} else if (notificationTO.getInstanceId() == null) {
			throw new NotificationWarException("Notification instanceID must be valid!");
		} else if (notificationTO.getTitle() == null || notificationTO.getTitle().length() > 45) {
			throw new NotificationWarException("Title must be not null and less then 45");
		} else if (notificationTO.getData() != null && notificationTO.getData().length() > 350) {
			throw new NotificationWarException("Data must be less then 350");
		}
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		if (notificationTO.getInstanceId().equals(token.getInstanceId()) == false) {
			throw new NotificationWarException("You are trying to do something in another instance!");
		}
		
		notificationTO.setSync("NO");
		
		notificationService.send(notificationTO);
		MessageStatus status = new MessageStatus("OK", "Notification (" + notificationTO.toString() + ") was sent!");
		return new ResponseEntity<MessageStatus>(status, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Get a Subscriber
	 * @param tokenID
	 * @param tokenKey
	 * @param subscriberId
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/pending", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<NotificationTO>> listPending(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey) 
			throws NotificationWarException {
		
		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("token is not invalid!");
		}
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		List<NotificationTO> notifications = notificationService.getPendingNotifications(token.getInstanceId());
		
		return new ResponseEntity<List<NotificationTO>>(notifications, new HttpHeaders(), HttpStatus.OK);
	}
	
}
