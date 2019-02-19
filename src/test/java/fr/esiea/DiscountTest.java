package fr.esiea;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DiscountTest 

{

@Test
    public void Discount_Methods_Test () 
    {
        SupermarketCatalog catalog = new FakeCatalog();
        Product pack_of_beer = new Product("pack of beer ", ProductUnit.Each);
        catalog.addProduct(pack_of_beer, 2.5);
        
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(pack_of_beer, 2);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, pack_of_beer, 2.5*2);

        Discount discount_on_pack_of_beer = new Discount(pack_of_beer, "Discount offer on pack of beer",1.25);

        //then we check articles on the cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        receipt.addDiscount(discount_on_pack_of_beer);

        // suposedCartPrice result  3.75€ 
        double SuposedCartPrice = (2.5*2 -((2.5*2)*0.25));
        double RealCartPrice = receipt.getTotalPrice();

        // Testing the medthod getDecription of the class Discount
        assertThat(discount_on_pack_of_beer.getDescription().equals("Discount offer on pack of beer")).isEqualTo(true);

        // Testing the method getProduct on the class Discount
        assertThat(discount_on_pack_of_beer.getProduct()).isEqualTo(pack_of_beer);

        // Testing the method getDiscountAmount on the class Discount
        assertThat(discount_on_pack_of_beer.getDiscountAmount()).isEqualTo(1.25);
    }
    
}