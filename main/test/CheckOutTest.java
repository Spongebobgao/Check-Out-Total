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
}
