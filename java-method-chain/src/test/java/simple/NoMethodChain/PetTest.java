package simple.NoMethodChain;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PetTest {

    private static final Logger LOG = LoggerFactory.getLogger(PetTest.class);

    @Test
    public void tesWithoutMethodChaining() {
        Pet pet = new Pet();
        pet.setName("BobbyPet");
        pet.setEyeColor("red");
        pet.setHungryLevel(10);
        LOG.info("{}", pet);
    }
}