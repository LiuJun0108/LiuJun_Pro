package com.rying.service.comparator;

import java.util.Comparator;

import com.rying.entity.Menu;

public class MenuComparator implements Comparator<Menu> {

	@Override
	public int compare(Menu o1, Menu o2) {
		if (o1.getSeq() > o2.getSeq())
			return 1;
		else if (o1.getSeq() < o2.getSeq())
			return -1;
		else
			return 0;
	}

}
