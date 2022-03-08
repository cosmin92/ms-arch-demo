package com.uxied.arch.registry.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles", indexes = {@Index(name = "id", unique = true , columnList = "id")})
public class Profile {

    @Id
    @Column(unique = true, nullable = false, length = 36)
    private String id;

    @Column(length = 32, nullable = false)
    private String firstName;

    @Column(length = 32, nullable = false)
    private String lastName;

    @Column(length = 32)
    private String gender;

    @Column(length = 20, nullable = false)
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthday;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Collection<CreditCard> creditCards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Profile profile = (Profile) o;
        return id != null && Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
