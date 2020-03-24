package test;

import org.junit.jupiter.api.Test;
import src.CheckOut;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOutTest {
    @Test
    void scanOneItemReturnThePrice(){
        CheckOut checkOut = new CheckOut();
        assertEquals(2.56, checkOut.getItemPrice("soup",1));
    }
    @Test
    void scanOneItemReturnTheCurrentTotal(){
        CheckOut checkOut = new CheckOut();
        double total = 2.56;
        assertEquals(total+2.56, checkOut.getItemPrice("soup",1));
    }
}
