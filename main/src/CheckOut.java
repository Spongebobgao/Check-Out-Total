package src;

public class CheckOut {
    public static double theTotalOfPurchasedPrice=0;
    public double getItemPrice(String productName, int quantity) {
        theTotalOfPurchasedPrice += 2.56;
        return theTotalOfPurchasedPrice;
    }
}
