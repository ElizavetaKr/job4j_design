package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
    @XmlAttribute
    private String clothes;
    @XmlAttribute
    private int price;

    public Product() { }

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
