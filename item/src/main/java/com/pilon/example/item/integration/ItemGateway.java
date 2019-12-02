package com.pilon.example.item.integration;

import com.pilon.example.item.domain.Item;

public interface ItemGateway {

    public void publish(Item item);

}