package com.pilon.example.item.repository;

import java.util.List;
import java.util.Optional;

import com.pilon.example.item.domain.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findById(long id);
    List<Item> findAll();
}