/**
 * 
 */
package notifications.war.controller;

import javax.transaction.Transactional;

import notifications.api.SubscriberTO;
import notifications.api.TokenTO;
import notifications.core.service.SubscriberService;
import notifications.core.service.TokenService;
import notifications.war.NotificationWarException;
import notifications.war.controller.status.MessageStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Subscriber Controller
 * 
 * @author luismr
 *
 */
@Controller
@RequestMapping("/subscriber")
public class SubscriberController extends AbstractController {

	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * Create a Subscriber
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param subscriberTO
	 * @param env
	 * @return
	 * @throws NotificationWarException
	 */ 
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<SubscriberTO> create(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey,
				@RequestBody SubscriberTO subscriberTO
			) throws NotificationWarException {
		
		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Token");
		} else if (subscriberTO.getId() != null) {
			throw new NotificationWarException("Subscriber ID must be null");
		} else if (subscriberTO.getInstanceId() == null) {
			throw new NotificationWarException("Subscriber instanceID must be valid!");
		}
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		if (subscriberTO.getInstanceId().equals(token.getInstanceId()) == false) {
			throw new NotificationWarException("You are trying to do something in another instance!");
		}
		
		SubscriberTO to = subscriberService.create(token, subscriberTO);
		return new ResponseEntity<SubscriberTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Get a Subscriber
	 * @param tokenID
	 * @param tokenKey
	 * @param subscriberId
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<SubscriberTO> read(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey, 
				@PathVariable("subscriberId") Long subscriberId) 
			throws NotificationWarException {
		
		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("token is not invalid!");
		}
		
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		SubscriberTO subscriber = subscriberService.read(token, subscriberId);
		
		if (subscriber.getInstanceId().equals(token.getInstanceId()) == false) {
			throw new NotificationWarException("You are trying to do something in another instance!");
		}
		
		return new ResponseEntity<SubscriberTO>(subscriber, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Update a Subscriber
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param subscriberTO
	 * @return
	 * @throws NotificationWarException
	 */ 
	@Transactional
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.POST, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<SubscriberTO> update(
			@RequestHeader("token-id") String tokenId, 
			@RequestHeader("token-key") String tokenKey,
			@RequestBody SubscriberTO subscriberTO,
			@PathVariable("subscriberId") Long subscriberId
			) throws NotificationWarException {

		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("token is not invalid!");
		} else if (subscriberId == null) {
			throw new NotificationWarException("Subscriber ID must be valid!");
		} else if (subscriberTO.getInstanceId() == null) {
			throw new NotificationWarException("Subscriber instanceID must be valid!");
		}

		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		subscriberTO.setId(subscriberId);
		
		SubscriberTO to = subscriberService.update(token, subscriberTO);
		return new ResponseEntity<SubscriberTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Delete a Category
	 * @param tokenId
	 * @param tokenKey
	 * @param subscriberId
	 * @return
	 * @throws NotificationWarException
	 */
	@Transactional
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<MessageStatus> delete(
			@RequestHeader("token-id") String tokenId, 
			@RequestHeader("token-key") String tokenKey,
			@PathVariable("subscriberId") Long subscriberId
			) throws NotificationWarException {
		
		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("token is not invalid!");
		} else if (subscriberId == null) {
			throw new NotificationWarException("Subscriber ID must be valid!");
		}
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		SubscriberTO subscriber = subscriberService.read(token, subscriberId);
		
		subscriberService.delete(token, subscriberId);
		
		MessageStatus status = new MessageStatus("OK", "Subscriber (" + subscriber.toString() + ") was sucefully removed.");
		
		return new ResponseEntity<MessageStatus>(status, new HttpHeaders(), HttpStatus.OK);
	}
}
