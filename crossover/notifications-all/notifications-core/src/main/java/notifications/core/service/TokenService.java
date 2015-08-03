/**
 * 
 */
package notifications.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import notifications.api.TokenTO;
import notifications.api.util.StringUtils;
import notifications.core.NotificationCoreException;
import notifications.core.util.EntityTOTransformUtils;
import notifications.data.domain.Instance;
import notifications.data.domain.Token;
import notifications.data.domain.id.TokenId;
import notifications.data.repository.InstanceRepository;
import notifications.data.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Token Service
 * 
 * @author luismr
 *
 */
@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private InstanceRepository instanceRepository;
	
	/**
	 * Validate if a Token passed to controller is valid or not
	 * 
	 * @param tokenId
	 * @param tokenKey
	 * 
	 * @return
	 */
	public Boolean isValid(final String tokenId, final String tokenKey) {
		Boolean valid = false;
		
		Token token = tokenRepository.getTokenByIdAndKey(tokenId, tokenKey);
		if (token != null) {
			valid = true;
		}
		
		return valid;
	}
	
	/**
	 * Get a Token
	 * 
	 * @param tokenId
	 * @param tokenKey
	 * @return
	 */
	public TokenTO getToken(final String tokenId, final String tokenKey) {
		return EntityTOTransformUtils.transformToken(tokenRepository.getTokenByIdAndKey(tokenId, tokenKey));
	}
	
	/**
	 * Get a Token
	 * 
	 * @param instanceId
	 * @param tokenId
	 * @return
	 */
	public TokenTO getTokenByInstanceAndId(final Integer instanceId, final String tokenId) {
		return EntityTOTransformUtils.transformToken(tokenRepository.getTokenByInstanceAndId(instanceId, tokenId));
	}
	
	/**
	 * List all Tokens by Instance Id
	 * 
	 * @param instanceId
	 * @return
	 */
	public List<TokenTO> listTokensByInstance(final Integer instanceId) {
		List<Token> tokens = tokenRepository.listByInstanceId(instanceId);

		List<TokenTO> tokenTOs = new ArrayList<TokenTO>();
		for (Token token : tokens) {
			tokenTOs.add(EntityTOTransformUtils.transformToken(token));
		}
		
		return tokenTOs;
	}
	
	/**
	 * Create a New Token
	 * 
	 * @param instanceId
	 * @param name
	 * @param password
	 * @return
	 */
	public TokenTO create(final Integer instanceId, final String name, final String password) {
		Instance instance = instanceRepository.findOne(instanceId);
		if (instance == null) {
			throw new NotificationCoreException("Instance not found!");
		}
		
		Token token = new Token();
		
		try {
			token.setName(name);
			token.setPassword(password);
			token.setHash(StringUtils.md5(password));
			
			TokenId tokenId = new TokenId();
			tokenId.setInstanceId(instanceId);
			tokenId.setTokenId(StringUtils.md5(instanceId + name + password));
			
			token.setId(tokenId);
			
			tokenRepository.save(token);
		} catch (Exception e) {
			throw new NotificationCoreException(e.getMessage());
		}
		
		return EntityTOTransformUtils.transformToken(token);
	}

	/**
	 * List All Tokens
	 * @return
	 */
	public List<TokenTO> listTokens() {
		List<TokenTO> tos = new ArrayList<TokenTO>();
		Iterable<Token> tokens = tokenRepository.findAll();
		for (Token token : tokens) {
			tos.add(EntityTOTransformUtils.transformToken(token));
		}
			
		return tos;
	}

	/**
	 * Delete Token
	 * @param tokenTO
	 */
	@Transactional
	public void delete(TokenTO tokenTO) {
		TokenId id = new TokenId();
		id.setInstanceId(tokenTO.getInstanceId());
		id.setTokenId(tokenTO.getId());
		
		tokenRepository.delete(id);
	}
}
