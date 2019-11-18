package com.pilon.example.item.repository;

import java.util.List;
import java.util.Optional;

import com.pilon.example.item.domain.ItemImage;

import org.springframework.data.repository.CrudRepository;

public interface ItemImageRepository extends CrudRepository<ItemImage, Long> {
    Optional<ItemImage> findById(long id);
    List<ItemImage> findAll();
}