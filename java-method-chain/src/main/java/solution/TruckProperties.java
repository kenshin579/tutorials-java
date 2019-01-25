package solution;

public class TruckProperties extends VehicleProperties<TruckProperties> {

    private int maxLoadWeightInKg;

    public TruckProperties() {
        super(TruckProperties.class); //super에 child를 넘겨줌
    }

    public TruckProperties setMaxLoadWeightInKg(final int maxLoadWeightInKg) {
        this.maxLoadWeightInKg = maxLoadWeightInKg;
        return self;
    }

    @Override
    public String toString() {
        return "TruckProperties{" +
//                "Registration='" + super.getRegistration() + '\'' +
//                ",Wheels=" + super.getWheels() +
//                ", self=" + self +
                "MaxLoadWeightInKg=" + maxLoadWeightInKg +
                '}';
    }
}
