package com.folaukaveinga.springboot.service;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.folaukaveinga.springboot.domain.User;

public class UserSpecs {

	public static Specification<User> isUserYoungerThan20(int yr) {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.lessThan(root.get("age"), yr);
			}
		};
	}
}
