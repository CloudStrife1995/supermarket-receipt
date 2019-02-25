package fr.esiea;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Map;
import fr.esiea.offerTypes.*;

public class ShoppingCartTest 
{



    @Test
    public void addItem_Test()
    {
        Product candy_bag= new Product("candy bag", ProductUnit.Each);
         //we create our cart to add item further;
         ShoppingCart cart = new ShoppingCart();
         //we want buy 5 candy bag for children
        cart.addItem(candy_bag);

        Map<Product, Double> productQuantities = cart.getProductsInCart();
        double productQuantity = productQuantities.get(candy_bag);

        double supposed_product_quantity =1.0;
        assertThat(supposed_product_quantity).isEqualTo(productQuantity);    
    }
    
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
        cart.addItem(apples, 2.5);
        

        // We have a special offer of 10% discount for tootbrush on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new PercentDiscount(10.0), toothbrush);

        //then we check articles onthe cart 
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        double SuposedCartPrice = 2.5 * 1.99;
        double RealCartPrice = receipt.getTotalPrice();

        //then the cashier announce the amount to pay to the cashier
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2.5kg of apple for a price of 1.99€ with a disount  of 10% on toothbruth");
    }



//------------ Three for Two ---------------//
        @Test
        public void ThreeForTwo_Test()
        {
            //We create a product "beans box" with a special offer of the type "ThreeForTwo"
            SupermarketCatalog catalog = new FakeCatalog();
            Teller teller = new Teller(catalog);
            Product beans_box = new Product("beans box", ProductUnit.Each);
            catalog.addProduct(beans_box, 2.99);
            teller.addSpecialOffer( new ThreeForTwo(), beans_box);

            /* We buy 2 bean box */
            ShoppingCart cart = new ShoppingCart();
            cart.addItem(beans_box, 2);           

            // suposedCartPrice is 2*2.99€
            double SuposedCartPrice = 2 * 2.99;
            // we get the real price of the cart
            double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

            //then we check that the price is correct 
            assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2 beans_box has been bought for the price of 2"); 



            /* We buy 1 more bean box */
            cart.addItem(beans_box); 

            // suposedCartPrice is 2*2.99€
            SuposedCartPrice = 2 * 2.99;
            // we get the real price of the cart
            RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

            //then we check that the price is correct 
            assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("2 beans_box has been bought for the price of 3"); 

        }


//------------ 10 % discount ---------------//
        @Test
        public void PercentDiscount_Test()
        {
           //We create a product "beef" with a special offer of the type "PercentDiscount"
           SupermarketCatalog catalog = new FakeCatalog();
           Teller teller = new Teller(catalog);
           Product beef = new Product("beef ", ProductUnit.Each);
           catalog.addProduct(beef, 8.99);
           teller.addSpecialOffer(new PercentDiscount(10.0), beef); // 10% discount

           /* We buy 1 beef coast */
           ShoppingCart cart = new ShoppingCart();
           cart.addItem(beef, 1);

           // suposedCartPrice is 8.99 - (8.99*0.1) 
           double SuposedCartPrice = 8.99 - (8.99*0.1);
           // we get the real price of the cart
           double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

            //then the cashier announce the amount to pay to the cashier
           assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("1 beef rigs at 8.99€ bought with a 10% discount"); 
       }


//------------ Two for amount ---------------//
       @Test
       public void twoForAmount_Test()
       {
        //We create a product "pack of water" with a special offer of the type "twoForAmount"
        SupermarketCatalog catalog = new FakeCatalog();
        Teller teller = new Teller(catalog);
        Product pack_of_water = new Product("pack of water ", ProductUnit.Each);
        catalog.addProduct(pack_of_water, 2.5);
        teller.addSpecialOffer(new TwoForAmount(4.0), pack_of_water);

        /* We buy 1 pack of water */
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(pack_of_water, 1);

        // suposedCartPrice is 2.5€ 
        double SuposedCartPrice = (2.5);
        double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

        //We verify that the discount insn't applied
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("one articles bought without a reduced price"); 

        /* We buy a second pack of water */
        cart.addItem(pack_of_water, 1);

        // suposedCartPrice result  4€ 
        SuposedCartPrice = (4);
        RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

        //then we check that the discount has been applied 
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("two articles bought for a reduced price"); 


    }


//------------ Five for amount ---------------//
    @Test
    public void FiveForAmount_Test()
    {
        SupermarketCatalog catalog = new FakeCatalog();
        Product Orange_Juice= new Product("Orange Juice ", ProductUnit.Each);
        catalog.addProduct(Orange_Juice, 2.00);        

        // We have a special offer of 25% discount
        // on Orange_Juice product only if we buy at least 5 article 
        // buy  on the catalog
        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(new FiveForAmount(7.5), Orange_Juice);


        // We buy 3 bottle of OrangeJuice 
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(Orange_Juice, 3);

        // supposedCartPrice result 6€
        double SuposedCartPrice =  2*3;
        double RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

        //then we check if the discount is applied
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("Three articles bought at normal price"); 

        // We buy 2 more bottle of OrangeJuice 
        cart.addItem(Orange_Juice, 2);

        // supposedCartPrice result 7.50€
        SuposedCartPrice =  2*5 - ((2*5)*0.25) ;
        RealCartPrice = teller.checksOutArticlesFrom(cart).getTotalPrice();

        //then we check if the discount is applied
        assertThat(RealCartPrice).isEqualTo(SuposedCartPrice).as("Five article bought with 25% discount"); 
    }

}

