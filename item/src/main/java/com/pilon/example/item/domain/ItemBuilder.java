package com.pilon.example.item.domain;

import java.util.HashSet;
import java.util.Set;

public class ItemBuilder {

    private Item item;
    private Set<ItemImage> itemImages;

    public ItemBuilder() {
        item = new Item();
        itemImages = new HashSet<ItemImage>();
        item.setImages(itemImages);
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

    public ItemBuilder addImage(long id, long itemId, String url) {
        ItemImage itemImage = new ItemImage(id, itemId, url);
        itemImages.add(itemImage);
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
