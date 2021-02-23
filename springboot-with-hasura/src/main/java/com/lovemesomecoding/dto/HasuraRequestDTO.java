package com.lovemesomecoding.dto;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class HasuraRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private HasuraRequestActionDTO action;
	private Map<String, String> input;
	private Map<String, String> session_variables;

}
