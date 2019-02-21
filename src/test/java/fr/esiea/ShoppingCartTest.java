package fr.esiea;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class ShoppingCartTest 
{

@Test  

    public void addItemQuantity_Test()
    {
        Product candy_bag= new Product("candy bag", ProductUnit.Each);
         //we create our cart to add item further;
         ShoppingCart cart = new ShoppingCart();
         //we want buy 5 candy bag for children
        cart.addItemQuantity(candy_bag,5);

        Map<Product, Double> items_quantities = cart.productQuantities();
        double final_product_quantity = items_quantities.get(candy_bag);

        //we caompare to the quantity in the cart 
        double supposed_product_quantity = 5 ;

        //test of the if else condition of the method addItemQuantity
        assertThat(items_quantities.containsKey(candy_bag)).isEqualTo(true);
         //test of the if else condition of the method addItemQuantity
        assertThat(supposed_product_quantity).isEqualTo(5);
      

    }
} 