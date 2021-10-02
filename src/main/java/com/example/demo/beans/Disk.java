package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "Disk")
@Table(name = "disks")
public class Disk {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User master;
    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User currentOwner;

    public Disk(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Disk() {
    }

    public Disk(long id, String name, User master, User currentOwner) {
        super();
        this.id = id;
        this.name = name;
        this.master = master;
        this.currentOwner = currentOwner;
    }

    public Disk(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public User getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(User currentOwner) {
        this.currentOwner = currentOwner;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentOwner == null) ? 0 : currentOwner.hashCode());
        result = prime * result + (int)(id ^ (id >>> 32));
        result = prime * result + ((master == null) ? 0 : master.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Disk other = (Disk)obj;

        return getCurrentOwner() == other.getCurrentOwner() && getMaster() == other.getMaster() && Objects.equals(getName(), other.getName()) && Objects.equals(getId(), other.getId());
    }

    @Override
    public String toString() {
        return "Disk [id=" + id + ", name=" + name + ", master=" + master.getName() + ", currentOwner=" + currentOwner.getName() + "]";
    }
}