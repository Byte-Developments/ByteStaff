package net.bytedev.bytestaff.files;

import java.sql.*;

public class ByteStaffChatDB {

    private static String url = "jdbc:sqlite:plugins/ByteStaff/staffchat.db";

    public static void CreateStaffDB() {

        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "CREATE TABLE IF NOT EXISTS StaffChat (" +
                    "username TEXT NOT NULL," +
                    "uuid TEXT NOT NULL," +
                    "isStaff BOOLEAN" +
                    ")";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void UpdateStaffChat(String UpdateUsername, String UpdateUUID, Boolean IsStaff) {
        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "INSERT OR REPLACE INTO StaffChat (username, uuid, isStaff) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, UpdateUsername);
                pstmt.setString(2, UpdateUUID);
                pstmt.setBoolean(3, IsStaff);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean CheckStaffChat(String CheckUUID, boolean CheckValue) {

        try (Connection connection = DriverManager.getConnection(url)) {
            String SQLine = "SELECT isStaff FROM StaffChat WHERE uuid = ?";
            try (PreparedStatement Statment = connection.prepareStatement(SQLine)) {
                Statment.setString(1, CheckUUID);
                try (ResultSet rs = Statment.executeQuery()) {
                    if (rs.next()) {
                        boolean ActualVal = rs.getBoolean("isStaff");
                        return ActualVal == CheckValue;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}