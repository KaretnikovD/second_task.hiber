//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//
////    private final Connection connection;
////
////    public UserDaoJDBCImpl() {
////        this.connection = Util.getConnection();
////    }
//
//    public void createUsersTable() {
//        try (Statement createTableStatement = connection.createStatement()) {
//            createTableStatement.executeUpdate("CREATE TABLE IF NOT EXISTS mydatabase1.users" +
//                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
//                    "name VARCHAR(50) NOT NULL, " +
//                    "lastName VARCHAR(50) NOT NULL, " +
//                    "age TINYINT NOT NULL)");
//            connection.commit();
//        } catch (Exception e) {
//            System.err.println("Error during create users table: " + e.getMessage());
//        }
//    }
//
//    public void dropUsersTable() {
//        try (Statement statement = connection.createStatement()) {
//            statement.execute("DROP TABLE IF EXISTS mydatabase1.users");
//            connection.commit();
//        } catch (Exception e) {
//            System.err.println("Error during drop users table: " + e.getMessage());
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        try (PreparedStatement insertStatement = connection.prepareStatement(
//                "INSERT INTO mydatabase1.users (name, lastName, age) VALUES (?, ?, ?)")) {
//            insertStatement.setString(1, name);
//            insertStatement.setString(2, lastName);
//            insertStatement.setByte(3, age);
//            insertStatement.executeUpdate();
//            connection.commit();
//        } catch (Exception e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                System.err.println("Error during try rollback: " + ex.getMessage());
//            }
//            System.err.println("Error during save new user: " + e.getMessage());
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM mydatabase1.users WHERE id = ?")) {
//            deleteStatement.setLong(1, id);
//            int rowsAffected = deleteStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("User deleted");
//            } else {
//                System.out.println("User by ID not found");
//            }
//            connection.commit();
//        } catch (Exception e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                System.err.println("Error during try rollback: " + ex.getMessage());
//            }
//            System.err.println("Deleting error: " + e.getMessage());
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        try (Statement st = connection.createStatement();
//             ResultSet rs = st.executeQuery("SELECT * FROM mydatabase1.users")) {
//            while (rs.next()) {
//                long id = rs.getLong("id");
//                String name = rs.getString("name");
//                String lastName = rs.getString("lastName");
//                byte age = rs.getByte("age");
//                User user = new User(id, name, lastName, age);
//                users.add(user);
//                connection.commit();
//            }
//        } catch (Exception e) {
//            System.err.println("Error to get UserList: " + e.getMessage());
//        }
//        return users;
//    }
//
//    public void cleanUsersTable() {
//        try (Statement st = connection.createStatement()) {
//            st.executeUpdate("TRUNCATE TABLE mydatabase1.users");
//            System.out.println("User table is cleaned");
//        } catch (Exception e) {
//            System.err.println("Error to clean UserList: " + e.getMessage());
//        }
//    }
//}
