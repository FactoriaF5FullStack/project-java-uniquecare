package org.factoriaf5.uniquecare.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.factoriaf5.uniquecare.service.Service;
import org.factoriaf5.uniquecare.role.Role;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties("owner")
    private Set<Service> facilities = new HashSet<>();

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    public User() {
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addFacility(Service facility) {
        this.getFacilities().add(facility);
    }

    public void removeFacility(Service facility) {
        this.getFacilities().remove(facility);
    }
}