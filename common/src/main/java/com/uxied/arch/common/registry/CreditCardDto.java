package com.uxied.arch.common.registry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardDto {
    private String number;
    private String brand;
    private short expiryMonth;
    private short expiryYear;
}
