package com.example.demo.service;

import com.example.demo.beans.TakenItem;

import java.util.List;

public interface TakenItemService {

    TakenItem getTakenItem(Long id);

    List<TakenItem> getAllTakenItems();

    List<?> getAllTakenItemsOfCurrentOwner(Long id);

    List<?> getAllTakenItemsOfMaster(Long id);

    List<?> getAllTakenItemsFree();

    void add(TakenItem takenItem);
}
