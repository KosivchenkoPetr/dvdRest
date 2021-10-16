package com.example.demo.service.impl;


import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import com.example.demo.repository.DiskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskRepository diskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Disk addDisk(Disk disk) {
        return diskRepository.saveAndFlush(disk);
    }

    @Override
    public Disk getDisk(Long id) {
        return diskRepository.getById(id);
    }

    @Override
    public Boolean changeAccessToDisk(Long idUser, Long idDisk, boolean isFree) {
        User user = userRepository.getById(idUser);
        for (Disk d : user.getListDisk()) {
            if (d.getId().equals(idDisk)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<?> addDiskToUser(Long id, Long idDisk) {
        User user = userRepository.getById(id);
        Disk disk = getDisk(idDisk);
        if (disk != null) {
            disk.setMaster(user);
            user.setDisk(disk);
        }
        User result = userRepository.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public List<Disk> getListDisksForUser(Long idUser) {
        User user = userRepository.getById(idUser);
        return diskRepository.findByMaster(user);
    }
}
