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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilon.example.item.domain.Item;
import com.pilon.example.item.domain.ItemBuilder;
import com.pilon.example.item.domain.ItemImage;
import com.pilon.example.item.domain.ItemImageBuilder;
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
        String imageDescription = "Amazon Echo (3rd Gen) - Twilight Blue";
        String imageUrl = "https://images-na.ssl-images-amazon.com/images/I/61gTLgYwFCL._AC_SL1000_.jpg";

        ItemImage itemImage = ItemImageBuilder
            .newInstance()
            .id(1)
            .itemId(1)
            .url(imageUrl)
            .build();
        Set<ItemImage> itemImages = new HashSet<>();
        itemImages.add(itemImage);

        Item item = ItemBuilder
            .newInstance()
            .id(1)
            .description(imageDescription)
            .images(itemImages)
            .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String itemJSON = objectMapper.writeValueAsString(item);

        Mockito
            .when(itemRepository.findById(1))
            .thenReturn(Optional.of(item));

        mockMvc.perform(
            MockMvcRequestBuilders.get("/item/1")
        )
        .andExpect(status().isOk())
        .andExpect(content().string(itemJSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.description").exists())
        .andExpect(jsonPath("$.description").value(imageDescription))
        .andExpect(jsonPath("$.images").exists())
        .andExpect(jsonPath("$.images[0]").exists())
        .andExpect(jsonPath("$.images[0].id").exists())
        .andExpect(jsonPath("$.images[0].id").value(1))
        .andExpect(jsonPath("$.images[0].itemId").exists())
        .andExpect(jsonPath("$.images[0].itemId").value(1))
        .andExpect(jsonPath("$.images[0].url").exists())
        .andExpect(jsonPath("$.images[0].url").value(imageUrl));
    }

    @Test
    public void getItemsTest() throws Exception {
        List<Item> items = new ArrayList<>();

        String imageDescription1 = "Amazon Echo (3rd Gen) - Twilight Blue";
        String imageUrl1 = "https://images-na.ssl-images-amazon.com/images/I/61gTLgYwFCL._AC_SL1000_.jpg";
        String imageDescription2 = "Introducing Echo Studio - High-fidelity smart speaker with 3D audio and Alexa";
        String imageUrl2 = "https://images-na.ssl-images-amazon.com/images/I/61FPZMMCqzL._AC_SL1000_.jpg";

        items.add(ItemBuilder
            .newInstance()
            .id(1)
            .description(imageDescription1)
            .images(new HashSet<>(Arrays.asList(
                ItemImageBuilder
                    .newInstance()
                    .id(1)
                    .itemId(1)
                    .url(imageUrl1)
                    .build())))
            .build());

        items.add(ItemBuilder
            .newInstance()
            .id(2)
            .description(imageDescription2)
            .images(new HashSet<>(Arrays.asList(
                ItemImageBuilder
                    .newInstance()
                    .id(2)
                    .itemId(2)
                    .url(imageUrl2)
                    .build())))
            .build());

        Mockito
            .when(itemRepository.findAll())
            .thenReturn(items);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/item")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$.[0]").exists())
        .andExpect(jsonPath("$.[0].id").exists())
        .andExpect(jsonPath("$.[0].description").exists())
        .andExpect(jsonPath("$.[0].images").exists())
        .andExpect(jsonPath("$.[0].images.length()").value(1))
        .andExpect(jsonPath("$.[1].id").exists())
        .andExpect(jsonPath("$.[1].description").exists())
        .andExpect(jsonPath("$.[1].images").exists())
        .andExpect(jsonPath("$.[1].images.length()").value(1))
        ;
    }

    @Test
    public void createItemTest() throws Exception {
        Item item = ItemBuilder
            .newInstance()
            .id(3)
            .description("Echo Show 8")
            // .images(itemImages)
            .build();
        
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