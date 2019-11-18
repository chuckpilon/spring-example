package com.pilon.example.item.rest;

import java.util.Optional;

import com.pilon.example.item.domain.ItemImage;
import com.pilon.example.item.repository.ItemImageRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@Controller
@ResponseBody
@RequestMapping(value = {"/itemimage"})
public class ItemImageController {

    @Autowired
    ItemImageRepository itemImageRepository;

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public Optional<ItemImage> getItemImage(@PathVariable("id") long id) {
        Optional<ItemImage> itemImage = itemImageRepository.findById(id);
        if (!itemImage.isPresent()) {
            throw new ResourceNotFoundException(String.format("Item image %d not found", id));
        }

        return itemImage;
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<ItemImage> getItemImages() {
        Iterable<ItemImage> itemImages = itemImageRepository.findAll();
        if (!itemImages.iterator().hasNext()) {
            throw new ResourceNotFoundException("No item images found");
        }

        return itemImages;
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemImage createItemImage(@RequestBody ItemImage itemImage) {
        return itemImageRepository.save(itemImage);
    }

}