package com.kaveinga.user;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String firstName;

	private String lastName;

}
