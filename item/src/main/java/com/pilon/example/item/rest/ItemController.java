package com.pilon.example.item.rest;

import java.util.Optional;

import com.pilon.example.item.domain.Item;
import com.pilon.example.item.repository.ItemRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@Controller
@ResponseBody
@RequestMapping(value = {"/item"})
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(method=RequestMethod.GET)
    public Optional<Item> getItem(@RequestParam(value="id") Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException(String.format("Item %ld not found", id));
        }

        return item;
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}