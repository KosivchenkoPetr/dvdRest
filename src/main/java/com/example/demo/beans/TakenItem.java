package com.example.demo.beans;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TakenItem")
@Table(name = "takenItems")
public class TakenItem {

    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Disk disk;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private User currentOwner;
    private boolean isFree;

    public TakenItem() {
        super();
        this.isFree = (currentOwner == null);
    }

    public TakenItem(Disk disk, User currentOwner) {
        super();
        setDisk(disk);
        setCurrentOwner(currentOwner);
    }

    public TakenItem(Disk disk) {
        super();
        setDisk(disk);
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
}
