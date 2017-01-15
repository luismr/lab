package org.springframework.social.connect.web;

import org.springframework.social.connect.Connection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Luis Machado Reis
 */
public class ProviderSignInAttemptStub extends ProviderSignInAttempt {

	private static final long serialVersionUID = 1L;

	private Connection<?> connection;

    private Set<String> connections = new HashSet<>();

    public ProviderSignInAttemptStub(Connection<?> connection) {
        super(connection, null, null);
        this.connection = connection;
    }

    @Override
    public Connection<?> getConnection() {
        return connection;
    }

    @Override
    void addConnection(String userId) {
        connections.add(userId);
    }

    public Set<String> getConnections() {
        return connections;
    }
}
