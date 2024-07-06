package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvertToJson {
    public static void main(String[] args) {
        final Shop shop = new Shop(true, 150,
                new Product("cap", 2),
                new String[]{"Anna", "Nina"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(shop));

        final String shopJson =
                "{"
                        + "\"open\":true,"
                        + "\"revenue\":150,"
                        + "\"product\":"
                        + "{"
                        + "\"clothes\":\"cap\","
                        + "\"price\":2"
                        + "},"
                        + "\"sellers\":"
                        + "[\"Anna\",\"Nina\"]"
                        + "}";
        
        final Shop shopMod = gson.fromJson(shopJson, Shop.class);
        System.out.println(shopMod);
    }
}
