package fr.esiea;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import fr.esiea.offerTypes.*;
import java.util.stream.*;

public class OfferTypeTests{
	
	@ParameterizedTest
	@MethodSource("provider")
	void offerTest(Teller teller,ShoppingCart cart, Double supposedPrice) {
		double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();
		assertThat(RealCartPrice).isEqualTo(supposedPrice);


	}

	private static Stream provider() {
		SupermarketCatalog catalog = new FakeCatalog();
		Teller teller = new Teller(catalog);

		// Creating products
		Product beans_box = new Product("beans box", ProductUnit.Each);
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		Product apples = new Product("apples", ProductUnit.Kilo);
		Product beef = new Product("beef ", ProductUnit.Each);
		Product pack_of_water = new Product("pack of water ", ProductUnit.Each);
		Product orange_Juice = new Product("Orange Juice ", ProductUnit.Each);


		// Filling the catalog
		catalog.addProduct(beans_box, 2.99);
        catalog.addProduct(toothbrush, 0.99);
        catalog.addProduct(apples, 1.99);
        catalog.addProduct(beef, 8.99);
        catalog.addProduct(pack_of_water, 2.5);
        catalog.addProduct(orange_Juice, 2.00); 

        // Creating offers
        teller.addSpecialOffer(new ThreeForTwo(), beans_box);
        teller.addSpecialOffer(new PercentDiscount(10.0), beef); // 10% discount
        teller.addSpecialOffer(new TwoForAmount(4.0), pack_of_water);
        teller.addSpecialOffer(new FiveForAmount(7.5), orange_Juice);

        // Creating carts
        ShoppingCart cartIndenpendency = new ShoppingCart();
        ShoppingCart cartThreeForTwoNotDiscounted = new ShoppingCart();
        ShoppingCart cartThreeForTwo = new ShoppingCart();
 		ShoppingCart cartPercentDiscount = new ShoppingCart();
 		ShoppingCart cartTwoForAmountNotDiscounted = new ShoppingCart();
        ShoppingCart cartTwoForAmount = new ShoppingCart();
        ShoppingCart cartFiveForAmountNotDiscounted = new ShoppingCart();
        ShoppingCart cartFiveForAmount = new ShoppingCart();

        // Filling carts
        cartIndenpendency.addItem(apples, 2.5);
        cartThreeForTwoNotDiscounted.addItem(beans_box, 2);
        cartThreeForTwo.addItem(beans_box,3);
		cartPercentDiscount.addItem(beef, 1);
        cartTwoForAmountNotDiscounted.addItem(pack_of_water, 1);
        cartTwoForAmount.addItem(pack_of_water,2);
        cartFiveForAmountNotDiscounted.addItem(orange_Juice, 3);
        cartFiveForAmount.addItem(orange_Juice, 5);


	    return Stream.of(
	            Arguments.of(teller, cartIndenpendency, 2.5 * 1.99),
	            Arguments.of(teller, cartThreeForTwoNotDiscounted,2 * 2.99),
	            Arguments.of(teller, cartThreeForTwo,2 * 2.99),
	            Arguments.of(teller, cartPercentDiscount,8.99 - (8.99*0.1)),
	            Arguments.of(teller, cartTwoForAmountNotDiscounted,2.5),
	            Arguments.of(teller, cartTwoForAmount,4.0),
	            Arguments.of(teller, cartFiveForAmountNotDiscounted,2*3.0),
	            Arguments.of(teller, cartFiveForAmount,7.5)
	    );
	}


}