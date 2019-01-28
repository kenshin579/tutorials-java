package repository;

import model.Quote;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class QuoteRepository implements QuoteDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override public List<Quote> getQuoteList(Quote quote) {
		return this.sqlSessionTemplate.selectList("", quote);
	}

	@Override public void insertQuote(Quote quote) {
		this.sqlSessionTemplate.insert("", quote);
	}

	@Override public Quote selectQuote(String quoteNo) {
		return this.sqlSessionTemplate.selectOne("", quoteNo);
	}

	@Override public void updateQuote(Quote quote) {
		this.sqlSessionTemplate.update("", quote);
	}

	@Override public void deleteQuote(String quoteNo) {
		this.sqlSessionTemplate.delete("", quoteNo);
	}
}
