package com.pilon.example.item.domain;

public class ItemImageBuilder {
    private ItemImage itemImage;

    public ItemImageBuilder() {
        itemImage = new ItemImage();
    }

    public static ItemImageBuilder newInstance() {
        return new ItemImageBuilder();
    }

    public ItemImageBuilder id(long id) {
        itemImage.setId(id);
        return this;
    }

    public ItemImageBuilder itemId(long itemId) {
        itemImage.setItemId(itemId);
        return this;
    }

    public ItemImageBuilder url(String url) {
        itemImage.setUrl(url);
        return this;
    }

    public ItemImage build() {
        return itemImage;
    }

}