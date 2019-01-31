package in28minutes.junit.business;

import in28minutes.exception.DifferentCurrenciesException;
import in28minutes.junit.model.Amount;
import in28minutes.junit.model.AmountImpl;
import in28minutes.junit.model.Currency;
import in28minutes.junit.model.Product;
import in28minutes.junit.model.ProductImpl;
import in28minutes.junit.model.ProductType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientBOTest {

	private ClientBO clientBO = new ClientBOImpl();

	@Test
	public void testClientProductSum() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<>();

		products.add(new ProductImpl(100, "Product 15",
				ProductType.BANK_GUARANTEE, new AmountImpl(
				new BigDecimal("5.0"), Currency.EURO)));

		products.add(new ProductImpl(120, "Product 20",
				ProductType.BANK_GUARANTEE, new AmountImpl(
				new BigDecimal("6.0"), Currency.EURO)));

		Amount temp = clientBO.getClientProductsSum(products);

		assertEquals(Currency.EURO, temp.getCurrency());
		assertEquals(new BigDecimal("11.0"), temp.getValue());
	}

	@Test(expected = DifferentCurrenciesException.class)
	public void testClientProductSum1() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<>();

		products.add(new ProductImpl(100, "Product 15",
				ProductType.BANK_GUARANTEE, new AmountImpl(
				new BigDecimal("5.0"), Currency.INDIAN_RUPEE)));

		products.add(new ProductImpl(120, "Product 20",
				ProductType.BANK_GUARANTEE, new AmountImpl(
				new BigDecimal("6.0"), Currency.EURO)));

		@SuppressWarnings("unused")
		Amount temp = null;

		temp = clientBO.getClientProductsSum(products);
	}

	@Test
	public void testClientProductSum2() {

		List<Product> products = new ArrayList<>();

		Amount temp = null;

		try {
			temp = clientBO.getClientProductsSum(products);
		} catch (DifferentCurrenciesException e) {
		}
		assertEquals(Currency.EURO, temp.getCurrency());
		assertEquals(BigDecimal.ZERO, temp.getValue());
	}

}