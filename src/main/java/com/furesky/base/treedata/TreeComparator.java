package com.furesky.base.treedata;

import java.util.Comparator;

public class TreeComparator<E extends Tree<E>> implements Comparator<E> {
	@Override
	public int compare(E o1, E o2) {
		String rank1=o1.peekRank();
		String rank2=o2.peekRank();
		return rank1.compareTo(rank2);
	}
}
