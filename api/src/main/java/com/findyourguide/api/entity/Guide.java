package com.findyourguide.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.findyourguide.api.entity.Service.Service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Guide extends User {

    @Column(name = "Country")
    private String country;

    @Column(name = "credential_photo")
    private String credentialPhoto;

    @Enumerated(EnumType.STRING)
    private Language language;

    @JsonManagedReference
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Service> guideServices;

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }
}
