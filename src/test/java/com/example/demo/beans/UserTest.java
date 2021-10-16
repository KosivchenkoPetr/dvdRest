//package com.example.demo.beans;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserTest {
//
//    @Test
//    void getListDisk() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1");
//
//        assertTrue(master.getListDisk().isEmpty()); //no disks, empty
//
//        master.setDisk(disk);
//        assertEquals(disk , master.getListDisk().get(0));
//    }
//
//    @Test
//    void setDisk() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1");
//        assertTrue(master.getListDisk().isEmpty()); //no disks, empty
//
//        master.setDisk(disk);
//        assertFalse(master.getListDisk().isEmpty());
//    }
//
//    @Test
//    void getName() {
//        User master = new User("master");
//        assertEquals("master" , master.getName());
//    }
//
//    @Test
//    void setName() {
//        User master = new User("master");
//        master.setName("testUser");
//        assertEquals("testUser" , master.getName());
//    }
//
//    @Test
//    void getCredential() {
//        User master = new User("master");
//        Credential credential = new Credential();
//        assertEquals(credential.toString(), master.getCredential().toString());
//
//    }
//
//}