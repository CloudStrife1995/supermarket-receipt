package fr.esiea;

import static org.assertj.core.api.Assertions.*;
import  org.junit.jupiter.api.Test;


public class SupermarketTest {

    @Test
    public void test_discount_indenpency_between_Product() {

        // Initialization of the catalogue
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

         //Our Cart

        /* We buy 2.5k of apple */
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        

        // We have a special offer of 10% discount for tootbrush on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        //then we check articles onthe cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double SuposedCartPrice = 2.5 * 1.99;
        double RealCartPrice = receipt.getTotalPrice();

        //then the cashier announce the amount to pay to the cashier
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2.5kg of apple for a price of 1.99€ with a disount  of 10% on toothbruth");
    }
}
