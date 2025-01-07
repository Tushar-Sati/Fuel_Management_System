package dao;

import model.Fuel;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelDAO {

    public void addFuel(Fuel fuel) {
        String sql = "INSERT INTO Fuel (type, quantity) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fuel.getType());
            pstmt.setDouble(2, fuel.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fuel> getAllFuels() {
        List<Fuel> fuels = new ArrayList<>();
        String sql = "SELECT * FROM Fuel";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Fuel fuel = new Fuel();
                fuel.setFuelId(rs.getInt("fuel_id"));
                fuel.setType(rs.getString("type"));
                fuel.setQuantity(rs.getDouble("quantity"));
                fuels.add(fuel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fuels;
    }

    public void updateFuelQuantity(int fuelId, double quantity) {
        String sql = "UPDATE Fuel SET quantity = ? WHERE fuel_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, quantity);
            pstmt.setInt(2, fuelId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFuel(int fuelId) {
        String sql = "DELETE FROM Fuel WHERE fuel_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, fuelId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
