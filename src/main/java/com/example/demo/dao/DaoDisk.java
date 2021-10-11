package com.example.demo.dao;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DaoDisk {

    void merge(Disk disk);
    void merge(User user);
    Disk getDisk(Long id);

    Boolean changeAccessToDisk(Long idUser, Long idDisk, boolean isFree);

    ResponseEntity<?> addDiskToUser(Long id, Long idDisk);

    void add(Object obj);

    List<Disk> getListDisksForUser(Long idUser);
}