package src;

import java.util.Objects;

public class Product {
    private String productName;
    private double productPrice;
    private double markdown;

    public double getMarkdown() {
        return markdown;
    }

    public void setMarkdown(double markdown) {
        this.markdown = markdown;
    }

    public Product(String productName, double productPrice, double markdown) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.markdown = markdown;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
