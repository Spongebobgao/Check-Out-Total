package test;

import org.junit.jupiter.api.Test;
import src.CheckOutTotal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOutTotalTest {
    @Test
    void scanOneItemReturnThePrice(){
        CheckOutTotal checkOutTotal = new CheckOutTotal();
        assertEquals(2.56, checkOutTotal.getItemPrice("soup",1));
    }
}
