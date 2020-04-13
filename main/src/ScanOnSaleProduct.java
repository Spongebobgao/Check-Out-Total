package src;

import java.util.List;
import java.util.Map;

public class ScanOnSaleProduct {
    public static double getItemPriceWithBuyNItemsGetMAtXOff(String productName, double quantity,
                                                             double originalPrice, double theTotalOfPurchasedPrice, long alreadyPurchasedSameItemCount,
                                                             Map<String, Product> allTheProductsInStore,
                                                             List<Product> allPurchasedProducts) {
        int[] buyNItemsGetMAtXOff = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfOriginalPrice = buyNItemsGetMAtXOff[0];
        int numberOfSalePrice = buyNItemsGetMAtXOff[1];
        int percentOfDiscount = buyNItemsGetMAtXOff[2];
        //if this is true, will get the original price
        if (alreadyPurchasedSameItemCount < numberOfOriginalPrice
                || (alreadyPurchasedSameItemCount % (numberOfOriginalPrice + numberOfSalePrice) >= 0
                && alreadyPurchasedSameItemCount % (numberOfOriginalPrice + numberOfSalePrice) < numberOfOriginalPrice)) {
            //maybe the item has markdown as well as buy N get M at X off
            return getThePriceAfterApplyTheMarkdown(productName, quantity, originalPrice, theTotalOfPurchasedPrice, allTheProductsInStore, allPurchasedProducts);
        } else {
            double onSalePrice = originalPrice - originalPrice * percentOfDiscount / 100.0;
            allPurchasedProducts.add(new Product(productName, onSalePrice * quantity, quantity));
            theTotalOfPurchasedPrice += onSalePrice * quantity;
            return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
        }
    }

    private static double getThePriceAfterApplyTheMarkdown(String productName, double quantity,
                                                           double originalPrice, double theTotalOfPurchasedPrice,
                                                           Map<String, Product> allTheProductsInStore,
                                                           List<Product> allPurchasedProducts) {
        Product onSaleProduct;
        onSaleProduct = new Product(productName, originalPrice * quantity, quantity);
        allPurchasedProducts.add(onSaleProduct);
        theTotalOfPurchasedPrice += originalPrice * quantity;
        return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
    }

    public static double getItemPriceWithBuyNForM(String productName, double quantity,
                                                  double originalPrice, double theTotalOfPurchasedPrice,
                                                  long alreadyPurchasedSameItemCount,
                                                  Map<String, Product> allTheProductsInStore,
                                                  List<Product> allPurchasedProducts) {
        int[] buyNForM = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfNeededItems = buyNForM[0];
        int priceForNItems = buyNForM[1];
        //because alreadyPurchasedSameItemCount starts from 0, so, add 1 to make the condition works as expected
        if (alreadyPurchasedSameItemCount + 1 < numberOfNeededItems || ((alreadyPurchasedSameItemCount + 1) % numberOfNeededItems != 0)) {
            return getThePriceAfterApplyTheMarkdown(productName, quantity, originalPrice, theTotalOfPurchasedPrice, allTheProductsInStore, allPurchasedProducts);

        }
        //else will reset each item price to M/N
        else {
            double differenceAfterApplyTheBuyNForM = priceForNItems - originalPrice * (numberOfNeededItems - 1);
            allPurchasedProducts.add(new Product(productName,originalPrice,quantity));
            double salePrice = Math.round((double) priceForNItems / (double) numberOfNeededItems * 100.0) / 100.0;
            allPurchasedProducts.stream().filter(product -> product.getProductName().equals(productName))
                    .forEach(product -> product.setProductPrice(salePrice));
            theTotalOfPurchasedPrice += differenceAfterApplyTheBuyNForM;
            return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
        }
    }

    public static double getItemPriceWithBuyNGetMFreeLimitX(String productName, double originalPrice, long alreadyPurchasedSameItemCount,
                                                            double quantity, double theTotalOfPurchasedPrice,
                                                            Map<String, Product> allTheProductsInStore,
                                                            List<Product> allPurchasedProducts) {
        int[] buyNGetMFreeLimitX = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfItemsNeedToBuy = buyNGetMFreeLimitX[0];
        int numberOfFreeItems = buyNGetMFreeLimitX[1];
        int limitNumberOfItems = buyNGetMFreeLimitX[2];
        if (alreadyPurchasedSameItemCount < limitNumberOfItems) {
            if ((alreadyPurchasedSameItemCount + 1) % (numberOfItemsNeedToBuy + numberOfFreeItems) == 0) {
                allPurchasedProducts.add(new Product(productName, 0, quantity));
                return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
            }
        }
        return getThePriceAfterApplyTheMarkdown(productName, quantity, originalPrice, theTotalOfPurchasedPrice, allTheProductsInStore, allPurchasedProducts);
    }

    public static double getItemPriceWithBuyNGetMOrLessOff(String productName, double quantity,
                                                           double originalPrice, double theTotalOfPurchasedPrice,
                                                           Map<String, Product> allTheProductsInStore,
                                                           List<Product> allPurchasedProducts) {
        int[] buyNGetMOrLessXOff = allTheProductsInStore.get(productName).getSpecial().getValue();
        int numberOfNeedWeight = buyNGetMOrLessXOff[0];
        int numberOfSaleWeight = buyNGetMOrLessXOff[1];
        int numberOfSaleWeightDiscount = buyNGetMOrLessXOff[2];
        double onSalePrice;
        if (quantity > numberOfNeedWeight) {
            if ((quantity - numberOfNeedWeight) <= numberOfSaleWeight) {
                onSalePrice = originalPrice * numberOfSaleWeightDiscount / 100.0 * (quantity - numberOfNeedWeight) + originalPrice * numberOfNeedWeight;
            } else {
                onSalePrice = originalPrice * numberOfSaleWeightDiscount / 100.0 * numberOfSaleWeight + originalPrice * (quantity - numberOfSaleWeight);
            }
            allPurchasedProducts.add(new Product(productName, onSalePrice,quantity));
            theTotalOfPurchasedPrice += onSalePrice;
            return Math.round(theTotalOfPurchasedPrice * 100.0) / 100.0;
        }
        return getThePriceAfterApplyTheMarkdown(productName, quantity, originalPrice, theTotalOfPurchasedPrice, allTheProductsInStore, allPurchasedProducts);
    }
}


