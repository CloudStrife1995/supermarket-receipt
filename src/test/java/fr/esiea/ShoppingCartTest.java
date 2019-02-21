package fr.esiea;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class ShoppingCartTest 
{

@Test  
    public void ShoppingCart_Test() 
    {
         Product candy_bag= new Product("Umbrella", ProductUnit.Each);
         //we create our cart to add item further;
         ShoppingCart cart = new ShoppingCart();
         //we want buy 5 candy bag for children
        cart.addItemQuantity(candy_bag,5);

        // we create a list 
        Map<Product, Double> items_into_cart = cart.productQuantities();

         assertThat(items_into_cart.containsKey(candy_bag)).isEqualTo(true);

    }
}