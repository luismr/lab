/**
 * 
 */
package notifications.war.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import notifications.core.NotificationCoreException;
import notifications.war.NotificationWarException;
import notifications.war.controller.status.ErrorStatus;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author luismr
 *
 */
public abstract class AbstractController {

	@Autowired
	protected Environment env;
	
	@ExceptionHandler({NotificationWarException.class, NotificationCoreException.class})
    public ResponseEntity<ErrorStatus> handleNotAuthenticatedException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<ErrorStatus>(new ErrorStatus(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
	
	@ExceptionHandler({ServletRequestBindingException.class, HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<ErrorStatus> handleServletRequestBindingException(Exception e, HttpServletRequest request) {
		return new ResponseEntity<ErrorStatus>(new ErrorStatus(HttpStatus.EXPECTATION_FAILED, e.getMessage()), HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler({PersistentObjectException.class, SQLException.class})
	public ResponseEntity<ErrorStatus> handlePersistentObjectException(Exception e, HttpServletRequest request) {
		return new ResponseEntity<ErrorStatus>(new ErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected boolean validateSuperToken(final String tokenId, final String tokenKey) {
		return (env.getProperty("super.token.id").equals(tokenId) && env.getProperty("super.token.key").equals(tokenKey));
	}
	
}
