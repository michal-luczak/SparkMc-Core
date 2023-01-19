package pl.sparkmc.sparkmcspigotcore.data.storage.database.impl;

import com.zaxxer.hikari.HikariDataSource;
import pl.sparkmc.sparkmcspigotcore.SparkSpigotCore;
import pl.sparkmc.sparkmcspigotcore.data.storage.AbstractDatabase;
import pl.sparkmc.sparkmcspigotcore.data.storage.database.MySQLStorage;
import pl.sparkmc.sparkmcspigotcore.data.storage.util.Queries;
import pl.sparkmc.sparkmcspigotcore.data.user.User;
import pl.sparkmc.sparkmcspigotcore.data.user.impl.UserImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static pl.sparkmc.sparkmcspigotcore.data.storage.util.Queries.SELECT_USERS;
import static pl.sparkmc.sparkmcspigotcore.data.storage.util.Queries.USER_TABLES;


public class MySQLStorageImpl extends AbstractDatabase implements MySQLStorage {

    private HikariDataSource dataSource;

    public MySQLStorageImpl(SparkSpigotCore sparkSpigotCore) {
        super(sparkSpigotCore);
    }


    @Override
    protected void create() {

        String databaseHost = "lastcraft.pl";
        String databasePort = "3306";
        String databaseUser = "root";
        String databaseName = "lastcraft";
        String databasePassword = "";

        this.dataSource = new HikariDataSource();

        this.dataSource.setMaximumPoolSize(6);

        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.dataSource.setJdbcUrl("jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName);

        this.dataSource.setUsername(databaseUser);
        this.dataSource.setPassword(databasePassword);

        this.dataSource.addDataSourceProperty("cachePrepStmts", true);
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        this.dataSource.addDataSourceProperty("useServerPrepStmts", true);
        this.dataSource.addDataSourceProperty("serverTimezone", "Europe/Warsaw");

        this.initTables();

        this.loadAll();
    }

    @Override
    protected void destroy() {
        this.sparkSpigotCore.getUserFactory().findAll().forEach(this::saveUser);

        this.dataSource.close();
    }

    private void initTables() {
        try (Connection connection = this.dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(USER_TABLES)) {
                preparedStatement.execute();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void loadAll() {
        try (Connection connection = this.dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    User user = new UserImpl(UUID.fromString(resultSet.getString("uuid")), resultSet.getString("nickname"));
                    user.fromResultSet(resultSet);

                    this.sparkSpigotCore.getUserFactory().registerUser(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void saveUser(User user) {
        String tableName = user.getTableName();

        String[] columnNames = user.getColumnNames();

        Object[] columnValues = user.getColumnValues();

        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Queries.getInsertQuery(tableName, columnNames))
        ) {
            for (int i = 0; i < columnValues.length; i++) {
                preparedStatement.setObject(i + 1, columnValues[i]);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
