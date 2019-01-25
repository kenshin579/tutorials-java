package solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehicleTest {

    private static final Logger LOG = LoggerFactory.getLogger(VehicleTest.class);

    public static void main(String[] args) {
        TruckProperties prop1 = new TruckProperties();

        prop1.setRegistration("EHW3829") //parent
                .setWheels(8) //parent
                .setMaxLoadWeightInKg(1204); //child
        LOG.info("prop1 {}", prop1);

        TruckProperties prop2 = new TruckProperties();
        prop2.setRegistration("hello") //parent
                .setMaxLoadWeightInKg(234) //child
                .setWheels(234); //parent

        LOG.info("prop1 {}", prop2);

        //이거의 단점은 child의 toString()에 super.toString()을 하면 StackOverFlow가 일어난다.
    }
}
