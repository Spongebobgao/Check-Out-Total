package src;

import java.util.Objects;

public class Product {
    private String productName;
    private double productPrice;
    private double markdown;
    private double quantity;
    private Special special;

    public Product(String productName, double productPrice, double markdown, Special special) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.markdown = markdown;
        this.special = special;
    }

    public Product(String productName, double productPrice, double quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getMarkdown() {
        return markdown;
    }

    public double getQuantity() {
        return quantity;
    }

    public Special getSpecial() {
        return special;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.productPrice, productPrice) == 0 &&
                Double.compare(product.quantity, quantity) == 0 &&
                Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, quantity);
    }
}
