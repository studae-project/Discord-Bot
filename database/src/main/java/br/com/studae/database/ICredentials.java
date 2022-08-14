package br.com.studae.database;

import org.bukkit.configuration.ConfigurationSection;

public interface ICredentials {

    String getUsername();

    String getPassword();

    String toJDBCURL();

    static ICredentials parse(ConfigurationSection section) {
        return new CredentialsImpl(
                section.getString("username"),
                section.getString("password"),
                section.getString("host-name"),
                section.getString("database"),
                section.getInt("port")
        );
    }

}
