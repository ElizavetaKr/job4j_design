package ru.job4j.serialization.json;

public class Product {
    private final String clothes;
    private final int price;

    public Product(String clothes, int price) {
        this.clothes = clothes;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{"
                + "clothes='" + clothes + '\''
                + ", price=" + price
                + '}';
    }
}
