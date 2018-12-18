package com.spring.example.service;

import com.spring.example.model.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteService {
    private

//
//    public Quote getQuote() {
//        Quote quote = new Quote("에디슨", "");
//        return quote;
//    }


    // Dummy database. Initialize with some dummy values.
    private static List<Quote> quotes;
    {
        quotes = new ArrayList();
        quotes.add(new Quote(1, "에디슨", "책을 읽는 다는 것은 많은 경우에 자신의 미래를 만드는 것과 같은 뜻이다"));
        quotes.add(new Quote(2, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
        quotes.add(new Quote(3, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
        quotes.add(new Quote(System.currentTimeMillis(), "Viral", "Patel", "vpatel@gmail.com", "356-758-8736"));
    }

    /**
     * Returns list of quotes from dummy database.
     *
     * @return list of quotes
     */
    public List list() {
        return quotes;
    }

    /**
     * Return Quote object for given id from dummy database. If Quote is
     * not found for id, returns null.
     *
     * @param id
     *            Quote id
     * @return Quote object for given id
     */
    public Quote get(int id) {

        for (Quote c : quotes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Create new Quote in dummy database. Updates the id and insert new
     * Quote in list.
     *
     * @param Quote
     *            Quote object
     * @return Quote object with updated id
     */
    public Quote create(Quote Quote) {
        Quote.setId(System.currentTimeMillis());
        quotes.add(Quote);
        return Quote;
    }

    /**
     * Delete the Quote object from dummy database. If Quote not found for
     * given id, returns null.
     *
     * @param id
     *            the Quote id
     * @return id of deleted Quote object
     */
    public Long delete(Long id) {

        for (Quote c : quotes) {
            if (c.getId().equals(id)) {
                quotes.remove(c);
                return id;
            }
        }

        return null;
    }

    /**
     * Update the Quote object for given id in dummy database. If Quote
     * not exists, returns null
     *
     * @param id
     * @param Quote
     * @return Quote object with id
     */
    public Quote update(Long id, Quote Quote) {

        for (Quote c : quotes) {
            if (c.getId().equals(id)) {
                Quote.setId(c.getId());
                quotes.remove(c);
                quotes.add(Quote);
                return Quote;
            }
        }

        return null;
    }
}
