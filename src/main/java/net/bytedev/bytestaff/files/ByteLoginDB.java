package net.bytedev.bytestaff.files;

import java.sql.*;


public class ByteLoginDB {

    private static Connection connection;

    public static void createTable() {
        String url = "jdbc:sqlite:plugins/ByteStaff/data/login.db";
        try {
            connection = DriverManager.getConnection(url);
            String sql = "CREATE TABLE IF NOT EXISTS Login (" +
                    "UUID TEXT PRIMARY KEY," +
                    "Player TEXT," +
                    "Password TEXT)";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createOrUpdateLogin(String uuid, String player, String password) {
        String sql = "INSERT OR REPLACE INTO Login(UUID, Player, Password) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid);
            pstmt.setString(2, player);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLogin(String uuid) {
        String sql = "DELETE FROM Login WHERE UUID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String findPasswordByUUID(String uuid) {
        String sql = "SELECT Password FROM Login WHERE UUID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Password");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}