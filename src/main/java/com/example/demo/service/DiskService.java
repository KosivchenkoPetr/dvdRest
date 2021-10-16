package com.example.demo.service;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiskService {

    Disk addDisk(Disk disk);

    Disk getDisk(Long id);


    //void merge(Disk disk);
    //void merge(User user);

    Boolean changeAccessToDisk(Long idUser, Long idDisk, boolean isFree);

    ResponseEntity<?> addDiskToUser(Long id, Long idDisk);

    //void add(Object obj);
    List<Disk> getListDisksForUser(Long idUser);

}
