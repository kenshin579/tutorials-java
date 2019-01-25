package complex.twoDepthAbstract.solution;

import complex.oneDepthAbstract.solution.CatTest;
import complex.twoDepthAbstract.problem.BombayCat;
import complex.twoDepthAbstract.problem.Cat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BombayCatTest {
    private static final Logger LOG = LoggerFactory.getLogger(CatTest.class);

    @Test
    public void name() {
        BombayCat c1 = new BombayCat();
        c1.setName("BobbyCat") //parent
                .setCutenessLevel(20) //child 부모 클래스 메서드 호출이후에도 자식 클래스 메서드를 호출할 수 있음.
                .setEyeColor("black") //parent
                .setAwesomeLevel(10); //child

        Cat c2 = new BombayCat();
        ((Cat) c2.setName("BobbyCat")) //parent
                .setCutenessLevel(5); //child //Pet 인터페이스인 경우애는 Cast이 필요핟다.

//        c2.setName("BobbyCat")
//                .setCutenessLevel(5); //child //Pet 인터페이스인 경우애는 Cast이 필요핟다.
        LOG.info("c2 {}", c2);
    }
}