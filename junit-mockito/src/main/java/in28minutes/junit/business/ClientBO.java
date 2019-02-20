package in28minutes.junit.business;

import in28minutes.exception.DifferentCurrenciesException;
import in28minutes.junit.model.Amount;
import in28minutes.junit.model.Product;

import java.util.List;

public interface ClientBO {
	Amount getClientProductsSum(List<Product> products) throws DifferentCurrenciesException;

}