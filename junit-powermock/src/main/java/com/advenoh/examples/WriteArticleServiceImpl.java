package com.advenoh.examples;


public class WriteArticleServiceImpl {
    private IdGenerator idGenerator;
    private ArticleDao articleDao;

    public Article writeArticle(Article article) {
        Integer id = idGenerator.getNextId();
        article.setId(id);
        articleDao.insert(article);
        return article;
    }

    public void setArticleDao(ArticleDao mockedDao) {
        // TODO Auto-generated method stub

    }

    public void setIdGenerator(IdGenerator mockedGenerator) {
        // TODO Auto-generated method stub

    }

}
