import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import notifications.api.TokenTO;
import notifications.api.util.StringUtils;
import notifications.core.NotificationCoreConfig;
import notifications.core.service.TokenService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationCoreConfig.class)
public class TokenServiceTest {

	@Autowired
	private TokenService tokenService;
	
	@Test
	public void testIsValid() {
		Boolean valid = tokenService.isValid("cfcd208495d565ef66e7dff9f98764da", "e2258a02f61f571d86dcab0d9efad046");
		assertTrue(valid);
	}
	
	@Test
	public void testListTokenByInstance() {
		List<TokenTO> tokens = tokenService.listTokensByInstance(1);
		assertNotNull(tokens);
		assertTrue(tokens.size() > 0);
	}
	
	@Test
	public void testCreate() throws NoSuchAlgorithmException {
		TokenTO token = tokenService.create(1, "E-commerce", "5tr0ng");
		assertNotNull(token);
		assertTrue(token.getHash().equals(StringUtils.md5("5tr0ng")));
	}

}
