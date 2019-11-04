package kr.pe.advenoh.examples;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ArticleDao.class)
public class ArticleTest {

    @Test
    public void testGetStaticInt() {
        // mock all the static methods in a class called "Static"
        mockStatic(ArticleDao.class);

        // use mockito to setup your expectation
        when(ArticleDao.setStaticInt(3)).thenReturn("test");

        // execute your test
        String resultStr = ArticleDao.setStaticInt(3);
        System.out.println(resultStr);
    }

}
