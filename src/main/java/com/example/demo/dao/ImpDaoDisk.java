package com.example.demo.dao;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ImpDaoDisk extends BaseDaoDisk implements DaoDisk {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Boolean changeAccessToDisk(Long idUser, Long IdDisk, boolean isFree) {
        User user = getUser(idUser);
        for (Disk d : user.getListDisk()) {
            if (d.getId().equals(IdDisk)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ResponseEntity<?> addDiskToUser(Long id, Long idDisk) {
        User user = getUser(id);
        Disk disk = getDisk(idDisk);
        if (user != null && disk != null) {
            disk.setMaster(user);
            user.setDisk(disk);
            merge(user);
        }
        User result = getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Override
    public List<Disk> getListDisksForUser(Long idUser) {
        return null;
    }


}