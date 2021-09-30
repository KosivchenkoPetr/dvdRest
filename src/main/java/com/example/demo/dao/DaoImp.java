package com.example.demo.dao;

import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import javax.persistence.*;

@Repository
public class DaoImp extends BaseDao implements Dao {
    @PersistenceContext
    private EntityManager em;


    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override

    public Credential findCredentialByName(String username) {
        TypedQuery<Credential> list;
        list = em.createQuery("SELECT c from Credential c WHERE c.login = :username", Credential.class)
                .setParameter("username", username);
        return list.getSingleResult();
    }

    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsOfCurrentOwner(Long id) {
        TypedQuery<TakenItem> list;
        User user = getUser(id);
        list = em.createQuery("SELECT c from TakenItem c WHERE c.currentOwner = :currentOwner", TakenItem.class)
                .setParameter("currentOwner", user);
        return list.getResultList();
    }

    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsOfMaster(Long id) {
        TypedQuery<TakenItem> list;
        User user = getUser(id);
        list = em.createQuery("SELECT c from TakenItem c WHERE c.disk.master = :master", TakenItem.class)
                .setParameter("master", user);
        return list.getResultList();
    }

    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsFree() {
        TypedQuery<TakenItem> list = em
                .createQuery("SELECT c from TakenItem c WHERE c.isFree = :isFree", TakenItem.class)
                .setParameter("isFree", true);

        return list.getResultList();
    }

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
    @Transactional(readOnly = true)
    public Boolean findLoginInBd(String login) {
        return findCredentialByName(login) != null;
    }

    public Boolean userHaveDisk(User user, Long IdDisk) {
        return user.getListDisk().stream().map(Disk::getId).anyMatch(e -> e.equals(IdDisk));
    }

    @Transactional(readOnly = true)
    public Object nameLoginClientOwner(Long idUserOwner) {
        String name = null;
        User user = getUser(idUserOwner);
        if (user != null) {
            name = user.getCredential().getLogin();
        }
        return name;
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
        List<Disk> list;
        User user = getUser(idUser);
        list = user.getListDisk();
        List<TakenItem> takenItems = (List<TakenItem>)getAllTakenItemsOfCurrentOwner(idUser);
        for (TakenItem t : takenItems) {
            list.add(t.getDisk());
        }
        return list;
    }

}