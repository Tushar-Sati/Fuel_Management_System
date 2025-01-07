package com.fuelmanagement.service;

import com.fuelmanagement.dao.FuelDAO;
import com.fuelmanagement.model.Fuel;

import java.util.List;

public class FuelService {

    private final FuelDAO fuelDAO;

    public FuelService(FuelDAO fuelDAO) {
        this.fuelDAO = fuelDAO;
    }

    /**
     * Adds a new fuel type with the specified quantity.
     *
     * @param type     The type of fuel (e.g., Diesel, Petrol).
     * @param quantity The quantity of the fuel.
     * @return true if the fuel is added successfully, false otherwise.
     */
    public boolean addFuel(String type, double quantity) {
        if (type == null || type.isEmpty() || quantity <= 0) {
            throw new IllegalArgumentException("Invalid fuel type or quantity");
        }

        Fuel fuel = new Fuel();
        fuel.setType(type);
        fuel.setQuantity(quantity);

        return fuelDAO.addFuel(fuel);
    }

    /**
     * Updates the quantity of a specified fuel type.
     *
     * @param fuelId   The ID of the fuel to update.
     * @param quantity The new quantity.
     * @return true if the update is successful, false otherwise.
     */
    public boolean updateFuelQuantity(int fuelId, double quantity) {
        if (fuelId <= 0 || quantity < 0) {
            throw new IllegalArgumentException("Invalid fuel ID or quantity");
        }

        return fuelDAO.updateFuelQuantity(fuelId, quantity);
    }

    /**
     * Deletes a fuel type by its ID.
     *
     * @param fuelId The ID of the fuel to delete.
     * @return true if the deletion is successful, false otherwise.
     */
    public boolean deleteFuel(int fuelId) {
        if (fuelId <= 0) {
            throw new IllegalArgumentException("Invalid fuel ID");
        }

        return fuelDAO.deleteFuel(fuelId);
    }

    /**
     * Retrieves a list of all fuels.
     *
     * @return A list of all fuels.
     */
    public List<Fuel> getAllFuels() {
        return fuelDAO.getAllFuels();
    }

    /**
     * Retrieves details of a specific fuel by ID.
     *
     * @param fuelId The ID of the fuel.
     * @return The details of the specified fuel, or null if not found.
     */
    public Fuel getFuelById(int fuelId) {
        if (fuelId <= 0) {
            throw new IllegalArgumentException("Invalid fuel ID");
        }

        return fuelDAO.getFuelById(fuelId);
    }
}
