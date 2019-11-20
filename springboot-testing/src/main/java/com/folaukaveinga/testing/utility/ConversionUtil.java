package com.folaukaveinga.testing.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConversionUtil {

	public static <E> List<E> getListFromSet(Set<E> set) {
		return new ArrayList<E>(set);
	}

	public static <E> Set<E> getSetFromList(List<E> list) {
		return new HashSet<E>(list);
	}
}
