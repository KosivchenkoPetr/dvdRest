//package com.example.demo.beans;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TakenItemTest {
//
//    @Test
//    void getDisk() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setCurrentOwner(master);
//        takenItem.setDisk(disk);
//
//        assertEquals(disk, takenItem.getDisk());
//    }
//
//    @Test
//    void setDisk() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setCurrentOwner(master);
//        takenItem.setDisk(disk);
//
//        assertEquals(disk, takenItem.getDisk());
//    }
//
//    @Test
//    void getCurrentOwner() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setCurrentOwner(master);
//        takenItem.setDisk(disk);
//
//        assertEquals(master, takenItem.getCurrentOwner());
//    }
//
//    @Test
//    void setCurrentOwner() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setCurrentOwner(master);
//        takenItem.setDisk(disk);
//
//        assertEquals(master, takenItem.getCurrentOwner());
//    }
//
//    @Test
//    void getId() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setCurrentOwner(master);
//        takenItem.setDisk(disk);
//        takenItem.setId(1L);
//        assertEquals(1, takenItem.getId());
//    }
//
//    @Test
//    void setId() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setCurrentOwner(master);
//        takenItem.setDisk(disk);
//        takenItem.setId(1L);
//        assertEquals(1, takenItem.getId());
//    }
//
//    @Test
//    void isFree() {
//        User master = new User("master");
//        Disk disk = new Disk(1L, "disk1", master, master);
//        TakenItem takenItem = new TakenItem();
//        takenItem.setDisk(disk);
//
//        assertTrue(takenItem.isFree());
//
//        takenItem.setCurrentOwner(master);
//        assertFalse(takenItem.isFree());
//    }
//
//}