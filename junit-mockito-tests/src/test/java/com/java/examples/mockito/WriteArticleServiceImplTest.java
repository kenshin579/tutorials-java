package com.java.examples.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


public class WriteArticleServiceImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void writeArticle() {
        // mock 객체 생성
        ArticleDao mockedDao = mock(ArticleDao.class);
        IdGenerator mockedGenerator = mock(IdGenerator.class);

        WriteArticleServiceImpl writeArticleService = new WriteArticleServiceImpl();
        writeArticleService.setArticleDao(mockedDao);
        writeArticleService.setIdGenerator(mockedGenerator);

        Article article = new Article();
        Article writtenArticle = writeArticleService.writeArticle(article);

        assertNotNull(writtenArticle);
    }
}
