package com.pilon.example.item.domain;

import java.util.Set;

public class ItemBuilder {

    private Item item;

    public ItemBuilder() {
        item = new Item();
    }

    public static ItemBuilder newInstance() {
        return new ItemBuilder();
    }

    public ItemBuilder id(long id) {
        item.setId(id);
        return this;
    }

    public ItemBuilder description(String description) {
        item.setDescription(description);
        return this;
    }

    public ItemBuilder images(Set<ItemImage> images) {
        item.setImages(images);
        return this;
    }

    public Item build() {
        return item;
    }

}