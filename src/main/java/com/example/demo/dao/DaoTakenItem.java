package com.example.demo.dao;

import com.example.demo.beans.TakenItem;

import java.util.List;

public interface DaoTakenItem {

    void merge(TakenItem takenItem);

    TakenItem getTakenItem(Long id);

    List<TakenItem> getAllTakenItems();

    List<?> getAllTakenItemsOfCurrentOwner(Long id);

    List<?> getAllTakenItemsOfMaster(Long id);

    List<?> getAllTakenItemsFree();

    void add(Object obj);

}