package dao;

import model.Fuel;
import org.junit.jupiter.api.*;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FuelDAOTest {

    private FuelDAO fuelDAO;

    @BeforeAll
    public void setup() {
        fuelDAO = new FuelDAO();
    }

    @BeforeEach
    public void clearDatabase() {
        // Clear the Fuel table before each test
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Fuel")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddFuel() {
        Fuel fuel = new Fuel();
        fuel.setType("Diesel");
        fuel.setQuantity(100.0);

        fuelDAO.addFuel(fuel);

        List<Fuel> fuels = fuelDAO.getAllFuels();
        assertEquals(1, fuels.size());
        assertEquals("Diesel", fuels.get(0).getType());
        assertEquals(100.0, fuels.get(0).getQuantity());
    }

    @Test
    public void testGetAllFuels() {
        Fuel fuel1 = new Fuel();
        fuel1.setType("Petrol");
        fuel1.setQuantity(200.0);

        Fuel fuel2 = new Fuel();
        fuel2.setType("CNG");
        fuel2.setQuantity(300.0);

        fuelDAO.addFuel(fuel1);
        fuelDAO.addFuel(fuel2);

        List<Fuel> fuels = fuelDAO.getAllFuels();
        assertEquals(2, fuels.size());
    }

    @Test
    public void testUpdateFuelQuantity() {
        Fuel fuel = new Fuel();
        fuel.setType("LPG");
        fuel.setQuantity(150.0);

        fuelDAO.addFuel(fuel);

        List<Fuel> fuels = fuelDAO.getAllFuels();
        int fuelId = fuels.get(0).getFuelId();

        fuelDAO.updateFuelQuantity(fuelId, 200.0);

        Fuel updatedFuel = fuelDAO.getAllFuels().get(0);
        assertEquals(200.0, updatedFuel.getQuantity());
    }

    @Test
    public void testDeleteFuel() {
        Fuel fuel = new Fuel();
        fuel.setType("Kerosene");
        fuel.setQuantity(50.0);

        fuelDAO.addFuel(fuel);

        List<Fuel> fuels = fuelDAO.getAllFuels();
        int fuelId = fuels.get(0).getFuelId();

        fuelDAO.deleteFuel(fuelId);

        fuels = fuelDAO.getAllFuels();
        assertTrue(fuels.isEmpty());
    }

    @AfterAll
    public void tearDown() {
        // Cleanup database after all tests
        clearDatabase();
    }
}
