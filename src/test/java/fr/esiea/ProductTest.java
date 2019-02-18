package fr.esiea;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {
    
@Test 
public void getUntit_Product_Test() {

       Product rice_bag = new Product("rice_bag", ProductUnit.Kilo);

    assertThat(rice_bag.getUnit().equals(ProductUnit.Kilo)).isEqualTo(true);
}

}
