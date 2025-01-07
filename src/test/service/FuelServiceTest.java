package service;

import dao.FuelDAO;
import model.Fuel;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FuelServiceTest {

    @Mock
    private FuelDAO fuelDAO;

    @InjectMocks
    private FuelService fuelService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFuel_Success() {
        Fuel fuel = new Fuel();
        fuel.setType("Petrol");
        fuel.setQuantity(500.0);

        when(fuelDAO.addFuel(fuel)).thenReturn(true);

        boolean result = fuelService.addFuel("Petrol", 500.0);
        assertTrue(result);

        verify(fuelDAO, times(1)).addFuel(any(Fuel.class));
    }

    @Test
    public void testAddFuel_InvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fuelService.addFuel("", -50.0);
        });

        assertEquals("Invalid fuel type or quantity", exception.getMessage());
        verify(fuelDAO, never()).addFuel(any(Fuel.class));
    }

    @Test
    public void testUpdateFuelQuantity_Success() {
        when(fuelDAO.updateFuelQuantity(1, 600.0)).thenReturn(true);

        boolean result = fuelService.updateFuelQuantity(1, 600.0);
        assertTrue(result);

        verify(fuelDAO, times(1)).updateFuelQuantity(1, 600.0);
    }

    @Test
    public void testUpdateFuelQuantity_InvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fuelService.updateFuelQuantity(-1, -100.0);
        });

        assertEquals("Invalid fuel ID or quantity", exception.getMessage());
        verify(fuelDAO, never()).updateFuelQuantity(anyInt(), anyDouble());
    }

    @Test
    public void testDeleteFuel_Success() {
        when(fuelDAO.deleteFuel(1)).thenReturn(true);

        boolean result = fuelService.deleteFuel(1);
        assertTrue(result);

        verify(fuelDAO, times(1)).deleteFuel(1);
    }

    @Test
    public void testDeleteFuel_InvalidFuelId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fuelService.deleteFuel(-1);
        });

        assertEquals("Invalid fuel ID", exception.getMessage());
        verify(fuelDAO, never()).deleteFuel(anyInt());
    }

    @Test
    public void testGetAllFuels() {
        List<Fuel> mockFuels = new ArrayList<>();
        mockFuels.add(new Fuel(1, "Diesel", 300.0));
        mockFuels.add(new Fuel(2, "Petrol", 500.0));

        when(fuelDAO.getAllFuels()).thenReturn(mockFuels);

        List<Fuel> fuels = fuelService.getAllFuels();
        assertNotNull(fuels);
        assertEquals(2, fuels.size());

        verify(fuelDAO, times(1)).getAllFuels();
    }

    @Test
    public void testGetFuelById_Success() {
        Fuel mockFuel = new Fuel(1, "Diesel", 300.0);
        when(fuelDAO.getFuelById(1)).thenReturn(mockFuel);

        Fuel fuel = fuelService.getFuelById(1);
        assertNotNull(fuel);
        assertEquals("Diesel", fuel.getType());
        assertEquals(300.0, fuel.getQuantity());

        verify(fuelDAO, times(1)).getFuelById(1);
    }

    @Test
    public void testGetFuelById_InvalidFuelId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fuelService.getFuelById(-1);
        });

        assertEquals("Invalid fuel ID", exception.getMessage());
        verify(fuelDAO, never()).getFuelById(anyInt());
    }
}
