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

}