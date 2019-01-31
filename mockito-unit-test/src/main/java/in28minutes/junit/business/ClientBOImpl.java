package in28minutes.junit.business;

import in28minutes.exception.DifferentCurrenciesException;
import in28minutes.junit.model.Amount;
import in28minutes.junit.model.AmountImpl;
import in28minutes.junit.model.Currency;
import in28minutes.junit.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ClientBOImpl implements ClientBO {

	@Override
	public Amount getClientProductsSum(List<Product> products) throws DifferentCurrenciesException {
		if (products.size() == 0)
			return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		if (!isCurrencySameForAllProducts(products)) {
			throw new DifferentCurrenciesException();
		}

		BigDecimal productSum = calculateProductSum(products);

		Currency firstProductCurrency = products.get(0).getAmount().getCurrency();

		return new AmountImpl(productSum, firstProductCurrency);
	}

	private BigDecimal calculateProductSum(List<Product> products) {
		BigDecimal sum = BigDecimal.ZERO;
		// Calculate Sum of Products
		for (Product product : products) {
			sum = sum.add(product.getAmount().getValue());
		}
		return sum;
	}

	private boolean isCurrencySameForAllProducts(List<Product> products) throws DifferentCurrenciesException {

		Currency firstProductCurrency = products.get(0).getAmount().getCurrency();

		for (Product product : products) {
			boolean currencySameAsFirstProduct = product.getAmount().getCurrency().equals(firstProductCurrency);
			if (!currencySameAsFirstProduct) {
				return false;
			}
		}
		return true;
	}
}