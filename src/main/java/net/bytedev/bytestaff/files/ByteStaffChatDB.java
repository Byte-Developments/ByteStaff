package net.bytedev.bytestaff.files;

import java.sql.*;

public class ByteStaffChatDB {

    private static String url = "jdbc:sqlite:plugins/ByteStaff/staffchat.db";

    public static void CreateTable() {
        try (Connection connection = DriverManager.getConnection(url);
             Statement stmt = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS StaffChat (" +
                    "Username TEXT NOT NULL, " +
                    "UUID TEXT PRIMARY KEY, " +
                    "StaffChat BOOLEAN NOT NULL)";

            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    // Update or insert a record into the StaffChat table
    public static void UpdateStaffChat(String username, String uuid, boolean staffChat) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement pstmt = connection.prepareStatement(
                     "INSERT OR REPLACE INTO StaffChat (Username, UUID, StaffChat) VALUES (?, ?, ?)")) {

            pstmt.setString(1, username);
            pstmt.setString(2, uuid);
            pstmt.setBoolean(3, staffChat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating record: " + e.getMessage());
        }
    }

    // Check if a user has staff chat enabled based on UUID
    public static boolean CheckStaffChat(String uuid) {
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT StaffChat FROM StaffChat WHERE UUID = ?")) {

            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("StaffChat");
            }
        } catch (SQLException e) {
            System.err.println("Error checking staff chat: " + e.getMessage());
        }
        return false;
    }

}