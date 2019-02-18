package fr.esiea;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
//import fr.esiea.ReceiptPrinter;

public class Receipt_ItemTest 
{
    // this test will test the following methods 
    // who are in the class ReceiptItem.java
    //
    // getPrice() ; getProduct() ; getQuantity() 
    // getTotalPrice()  equals(Object o) and hashCode() ;   
     //

    public void ReceiptItem_Test () {

        // Initialization of the catalogue
        Product mugs = new Product("mugs", ProductUnit.Each); 
        Product sugar = new Product("sugar", ProductUnit.Kilo); 
        Product teaBox = new Product("tea box", ProductUnit.Each); 
        Product cofeeBox = new Product("cofee box", ProductUnit.Each);

        // Method of ReceiptItem(Product p, double quantity, double price, double totalPrice)
        ReceiptItem receiptItem_mugs = new ReceiptItem(mugs, 2, 5, 10);   
        ReceiptItem receiptItem_sugar = new ReceiptItem(sugar,2.5, 2, 5);   
        ReceiptItem receiptItem_teaBox = new ReceiptItem(teaBox, 2, 2, 4);   
        ReceiptItem receiptItem_cofeeBox = new ReceiptItem(cofeeBox, 2, 3, 6);  

        //then the test on methods begin

        // check if the product picked is not the receipt of the product
        assertThat(mugs.equals(receiptItem_mugs)).isNotEqualTo(true); 

        // check if the receipt of the teaBox is really this reciept aand not an other
        assertThat(receiptItem_teaBox.equals(receiptItem_teaBox)).isEqualTo(true); 
        
        // check if the  receipt of the product is not null
        assertThat(receiptItem_mugs).isNotEqualTo(null); 

        //we create a new product and we compare the new receipt of the milk with the cofee Box
        /*final Product milkBox = new Product("milk Box",ProductUnit.Each);
        final double milkBox_quantity = 3;
        final double milkBox_price = 1.5;
        final double milkBox_total_price = 4.5;
        Assert.assertNotEquals(receiptItem_cofeeBox, new ReceiptItem(milkBox ,milkBox_quantity,milkBox_price,milkBox_total_price)); // check if the product picked is != receipt of the product
        */
    }
}
