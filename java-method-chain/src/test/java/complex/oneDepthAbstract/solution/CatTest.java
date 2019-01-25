package complex.oneDepthAbstract.solution;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatTest {
    private static final Logger LOG = LoggerFactory.getLogger(CatTest.class);

    @Test
    public void testMethodChainWIthGenerics() {
        Cat c1 = new Cat();
        c1.setName("BobbyCat") //parent
                .setCutenessLevel(20) //child 부모 클래스 메서드 호출이후에도 자식 클래스 메서드를 호출할 수 있음.
                .setEyeColor("black") //parent
                .setAwesomeLevel(10); //child

        Pet c2 = new Cat();
        ((Cat) c2.setName("BobbyCat")) //parent
                .setCutenessLevel(5); //child //Pet 인터페이스인 경우애는 Cast이 필요핟다.

//        c2.setName("BobbyCat")
//                .setCutenessLevel(5); //child //Pet 인터페이스인 경우애는 Cast이 필요핟다.
        LOG.info("c2 {}", c2);

    }
}