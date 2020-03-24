package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.CheckOut;

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
    void scanOneItemWithMarkDown(){
        assertEquals(4.52, checkOut.getItemPrice("soup", 2));
        assertEquals(6.32, checkOut.getItemPrice("banana",3));
    }
    @Test
    void voidOneItemReturnTotal(){
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        assertEquals(2.26,checkOut.voidOneItem("soup",1));
    }
    @Test
    void voidOneItemWithWeightReturnTotal(){
        assertEquals(1.8, checkOut.getItemPrice("banana", 3));
        assertEquals(4.8, checkOut.getItemPrice("banana", 5));
        assertEquals(1.8,checkOut.voidOneItem("banana",5));
    }
}
