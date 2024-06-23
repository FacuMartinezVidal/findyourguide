package com.findyourguide.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Guide extends User {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    private String cities;

    @Column(name = "credential_photo")
    private String credentialPhoto;

    @Enumerated(EnumType.STRING)
    private Language language;

    @JsonManagedReference
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL)
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
