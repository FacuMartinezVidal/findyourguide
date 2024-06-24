package com.findyourguide.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.findyourguide.api.entity.Trophy.Trophy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends Base implements UserDetails {
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Trophy> trophyList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user", nullable = false, unique = true)
    private String username;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String password;
    private String phone;
    private String dni;
    private String gender;
    private Double score;
    private boolean active;
    @Column(name = "profile_photo")
    private String profilePhoto;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.OFFLINE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(username));
    }

}
