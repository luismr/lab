/**
 * 
 */
package notifications.war.controller;

import java.util.List;

import notifications.api.TokenTO;
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
@RequestMapping("/token")
public class TokenController extends AbstractController {

	@Autowired
	private TokenService tokenService;
	
	/**
	 * Create a New Token
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param tokenTO
	 * @return
	 * @throws NotificationWarException
	 */ 
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<TokenTO> create(
				@RequestHeader("super-token-id") String tokenId, 
				@RequestHeader("super-token-key") String tokenKey,
				@RequestBody TokenTO tokenTO
			) throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} else if (tokenTO.getId() != null) {
			throw new NotificationWarException("Token ID must be null");
		} else if (tokenTO.getInstanceId() == null) {
			throw new NotificationWarException("Token instanceID must be valid!");
		} else if (tokenTO.getName() == null || tokenTO.getName().length() > 45) {
			throw new NotificationWarException("Token Name must be not null and less then 45");
		} else if (tokenTO.getPassword() == null) {
			throw new NotificationWarException("Token must have a Password");
		}
		
		TokenTO to = tokenService.create(tokenTO.getInstanceId(), tokenTO.getName(), tokenTO.getPassword());
		return new ResponseEntity<TokenTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Get all Tokens
	 * @param tokenID
	 * @param tokenKey
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<TokenTO>> list(
				@RequestHeader("super-token-id") String tokenId, 
				@RequestHeader("super-token-key") String tokenKey) 
			throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} 
		
		List<TokenTO> tokens = tokenService.listTokens();
		
		return new ResponseEntity<List<TokenTO>>(tokens, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Delete Token
	 * @param tokenID
	 * @param tokenKey
	 * @Param tokenTO
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<MessageStatus> delete(
			@RequestHeader("super-token-id") String tokenId, 
			@RequestHeader("super-token-key") String tokenKey,
			@RequestBody TokenTO tokenTO)
					throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} 
		
		tokenService.delete(tokenTO);
		
		MessageStatus status = new MessageStatus("OK", "Token (" + tokenTO.toString() + ") was deleted!");
		return new ResponseEntity<MessageStatus>(status, new HttpHeaders(), HttpStatus.OK);
	}
	
}
