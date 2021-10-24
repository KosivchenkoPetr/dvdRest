package com.example.demo.service;

import com.example.demo.beans.Disk;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiskService {

    Disk addDisk(Disk disk);

    Disk getDisk(Long id);

    Boolean changeAccessToDisk(Long idUser, Long idDisk, boolean isFree);

    ResponseEntity<?> addDiskToUser(Long id, Long idDisk);

    List<Disk> getListDisksForUser(Long idUser);

}
