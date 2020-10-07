package com.lovemesomecoding.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String            firstName;

    private String            lastName;

    private String            email;

}
