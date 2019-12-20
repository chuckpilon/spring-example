package com.pilon.example.item.webmvc;

import java.util.Optional;

import com.pilon.example.item.domain.Item;
import com.pilon.example.item.repository.ItemRepository;
import com.pilon.example.item.repository.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/item/display"})
public class WebMVCController {

    @Autowired
    ItemRepository itemRepository;

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException e) {
        return "item-not-found";
    }
    
    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public String getItem(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            throw new ResourceNotFoundException(String.format("Item %d not found", id));
        }

        model.addAttribute("item", item.get());
        return "item";
    }
}
