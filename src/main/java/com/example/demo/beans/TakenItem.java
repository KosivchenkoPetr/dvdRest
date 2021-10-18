package com.example.demo.beans;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "TakenItem")
@Table(name = "takenItems")
public class TakenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disk_id")
    private Disk disk;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "user_id")
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
