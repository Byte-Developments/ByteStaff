package net.bytedev.bytestaff.files;

import java.sql.*;

public class ByteWhitelistDB {

    private static String url = "jdbc:sqlite:plugins/ByteStaff/whitelist.db";

    public static void CreateWhitelistDB() {

        try (Connection connection = DriverManager.getConnection(url)) {
            String sql = "CREATE TABLE IF NOT EXISTS Whitelist (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Player TEXT NOT NULL," +
                    "UUID TEXT NOT NULL," +
                    "Whitelist BOOLEAN" +
                    ")";
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void ByteAddWH(String player, String uuid, boolean whitelist) {
        String sql = "INSERT INTO Whitelist (Player, UUID, Whitelist) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url)) {
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, player);
                pstmt.setString(2, uuid);
                pstmt.setBoolean(3, whitelist);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ByteRemoveWH(String player) {
        String sql = "DELETE FROM Whitelist WHERE Player = ?";
        try (Connection connection = DriverManager.getConnection(url)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, player);
            statement.executeUpdate();
            System.out.println("Player removed from Whitelist.");
        } catch (SQLException e) {
            System.err.println("Failed to remove player: " + e.getMessage());
        }
    }

}