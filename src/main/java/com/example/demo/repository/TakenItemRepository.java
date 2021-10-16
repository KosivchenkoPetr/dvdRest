package com.example.demo.repository;

import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakenItemRepository extends JpaRepository<TakenItem, Long> {

    //@Query("SELECT c from TakenItem c WHERE c.currentOwner = ?1")
    List<TakenItem> findByCurrentOwner(User user);

    @Query("SELECT c from TakenItem c WHERE c.disk.master = ?1")
    List<TakenItem> findByMaster(User user);

    //@Query("SELECT c from TakenItem c WHERE c.isFree = ?1")
    List<TakenItem> findByIsFreeTrue();

}