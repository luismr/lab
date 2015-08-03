/**
 * 
 */
package notifications.war.controller;

import java.util.List;

import notifications.api.InstanceTO;
import notifications.core.service.InstanceService;
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
 * Notification Controller
 * 
 * @author luismr
 *
 */
@Controller
@RequestMapping("/instance")
public class InstanceController extends AbstractController {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private InstanceService instanceService;
	
	/**
	 * Create a New Instance
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param instanceTO
	 * @return
	 * @throws NotificationWarException
	 */ 
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<InstanceTO> create(
				@RequestHeader("super-token-id") String tokenId, 
				@RequestHeader("super-token-key") String tokenKey,
				@RequestBody InstanceTO instanceTO
			) throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} else if (instanceTO.getId() != null) {
			throw new NotificationWarException("Instance ID must be null");
		} else if (instanceTO.getName() == null || instanceTO.getName().length() > 45) {
			throw new NotificationWarException("Instance Name must be not null and less then 45");
		}
		
		InstanceTO to = instanceService.create(instanceTO);
		return new ResponseEntity<InstanceTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Get all Intances
	 * @param tokenID
	 * @param tokenKey
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<InstanceTO>> list(
				@RequestHeader("super-token-id") String tokenId, 
				@RequestHeader("super-token-key") String tokenKey) 
			throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} 
		
		List<InstanceTO> tokens = instanceService.listInstances();
		
		return new ResponseEntity<List<InstanceTO>>(tokens, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Read an Intance
	 * @param tokenID
	 * @param tokenKey
	 * @Param instanceId
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<InstanceTO> read(
			@RequestHeader("super-token-id") String tokenId, 
			@RequestHeader("super-token-key") String tokenKey,
			@PathVariable("instanceId") Integer instanceId)
					throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} 
		
		InstanceTO instanceTO = instanceService.read(instanceId);
		
		return new ResponseEntity<InstanceTO>(instanceTO, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Delete an Instance
	 * @param tokenID
	 * @param tokenKey
	 * @Param instanceId
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<MessageStatus> delete(
			@RequestHeader("super-token-id") String tokenId, 
			@RequestHeader("super-token-key") String tokenKey,
			@PathVariable Integer instanceId)
					throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} 
		
		InstanceTO instanceTO = instanceService.read(instanceId);
		instanceService.delete(instanceId);
		
		MessageStatus status = new MessageStatus("OK", "Instance (" + instanceTO.toString() + ") was deleted!");
		return new ResponseEntity<MessageStatus>(status, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Update an Instance
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param instanceId
	 * @return
	 * @throws NotificationWarException
	 */ 
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.POST, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<InstanceTO> update(
				@RequestHeader("super-token-id") String tokenId, 
				@RequestHeader("super-token-key") String tokenKey,
				@PathVariable("instanceId") Integer instanceId,
				@RequestBody InstanceTO instanceTO
			) throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} else if (instanceTO.getId() == null) {
			throw new NotificationWarException("Instance ID must be not null");
		} else if (instanceTO.getName() == null || instanceTO.getName().length() > 45) {
			throw new NotificationWarException("Instance Name must be not null and less then 45");
		}
		
		InstanceTO to = instanceService.update(instanceTO);
		return new ResponseEntity<InstanceTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	
}
