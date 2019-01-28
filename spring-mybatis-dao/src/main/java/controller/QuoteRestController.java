package controller;

import lombok.extern.slf4j.Slf4j;
import model.Quote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.QuoteService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/quote")
public class QuoteRestController {

	@Resource
	QuoteService quoteService;

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, ?> list(Quote quote) {
		List<Quote> listQuote = this.quoteService.find(quote);

		Map<String, Object> result = new HashMap<>();
		result.put("data", listQuote);
		log.info("result: {}", result);
		return result;
	}
}
