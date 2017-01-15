package org.springframework.social.connect.support;

import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;

/**
 * @author Luis Machado Reis
 */
public class TestConnection extends AbstractConnection<Object> {

	private static final long serialVersionUID = 1L;

	private ConnectionData connectionData;

    private UserProfile userProfile;

    public TestConnection(ConnectionData connectionData, UserProfile userProfile) {
        super(connectionData, null);
        this.connectionData = connectionData;
        this.userProfile = userProfile;
    }

    @Override
    public UserProfile fetchUserProfile() {
        return userProfile;
    }

    @Override
    public Object getApi() {
        return null;
    }

    @Override
    public ConnectionData createData() {
        return connectionData;
    }
}
