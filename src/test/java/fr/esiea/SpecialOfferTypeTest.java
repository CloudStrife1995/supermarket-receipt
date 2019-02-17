package fr.esiea;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SpecialOfferTypeTest {


//------------ Three for Two ---------------//
@Test
public void ThreeForTwo_Test()
    {
        SupermarketCatalog catalog = new FakeCatalog();
        Product beans_box = new Product("beans box", ProductUnit.Each);
        catalog.addProduct(beans_box, 2.99);
         //Our Cart

        /* We buy 3 bean box */
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(beans_box, 3.0);
        

        // We have a special offer 3 product buy including 1 free  on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, beans_box, 0);

        //then we check articles on the cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double SuposedCartPrice = 2 * 2.99;
        double RealCartPrice = receipt.getTotalPrice();

        //then the cashier announce the amount to pay to the cashier
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("3  beans box bought but 2 was realy buy at 2.99€ and the last is free "); 
    }


//------------ 10 % discount ---------------//
@Test
public void TenPercentDiscount_Test()
    {
         SupermarketCatalog catalog = new FakeCatalog();
        Product beef = new Product("beef ", ProductUnit.Each);
        catalog.addProduct(beef, 8.99);
         //Our Cart

        /* We buy 1 beef coast */
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(beef, 1);
        

        // We have a special offer of 20% discount for beef rib on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, beef, 10);

        //then we check articles on the cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double SuposedCartPrice = 8.99 - (8.99*0.1);
        double RealCartPrice = receipt.getTotalPrice();

        //then the cashier announce the amount to pay to the cashier
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("1 beef rigs at 8.99€ bought with a 10% discount"); 
    }


//------------ Two for amount ---------------//
@Test
public void twoForAmount_Test()
    {
        SupermarketCatalog catalog = new FakeCatalog();
        Product pack_of_water = new Product("pack of water ", ProductUnit.Each);
        catalog.addProduct(pack_of_water, 2.5);
         //Our Cart

        /* We buy 1 beef coast */
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(pack_of_water, 2);
        

         // We have a special offer of 25% discount
        // on pack of water product only if we buy at least 2 article buy at least 
        //on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, pack_of_water, 2.5*2);

        // We apply the discount on the cart now
        Discount discount_on_pack_of_water = new Discount(pack_of_water, "Discount offer on pack of water",1.25);

        //then we check articles on the cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        receipt.addDiscount(discount_on_pack_of_water);

        // suposedCartPrice result  3.75€ 
        double SuposedCartPrice = (2.5*2 -((2.5*2)*0.25));
        double RealCartPrice = receipt.getTotalPrice();

        //then the cashier announce the amount to pay to the cashier
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("Two article buy for the same amount discount of 25% "); 
    }



//------------ Five for amount ---------------//
@Test
public void FiveForAmount_Test()
    {
        SupermarketCatalog catalog = new FakeCatalog();
        Product Orange_Juice= new Product("Orange Juice ", ProductUnit.Each);
        catalog.addProduct(Orange_Juice, 2.00);
         //Our Cart

        // We buy 5 bottle of OrangeJuice 
        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(Orange_Juice, 5);
        

        // We have a special offer of 25% discount
        // on Orange_Juice product only if we buy at least 5 article 
        // buy  on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, Orange_Juice, 2.00*5);

        // We apply the discount on the cart now
        Discount discount_Orange_Juice = new Discount(Orange_Juice, "Discount offer on pack of water",2.5);

        //then we check articles on the cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        receipt.addDiscount(discount_Orange_Juice);

        // supposedCartPrice result 7.50€
        double SuposedCartPrice =  2*5 - ((2*5)*0.25) ;
        double RealCartPrice = receipt.getTotalPrice();

        //then the cashier announce the amount to pay to the cashier
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("Five article buy for the amount discount of 30%"); 
    }

}