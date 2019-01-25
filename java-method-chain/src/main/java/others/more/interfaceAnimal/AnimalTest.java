package others.more.interfaceAnimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimalTest {

    private static final Logger LOG = LoggerFactory.getLogger(AnimalTest.class);

    public static void main(String[] args) {
        BombayCat cat1 = new BombayCat();
        cat1.setName("fra") //parent
                .setAwesomeLevel(234) //child - cat
                .setHungryLevel(1234) //parent - pet
                .setCutenessLevel(2); //child - cat

        LOG.info("cat1 {}", cat1);

        Cat cat2 = new BombayCat();
        ((BombayCat) cat2).setName("parent") //parent
                .setAwesomeLevel(1234) //child - cat
                .setHungryLevel(1234) //parent - pet
                .setCutenessLevel(2); //child - cat

        LOG.info("cat2 {}", cat2);
        Cat cat3 = new BombayCat();
        cat3.setName("sdf");
//                .setAwesomeLevel(1234);

        LOG.info("cat3 {}", cat3);
    }
}
