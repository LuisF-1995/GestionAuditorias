package com.lurodev.usersauditorapi.multitenancy.provider;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ConnectionProvider implements MultiTenantConnectionProvider<String>, HibernatePropertiesCustomizer {
    private final DataSource dataSource;
    @Value("${multitenancy.database.default-schema}")
    String defaultSchema;

    private DataSource createDataSource(String url, String schema, String username, String password) {
        String urlNewDb = url + schema;
        DriverManagerDataSource tenantDataSource = new DriverManagerDataSource();
        tenantDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        tenantDataSource.setUrl(urlNewDb);
        tenantDataSource.setUsername(username);
        tenantDataSource.setPassword(password);
        return tenantDataSource;
    }
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        String dataSourceUrl = "jdbc:mysql://localhost:3306/";
        String schema = tenantIdentifier;

        if(!tenantIdentifier.equals(defaultSchema))
            schema = "auditor_users_" + tenantIdentifier;

        DataSource newDataSource = this.createDataSource(dataSourceUrl, schema, "root", "DevMed@2022*");
        Connection connection = newDataSource.getConnection();
        connection.setSchema(schema);

        return connection;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return getConnection(defaultSchema);
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.setSchema(defaultSchema);
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
    }

    @Override
    public boolean isUnwrappableAs(Class<?> unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
