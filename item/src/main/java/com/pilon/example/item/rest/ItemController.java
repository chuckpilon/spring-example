package com.pilon.example.item.rest;

import java.util.Optional;

import com.pilon.example.item.domain.Item;
import com.pilon.example.item.integration.ItemGateway;
import com.pilon.example.item.repository.ItemRepository;
import com.pilon.example.item.repository.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/item"}, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemGateway itemGateway;

    // @ExceptionHandler(ResourceNotFoundException.class)
    // public String handleResourceNotFoundException(ResourceNotFoundException e) {
    //     return e.getMessage();
    // }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public Item getItem(@PathVariable("id") long id) throws ResourceNotFoundException {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException(String.format("Item %d not found", id));
        }

        return item.get();
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<Item> getItems() throws ResourceNotFoundException {
        Iterable<Item> items = itemRepository.findAll();
        if (!items.iterator().hasNext()) {
            throw new ResourceNotFoundException("No items found");
        }

        return items;
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        Item newItem = itemRepository.save(item);
        itemGateway.publish(newItem);
        return newItem;
    }

}