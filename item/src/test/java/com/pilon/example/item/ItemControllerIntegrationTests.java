package com.pilon.example.item;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilon.example.item.domain.Item;
import com.pilon.example.item.repository.ItemRepository;
import com.pilon.example.item.rest.ItemController;

@RunWith(SpringJUnit4ClassRunner.class)
public class ItemControllerIntegrationTests {

    @Mock
    ItemRepository itemRepository;
    
    @InjectMocks
    ItemController itemController;
    
    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = standaloneSetup(itemController).build();
    }

    @Test
    public void getItemTest() throws Exception {
        Item item = new Item(1, "Amazon Echo (3rd Gen) - Twilight Blue");

        ObjectMapper objectMapper = new ObjectMapper();
        String itemJSON = objectMapper.writeValueAsString(item);

        Mockito
            .when(itemRepository.findById(1))
            .thenReturn(Optional.of(item));

        mockMvc.perform(
            MockMvcRequestBuilders.get("/item/1")
        )
        .andExpect(status().isOk())
        .andExpect(content().string(itemJSON));
    }

    @Test
    public void createItemTest() throws Exception {
        Item item = new Item(3, "Echo Show 8");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJSON = objectMapper.writeValueAsString(item);

        Mockito
            .when(itemRepository.save(any(Item.class)))
            .thenReturn(item);

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJSON)
        )
        .andExpect(status().isCreated())
        .andExpect(content().string(itemJSON));
    }

}