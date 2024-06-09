package com.findyourguide.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guide")
public class Guide extends User {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    Country country;

    String cities;


    @Column(name = "credential_photo")
    String credentialPhoto;

    @Enumerated(EnumType.STRING)
    Language language;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
