package fr.esiea;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ReceiptTest 

{


	@Test
	public void Add_get_Methods_Test () 
	{

		// Creating products, discounts and a new receipt for testing
		Product pack_of_beer = new Product("pack of beer ", ProductUnit.Each);
		Product potato = new Product("pack of beer ", ProductUnit.Kilo);
		Discount discount_on_pack_of_beer = new Discount(pack_of_beer, "Discount offer on pack of beer",3);
		Discount discount_on_potato = new Discount(potato, "Discount offer on potato",0.25);
		Receipt receipt = new Receipt();

		// Test of addProduct() and getItems()
		receipt.addProduct(pack_of_beer, 5, 2,10);
		receipt.addProduct(potato, 2.5, 0.5, 1.25);

		ReceiptItem receiptItem_pack_of_beer = new ReceiptItem(pack_of_beer, 5, 2, 10);
		ReceiptItem receiptItem_potato = new ReceiptItem(potato, 2.5, 0.5, 1.25);

		List<ReceiptItem> items = receipt.getItems();
		assertThat(items.size()).isEqualTo(2);
		assertThat(items.get(0).equals(receiptItem_pack_of_beer)).isEqualTo(true);
		assertThat(items.get(1).equals(receiptItem_potato)).isEqualTo(true);

		// Test of addDiscount() and getDiscounts()
		receipt.addDiscount(discount_on_pack_of_beer);
		receipt.addDiscount(discount_on_potato);
		
		List<Discount> discounts = receipt.getDiscounts();

		assertThat(discounts.get(0).getProduct()).isEqualTo(pack_of_beer);
		assertThat(discounts.get(0).getDiscountAmount()).isEqualTo(3);
		assertThat(discounts.get(0).getDescription()).isEqualTo("Discount offer on pack of beer");

		assertThat(discounts.get(1).getProduct()).isEqualTo(potato);
		assertThat(discounts.get(1).getDiscountAmount()).isEqualTo(0.25);
		assertThat(discounts.get(1).getDescription()).isEqualTo("Discount offer on potato");
	}

	@Test
		public void getTotalPrice_Test () 
	{
		// Creating products, discounts and a new receipt for testing
		Product pack_of_beer = new Product("pack of beer ", ProductUnit.Each);
		Product potato = new Product("pack of beer ", ProductUnit.Kilo);
		Discount discount_on_pack_of_beer = new Discount(pack_of_beer, "Discount offer on pack of beer",3);
		Discount discount_on_potato = new Discount(potato, "Discount offer on potato",0.25);
		Receipt receipt = new Receipt();

		// Add items and discounts while making sure etTotalPrice() gives the right price everytime
		receipt.addProduct(pack_of_beer, 5, 2,10);
		assertThat(receipt.getTotalPrice()).isEqualTo(10);
		receipt.addProduct(potato, 2.5, 0.5, 1.25);
		assertThat(receipt.getTotalPrice()).isEqualTo(11.25);
		receipt.addDiscount(discount_on_pack_of_beer);
		assertThat(receipt.getTotalPrice()).isEqualTo(8.25);
		receipt.addDiscount(discount_on_potato);
		assertThat(receipt.getTotalPrice()).isEqualTo(8);
	}
}