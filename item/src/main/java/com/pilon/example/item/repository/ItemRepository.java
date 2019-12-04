package com.pilon.example.item.repository;

import com.pilon.example.item.domain.Item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}