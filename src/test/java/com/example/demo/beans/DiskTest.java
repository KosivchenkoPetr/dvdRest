//package com.example.demo.beans;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DiskTest {
//
//    @Test
//    void getId() {
//        Disk disk = new Disk(1L, "disk1");
//        assertEquals(1, disk.getId());
//    }
//
//    @Test
//    void getName() {
//        Disk disk = new Disk(1L, "disk1");
//        assertEquals("disk1", disk.getName());
//    }
//
//    @Test
//    void setName() {
//        Disk disk = new Disk(1L, "disk1");
//        disk.setName("disk2");
//        assertEquals("disk2", disk.getName());
//    }
//
//    @Test
//    void testToString() {
//        User currentOwner = new User("currentOwner");
//        User master = new User("master");
//        Disk disk = new Disk(1, "disk1", master, currentOwner);
//        assertEquals("Disk [id=1, name=disk1, master=master, currentOwner=currentOwner]", disk.toString());
//    }
//
//    @Test
//    void testEquals() {
//        User master = new User("master");
//        User masterOther = new User("masterOther");
//        User currentOwner = new User("currentOwner");
//        User currentOwnerOther = new User("currentOwnerOther");
//        Disk disk = new Disk(1, "disk1", master, currentOwner);
//        Disk diskOther = new Disk(1, "disk1", master, currentOwner);
//        Disk diskOtherId = new Disk(2, "disk1", master, currentOwner);
//        Disk diskOtherName = new Disk(1, "disk2", master, currentOwner);
//        Disk diskOtherMaster = new Disk(1, "disk1", masterOther, currentOwner);
//        Disk diskOtherOwner = new Disk(1, "disk1", master, currentOwnerOther);
//
//        assertEquals(disk, disk);
//        assertEquals(disk, diskOther);
//        assertNotEquals(null, disk);
//        assertNotEquals(disk, diskOtherId);
//        assertNotEquals(disk, diskOtherName);
//        assertNotEquals(disk, diskOtherMaster);
//        assertNotEquals(disk, diskOtherOwner);
//
//    }
//
//    @Test
//    void getMaster() {
//        User master = new User("master");
//        User masterOther = new User("masterOther");
//        User currentOwner = new User("currentOwner");
//        Disk disk = new Disk(1, "disk1", master, currentOwner);
//        assertEquals(master, disk.getMaster());
//        assertNotEquals(masterOther, disk.getMaster());
//    }
//
//    @Test
//    void setMaster() {
//        User master = new User("master");
//        User masterOther = new User("masterOther");
//        User currentOwner = new User("currentOwner");
//        Disk disk = new Disk(1, "disk1", master, currentOwner);
//        disk.setMaster(masterOther);
//        assertEquals(masterOther, disk.getMaster());
//        assertNotEquals(master, disk.getMaster());
//    }
//
//    @Test
//    void getCurrentOwner() {
//        User master = new User("master");
//        User currentOwner = new User("currentOwner");
//        User otherOwner = new User("otherOwner");
//        Disk disk = new Disk(1, "disk1", master, currentOwner);
//        assertEquals(currentOwner, disk.getCurrentOwner());
//        assertNotEquals(otherOwner, disk.getCurrentOwner());
//    }
//
//    @Test
//    void setCurrentOwner() {
//        User master = new User("master");
//        User currentOwner = new User("currentOwner");
//        User otherOwner = new User("otherOwner");
//        Disk disk = new Disk(1, "disk1", master, currentOwner);
//        disk.setCurrentOwner(otherOwner);
//        assertEquals(otherOwner, disk.getCurrentOwner());
//        assertNotEquals(currentOwner, disk.getCurrentOwner());
//    }
//
//}