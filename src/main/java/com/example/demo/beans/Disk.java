package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Data
@Entity(name = "Disk")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "disks")
@ToString

public class Disk {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User master;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User currentOwner;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentOwner == null) ? 0 : currentOwner.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((master == null) ? 0 : master.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Disk disk = (Disk) o;
        return Objects.equals(id, disk.id);
    }
}