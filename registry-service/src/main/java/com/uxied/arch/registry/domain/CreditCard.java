package com.uxied.arch.registry.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "credit_cards", indexes = {@Index(name = "number", columnList = "number", unique = true)})
public class CreditCard {

    @Id
    @Column(nullable = false, unique = true, length = 16)
    private String number;

    @Column(nullable = false, length = 32)
    private String brand;

    @Column(nullable = false)
    private short expiryMonth;

    @Column(nullable = false)
    private short expiryYear;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Profile owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CreditCard that = (CreditCard) o;
        return number != null && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
