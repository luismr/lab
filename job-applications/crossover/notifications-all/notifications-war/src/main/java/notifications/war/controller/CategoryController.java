/**
 * 
 */
package notifications.war.controller;

import java.util.List;

import javax.transaction.Transactional;

import notifications.api.CategoryTO;
import notifications.api.SubscriptionTO;
import notifications.api.TokenTO;
import notifications.core.service.CategoryService;
import notifications.core.service.SubscriberService;
import notifications.core.service.TokenService;
import notifications.war.NotificationWarException;
import notifications.war.controller.request.SubscriberIdRequest;
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
 * Category Subscriptions Controller
 * 
 * @author luismr
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * Get All Categories
	 * @param tokenId
	 * @param tokenKey
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<CategoryTO>> list(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey) throws NotificationWarException {
		if (tokenService.isValid(tokenId, tokenKey) == false) {
			throw new NotificationWarException("token is not invalid!");
		}
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		List<CategoryTO> categories = categoryService.listCategories(token);
		
		return new ResponseEntity<List<CategoryTO>>(categories, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Create a Category
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param categoryTO
	 * @param env
	 * @return
	 * @throws NotificationWarException
	 */ 
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<CategoryTO> create(
				@RequestHeader("super-token-id") String tokenId, 
				@RequestHeader("super-token-key") String tokenKey,
				@RequestBody CategoryTO categoryTO
			) throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} else if (categoryTO.getId() != null) {
			throw new NotificationWarException("Category ID must be null");
		} else if (categoryTO.getInstanceId() == null) {
			throw new NotificationWarException("Category instanceID must be valid!");
		}
		
		CategoryTO to = categoryService.create(categoryTO);
		return new ResponseEntity<CategoryTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Get a Category
	 * @param tokenID
	 * @param tokenKey
	 * @param categoryId
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<CategoryTO> read(
				@PathVariable("categoryId") Integer categoryId) 
			throws NotificationWarException {
		
		CategoryTO category = categoryService.read(categoryId);
				
		return new ResponseEntity<CategoryTO>(category, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Follow a Category as a Subscription
	 * @param tokenId
	 * @param tokenKey
	 * @param categoryId
	 * @param request
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/{categoryId}/follow", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<SubscriptionTO> follow(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey, 
				@PathVariable("categoryId") Integer categoryId,
				@RequestBody SubscriberIdRequest request) throws NotificationWarException {

		CategoryTO category = categoryService.read(categoryId);
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		if (category.getInstanceId().equals(token.getInstanceId()) == false) {
			throw new NotificationWarException("You are trying to do something in another instance!");
		}

		SubscriptionTO subscription = subscriberService.follow(token, request.getSubscriberId(), categoryId);
		return new ResponseEntity<SubscriptionTO>(subscription, new HttpHeaders(), HttpStatus.OK);
	}

	
	/**
	 * Unfollow a Category Subscription
	 * 
	 * @param tokenId
	 * @param tokenKey
	 * @param categoryId
	 * @param request
	 * @return
	 * @throws NotificationWarException
	 */
	@RequestMapping(value = "/{categoryId}/unfollow", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<MessageStatus> unfollow(
				@RequestHeader("token-id") String tokenId, 
				@RequestHeader("token-key") String tokenKey, 
				@PathVariable("categoryId") Integer categoryId,
				@RequestBody SubscriberIdRequest request) throws NotificationWarException {

		CategoryTO category = categoryService.read(categoryId);
		
		TokenTO token = tokenService.getToken(tokenId, tokenKey);
		if (category.getInstanceId().equals(token.getInstanceId()) == false) {
			throw new NotificationWarException("You are trying to do something in another instance!");
		}

		SubscriptionTO subscription = subscriberService.unfollow(token, request.getSubscriberId(), categoryId);
		MessageStatus response = new MessageStatus("OK", "Category Subscription (" + subscription.toString() + ") was sucefully removed.");
		
		return new ResponseEntity<MessageStatus>(response, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Update a Category
	 * 
	 * @param tokenId Super Token ID
	 * @param tokenKey Super Token Key
	 * @param categoryTO
	 * @param env
	 * @return
	 * @throws NotificationWarException
	 */ 
	@Transactional
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.POST, consumes = "application/json" , produces = "application/json")
	public @ResponseBody ResponseEntity<CategoryTO> update(
			@RequestHeader("super-token-id") String tokenId, 
			@RequestHeader("super-token-key") String tokenKey,
			@RequestBody CategoryTO categoryTO,
			@PathVariable("categoryId") Integer categoryId
			) throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} else if (categoryId == null) {
			throw new NotificationWarException("Category ID must be valid!");
		} else if (categoryTO.getInstanceId() == null) {
			throw new NotificationWarException("Category instanceID must be valid!");
		}

		categoryTO.setId(categoryId);
		
		CategoryTO to = categoryService.update(categoryTO);
		return new ResponseEntity<CategoryTO>(to, new HttpHeaders(), HttpStatus.OK);
	}
	
	/**
	 * Delete a Category
	 * @param tokenId
	 * @param tokenKey
	 * @param categoryId
	 * @return
	 * @throws NotificationWarException
	 */
	@Transactional
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<MessageStatus> delete(
			@RequestHeader("super-token-id") String tokenId, 
			@RequestHeader("super-token-key") String tokenKey,
			@PathVariable("categoryId") Integer categoryId
			) throws NotificationWarException {
		
		if (validateSuperToken(tokenId, tokenKey) == false) {
			throw new NotificationWarException("Invalid Super Token");
		} else if (categoryId == null) {
			throw new NotificationWarException("Category ID must be valid!");
		}
		
		CategoryTO category = categoryService.read(categoryId);
		categoryService.delete(categoryId);
		
		MessageStatus status = new MessageStatus("OK", "Category (" + category.toString() + ") was sucefully removed.");
		return new ResponseEntity<MessageStatus>(status, new HttpHeaders(), HttpStatus.OK);
	}
}
