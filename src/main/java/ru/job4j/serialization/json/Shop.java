package ru.job4j.serialization.json;

import java.util.Arrays;

public class Shop {
        private final boolean open;
        private final int revenue;
        private final Product product;
        private final String[] sellers;

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
}
