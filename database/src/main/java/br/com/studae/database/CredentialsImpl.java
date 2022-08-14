package br.com.studae.database;

public class CredentialsImpl implements ICredentials {

    private final String username, password, host, database;
    private final int port;

    public CredentialsImpl(String username, String password, String host, String database, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.database = database;
        this.port = port;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toJDBCURL() {
        return String.format("jdbc:mysql://%s:%d/%s", host, port, database);
    }
}
