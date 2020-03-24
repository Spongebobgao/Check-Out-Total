package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.CheckOut;
import src.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOutTest {

    CheckOut checkOut;
    @BeforeEach
    void setCheckOut(){
        checkOut = new CheckOut();
    }
    @Test
    @DisplayName("Scan one item get the price")
    void scanOneItemReturnThePrice(){
        assertEquals(2.56, checkOut.getItemPrice("soup",1));
    }
    @Test
    @DisplayName("Scan one item get the current total")
    void scanOneItemReturnTheCurrentTotal(){
        double total = 2.56;
        assertEquals(total, checkOut.getItemPrice("soup",1));
    }
    @Test
    @DisplayName("Scan one item with weight get the current total")
    void scanOneItemWithWeightReturnTheCurrentTotal(){
        assertEquals(1.8,checkOut.getItemPrice("banana",3));
    }
    @Test
    @DisplayName("Scan one item with markdown get the current total")
    void scanOneItemWithMarkDown(){
        assertEquals(4.52, checkOut.getItemPrice("soup", 2));
        assertEquals(6.32, checkOut.getItemPrice("banana",3));
    }
    @Test
    @DisplayName("Void one item  get the current total")
    void voidOneItemReturnTotal(){
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        assertEquals(2.26,checkOut.voidOneItem("soup",1));
    }
    @Test
    @DisplayName("Void one item with weight get the current total")
    void voidOneItemWithWeightReturnTotal(){
        assertEquals(1.8, checkOut.getItemPrice("banana", 3));
        assertEquals(4.8, checkOut.getItemPrice("banana", 5));
        assertEquals(1.8,checkOut.voidOneItem("banana",5));
        List<Product> allPurchasedProducts = new ArrayList<>();
        allPurchasedProducts.add(new Product("banana",1.8,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Scan one item imply bogo and get the current total")
    void testBuyOneGetOneFree(){
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        List<Product> allPurchasedProducts = new ArrayList<>();
        allPurchasedProducts.add(new Product("soup",2.26,0));
        allPurchasedProducts.add(new Product("soup",0,0));
        allPurchasedProducts.add(new Product("soup",2.26,0));
        allPurchasedProducts.add(new Product("soup",0,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Expand BOGO buy 4 get 2 at 50% off")
    void testBuyFourGetTwoAt50PercentOff(){
        assertEquals(1.2, checkOut.getItemPrice("pasta", 1));
        assertEquals(2.4, checkOut.getItemPrice("pasta", 1));
        assertEquals(3.6, checkOut.getItemPrice("pasta", 1));
        assertEquals(4.8, checkOut.getItemPrice("pasta", 1));
        assertEquals(5.4, checkOut.getItemPrice("pasta", 1));
        assertEquals(6, checkOut.getItemPrice("pasta", 1));
        assertEquals(7.2, checkOut.getItemPrice("pasta", 1));
        List<Product> allPurchasedProducts = new ArrayList<>();
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",0.6,0));
        allPurchasedProducts.add(new Product("pasta",0.6,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Buy 3 for $ 3, assume only buy three can get the sale price and only one kind of sale available")
    void testBuyThreeForThree(){
        assertEquals(1.2, checkOut.getItemPrice("pasta", 1));
        assertEquals(2.4, checkOut.getItemPrice("pasta", 1));
        assertEquals(3, checkOut.getItemPrice("pasta", 1));
        assertEquals(4.2, checkOut.getItemPrice("pasta", 1));
        assertEquals(5.4, checkOut.getItemPrice("pasta", 1));
        List<Product> allPurchasedProducts = new ArrayList<>();
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Buy 2 get 1 free, limit 12, assume only one kind of sale available")
    void testBuyTwoGetOneFreeLimit6(){
        assertEquals(4.2, checkOut.getItemPrice("milk", 1));
        assertEquals(8.4, checkOut.getItemPrice("milk", 1));
        assertEquals(8.4, checkOut.getItemPrice("milk", 1));
        assertEquals(12.6, checkOut.getItemPrice("milk", 1));
        assertEquals(16.8, checkOut.getItemPrice("milk", 1));
        assertEquals(16.8, checkOut.getItemPrice("milk", 1));
        assertEquals(21, checkOut.getItemPrice("milk", 1));
        assertEquals(25.2, checkOut.getItemPrice("milk", 1));
        assertEquals(29.4, checkOut.getItemPrice("milk", 1));
        List<Product> allPurchasedProducts = new ArrayList<>();
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",0,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",0,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
}
