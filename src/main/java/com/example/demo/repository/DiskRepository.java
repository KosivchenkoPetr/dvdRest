package com.example.demo.repository;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiskRepository extends JpaRepository<Disk, Long> {


    List<Disk> findByMaster(User user);



}