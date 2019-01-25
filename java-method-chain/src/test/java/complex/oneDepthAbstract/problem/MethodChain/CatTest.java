package complex.oneDepthAbstract.problem.MethodChain;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatTest {

    private static final Logger LOG = LoggerFactory.getLogger(CatTest.class);

    @Test
    public void testMethodChain() {
        Cat c1 = new Cat();
        c1.setAwesomeLevel(10) //child
                .setCutenessLevel(20) //child
                .setName("BobbyCat"); //parent
//                .setCutenessLevel(20); //child
        LOG.info("c1 {}", c1);

        Cat c2 = new Cat();
        ((Cat) (c2.setAwesomeLevel(10) //child
                .setCutenessLevel(20) //child
                .setName("BobbyCat"))) //parent
                .setCutenessLevel(5); //child
        LOG.info("c2 {}", c2);
    }
}