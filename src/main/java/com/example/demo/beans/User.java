package com.example.demo.beans;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
@Table(name = "clients")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    private Credential credential;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Disk> listDisk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String name) {
        super();
        this.name = name;
    }

    public List<Disk> getListDisk() {
        if (listDisk == null) {
            this.listDisk = new ArrayList<>();
        }
        return listDisk;
    }

    public void setDisk(@NotNull Disk disk) {
        disk.setMaster(this);
        getListDisk().add(disk);
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credential getCredential() {
        if (credential == null) {
            setCredential(new Credential());
        }
        return credential;
    }

    public void setCredential(Credential credential) {
        if (credential == null) {
            credential = new Credential();
        }
        this.credential = credential;
        credential.setUser(this);
    }

}
