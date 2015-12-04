package com.rying.easyui.model;

public enum IconCls {
	ICON_ADD("icon-add"),
	ICON_EDIT("icon-edit"),
	ICON_REMOVE("icon-remove"),
	ICON_SAVE("icon-save"), 
	ICON_CUT("icon-cut"),
	ICON_OK("icon-ok"),
	ICON_NO("icon-no"),
	ICON_CANCEL("icon-cancel"),
	ICON_RELOAD("icon-reload"),
	ICON_GO("icon-cog_go"),
	ICON_SEARCH("icon-search");

	private final String iconCls;
	private IconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Override
	public String toString() {
		return this.iconCls;
	}
	
	public static void main(String[] args) {
		System.out.println(IconCls.ICON_REMOVE);
	}
}
