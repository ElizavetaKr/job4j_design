package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private static Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    private static Connection getConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

    public static void createTable(String tableName) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("CREATE TABLE IF NOT EXISTS %s();", tableName));
            }
    }

    public static void dropTable(String tableName) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("DROP TABLE %s;", tableName));
            }
    }

    public static void addColumn(String tableName, String columnName, String type) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type));
            }
    }

    public static void dropColumn(String tableName, String columnName) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("ALTER TABLE %s DROP  COLUMN %s;", tableName, columnName));
            }
    }

    public static void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;",
                        tableName, columnName, newColumnName));
            }
    }

    public static void main(String[] args) throws Exception {
        properties = new Properties();
        TableEditor tableEditor = new TableEditor(properties);
        String table = "new_table";
        createTable(table);
        System.out.println(getTableScheme(table));
        addColumn(table, "name", "text");
        addColumn(table, "age", "int");
        System.out.println(getTableScheme(table));
        renameColumn(table, "age", "amount");
        System.out.println(getTableScheme(table));
        dropColumn(table, "name");
        System.out.println(getTableScheme(table));
        dropTable(table);
        System.out.println(getTableScheme(table));
    }

    public static String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}