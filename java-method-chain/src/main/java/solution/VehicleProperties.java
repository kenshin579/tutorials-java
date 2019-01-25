package solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehicleProperties<T extends VehicleProperties<?>> {
    private static final Logger LOG = LoggerFactory.getLogger(VehicleProperties.class);

    //reference to self as the subclass type
    private String registration;
    private int wheels = 4;

    protected final T self; //child 객체임

    //make it protected to hide the magic
    protected VehicleProperties(final Class<T> selfClass) {
        LOG.info("VehicleProperties called");

        this.self = selfClass.cast(this);
    }

    //return the generic type in our chained methods
    public T setRegistration(final String registration) {
        this.registration = registration;
        return self;
    }

    public T setWheels(final int wheels) {
        this.wheels = wheels;
        return self;
    }

    public int getWheels() {
        return this.wheels;
    }

    public String getRegistration() {
        return this.registration;
    }

    @Override
    public String toString() {
        return "VehicleProperties{" +
                "setRegistration='" + registration + '\'' +
                ", setWheels=" + wheels +
                ", self=" + self +
                '}';
    }
}