package com.lovemesomecoding.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(value = Include.NON_NULL)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            uuid;

    private String            firstName;

    private String            lastName;

    private String            email;

}
