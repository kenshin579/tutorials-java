package complex.oneDepthAbstract.problem.NoMethodChain;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatTest {

    private static final Logger LOG = LoggerFactory.getLogger(CatTest.class);

    @Test
    public void testNoMethodChain() {
        Cat c1 = new Cat();
        c1.setName("BobbyCat"); //parent
        c1.setCutenessLevel(20); //child
        c1.setEyeColor("blue"); //parent
        LOG.info("c1 {}", c1);
    }
}