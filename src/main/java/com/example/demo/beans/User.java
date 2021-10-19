package com.example.demo.beans;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Client")
@Table(name = "clients")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "credential_id")
    private Credential credential;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    private List<Disk> listDisk;

    public User(String name) {
        super();
        this.name = name;
    }

    public void setDisk(@NotNull Disk disk) {
        disk.setMaster(this);
        //getListDisk().add(disk);
    }

    public Credential getCredential() {
        return credential;
    }

    public User() {

    }

}
