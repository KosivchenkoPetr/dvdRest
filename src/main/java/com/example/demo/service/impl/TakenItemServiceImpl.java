package com.example.demo.service.impl;

import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import com.example.demo.repository.TakenItemRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TakenItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TakenItemServiceImpl implements TakenItemService {
    @Autowired
    private TakenItemRepository takenItemRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TakenItem getTakenItem(Long id) {
        return takenItemRepository.getById(id);
    }

    @Override
    public List<TakenItem> getAllTakenItems() {
        return takenItemRepository.findAll();
    }

    @Override
    public List<?> getAllTakenItemsOfCurrentOwner(Long id) {
        User user = userRepository.getById(id);
        return takenItemRepository.findByCurrentOwner(user);
    }

    @Override
    public List<?> getAllTakenItemsOfMaster(Long id) {
        User user = userRepository.getById(id);
        return takenItemRepository.findByMaster(user);
    }

    @Override
    public List<?> getAllTakenItemsFree() {
        return takenItemRepository.findByIsFreeTrue();
    }

    @Override
    public void add(TakenItem takenItem) {
        takenItemRepository.saveAndFlush(takenItem);
    }
}
