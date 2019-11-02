package com.pilon.example.item.rest;

import com.pilon.example.item.domain.Item;
import com.pilon.example.item.repository.ItemRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ResponseBody
@RequestMapping(value = {"/items"})
public class ItemsController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Item> getItems() {
        Iterable<Item> items = itemRepository.findAll();
        if (!items.iterator().hasNext()) {
            throw new ResourceNotFoundException("No items found");
        }

        return items;
    }

}