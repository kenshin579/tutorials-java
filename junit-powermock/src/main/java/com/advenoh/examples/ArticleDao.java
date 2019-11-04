package kr.pe.advenoh.examples;

public class ArticleDao {
    private static int staticInt;

    public static String setStaticInt(int num) {
        staticInt = num;

        return Integer.toString(staticInt);
    }

    public void insert(Article article) {

    }

}
