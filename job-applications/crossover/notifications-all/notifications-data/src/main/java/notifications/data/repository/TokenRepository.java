/**
 * 
 */
package notifications.data.repository;

import java.util.List;

import notifications.data.domain.Token;
import notifications.data.domain.id.TokenId;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * CRUD for Tokens
 * @author luismr
 *
 */
public interface TokenRepository extends CrudRepository<Token, TokenId> {
	
	@Cacheable("default")
	@Query("select o from Token o where o.id.tokenId = :tokenId and o.hash = :tokenKey")
	Token getTokenByIdAndKey(@Param("tokenId") String tokenId, @Param("tokenKey") String tokenKey); 
	
	@Query("select o from Token o where o.id.instanceId = :instanceId")
	List<Token> listByInstanceId(@Param("instanceId") Integer instanceId);

	@Cacheable("default")
	@Query("select o from Token o where o.id.tokenId = :tokenId and o.id.instanceId = :instanceId")
	Token getTokenByInstanceAndId(@Param("instanceId") Integer instanceId, @Param("tokenId") String tokenId);
	
}
