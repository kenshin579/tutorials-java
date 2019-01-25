package simple.methodChain;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetTest {
    private static final Logger LOG = LoggerFactory.getLogger(PetTest.class);

    @Test
    public void testMethodChaining() {
        Pet pet = new Pet();
        pet.setName("BobbyPet")
                .setEyeColor("red")
                .setHungryLevel(10);
        LOG.info("{}", pet);
    }
}