package ru.job4j.serialization.json;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shop {
    @XmlAttribute
    private boolean open;
    @XmlAttribute
    private int revenue;
    private Product product;

    @XmlElementWrapper(name = "sellers")
    @XmlElement(name = "seller")
    private String[] sellers;

    public Shop() { }

    public Shop(boolean open, int revenue, Product product, String[] sellers) {
        this.open = open;
        this.revenue = revenue;
        this.product = product;
        this.sellers = sellers;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "open=" + open
                + ", revenue=" + revenue
                + ", product=" + product
                + ", sellers=" + Arrays.toString(sellers)
                + '}';
    }
    public static void main(String[] args) throws JAXBException {

        final Shop shop = new Shop(false, 150, new Product("cap", 2),
                new String[]{"Anna", "Nina"});

        JAXBContext context = JAXBContext.newInstance(Shop.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(shop, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Shop result = (Shop) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
