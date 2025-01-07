package model;

public class Fuel {
    private int fuelId;
    private String type;
    private double quantity;

    public Fuel() {}

    public Fuel(String type, double quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
