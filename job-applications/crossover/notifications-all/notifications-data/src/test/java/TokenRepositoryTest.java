import static org.junit.Assert.*;

import java.util.List;

import notifications.data.NotificationDataConfig;
import notifications.data.domain.Token;
import notifications.data.repository.TokenRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationDataConfig.class)
public class TokenRepositoryTest {

	@Autowired 
	TokenRepository tokenRepository;
	
	@Test
	public void testFindAll() {
		Iterable<Token> tokens = tokenRepository.findAll();
		assertNotNull(tokens);
		
		for (Token token : tokens) {
			System.out.println(token);
		}
		
	}
	
	@Test
	public void testGetTokenByIdAndKey() {
		Token token = tokenRepository.getTokenByIdAndKey("cfcd208495d565ef66e7dff9f98764da", "e2258a02f61f571d86dcab0d9efad046");
		assertNotNull(token);
		
		System.out.println(token);
	}
	
	@Test
	public void testGetTokenByIdAndKeyNotFound() {
		Token token = tokenRepository.getTokenByIdAndKey("null", "null");
		assertNull(token);
		
		System.out.println(token);
	}
	
	@Test
	public void testListByInstanceId() {
		List<Token> tokens = tokenRepository.listByInstanceId(1);
		assertNotNull(tokens);
		assertTrue(tokens.size() > 0);
		
		System.out.println(tokens);
	}
	

}
