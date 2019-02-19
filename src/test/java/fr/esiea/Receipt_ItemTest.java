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

    @Test
    public void ReceiptItem_Test () {

        // Initialization of the catalogue
        Product mugs = new Product("mugs", ProductUnit.Each); 
        Product sugar = new Product("sugar", ProductUnit.Kilo); 
        Product teaBox = new Product("tea box", ProductUnit.Each); 
        Product coffeeBox = new Product("cofee box", ProductUnit.Each);

        // Method of ReceiptItem(Product p, double quantity, double price, double totalPrice)
       
       
        ReceiptItem receiptItem_mugs = new ReceiptItem(mugs, 4, 5, 20);   
        ReceiptItem receiptItem_sugar = new ReceiptItem(sugar,2.5, 2, 5);   
        ReceiptItem receiptItem_teaBox1 = new ReceiptItem(teaBox, 3, 2, 4);     
        ReceiptItem receiptItem_teaBox2 = new ReceiptItem(teaBox, 3, 2, 4);
        ReceiptItem receiptItem_coffeeBox = new ReceiptItem(coffeeBox, 2, 3, 6);
               
        //then the test on methods begin
        

        // check if the product picked is not the receipt of the product
        assertThat(mugs.equals(receiptItem_mugs)).isNotEqualTo(true); 

        // Check the methods equals
        assertThat(receiptItem_teaBox1.equals(receiptItem_teaBox2)).isEqualTo(true);
        assertThat(receiptItem_teaBox1.equals(receiptItem_coffeeBox)).isNotEqualTo(true);

        // check if the  receipt of the product is not null
        assertThat(receiptItem_mugs).isNotEqualTo(null);


        // test on the method  getPrice
         assertThat(receiptItem_mugs.getPrice()).isEqualTo(5);

        // test on the method  getProduct
         assertThat(receiptItem_mugs.getProduct()).isEqualTo(mugs);

         // test on the method  getQuantity
         assertThat(receiptItem_mugs.getQuantity()).isEqualTo(4);

        // test on the method  getTotalPrice
         assertThat(receiptItem_mugs.getTotalPrice()).isEqualTo(20);

                 
    }
    
}
