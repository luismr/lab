import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import notifications.api.NotificationTO;
import notifications.api.SubscriberTO;
import notifications.api.util.StringUtils;
import notifications.connector.NotificationConnectorConfig;
import notifications.connector.SendEmailException;
import notifications.connector.adaptor.EmailConnector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationConnectorConfig.class)
public class EmailRATest {

	@Autowired
	private EmailConnector connector;
	
	private SubscriberTO subscriber;
	private NotificationTO notification;

	@Before
	public void before() throws NoSuchAlgorithmException {
		subscriber = new SubscriberTO();
		subscriber.setEmail("luis.reis@singularideas.com.br");
		subscriber.setFrequency("DAILY");
		subscriber.setPin("1406");
		subscriber.setHash(StringUtils.md5(subscriber.getPin()));
		subscriber.setId(1L);
		subscriber.setInstanceId(1);
		subscriber.setName("Luis Machado Reis");
		subscriber.setNotifyByPhone("NO");
		subscriber.setPhone("553499783472");
		
		connector.setSubscriber(subscriber);
		
		notification = new NotificationTO();
		notification.setCategoryId(1);
		notification.setData("This is a example Data of dummy nottification!");
		notification.setDateCreated(new Date());
		notification.setId(1L);
		notification.setInstanceId(1);
		notification.setSync("YES");
		notification.setTitle("Dummy");
		notification.setTokenId("1234567890");
		
		connector.setNotification(notification);
	}
	
	@Test
	public void testSend() {
		connector.send();
		assertNotNull(connector);
	}


	@Test(expected = SendEmailException.class)
	public void testValidateInvalid() {
		subscriber.setEmail("invalid@invalid");
		connector.setSubscriber(subscriber);
		connector.send();
		
		assertNotNull(connector);
	}
	
}
