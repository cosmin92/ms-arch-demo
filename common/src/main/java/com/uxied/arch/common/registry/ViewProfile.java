package com.uxied.arch.common.registry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewProfile {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private Date birthday;
}
