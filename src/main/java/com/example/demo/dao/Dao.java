package com.example.demo.dao;
import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface Dao {
    void merge(Disk disk);

    void merge(User user);

    void merge(TakenItem takenItem);

    TakenItem getTakenItem(Long id);

    Disk getDisk(Long id);

    User getUser(Long id);


    Credential findCredentialByName(String username);

    void add(Object obj);

    List<TakenItem> getAllTakenItems();

    Boolean findLoginInBd(String login);

    Boolean changeAccessToDisk(Long idUser, Long idDisk, boolean isFree);

    List<?> getAllTakenItemsOfCurrentOwner(Long id);

    List<?> getAllTakenItemsOfMaster(Long id);

    List<?> getAllTakenItemsFree();

    ResponseEntity<?> addDiskToUser(Long id, Long idDisk);

    List<Disk> getListDisksForUser(Long idUser);
}