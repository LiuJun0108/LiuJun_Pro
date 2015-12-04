package com.rying.model;

public class QueryDto {
	private String key;
	private String value;
	private Symbol sym;

	public enum Symbol {
		EQ("=", "等于"), GT(">", "大于"), LT("<", "小于"), GE(">=", "大于等于"), LE("<=",
				"小于等于"), LIKE("like", "模糊查询");
		private String sym;
		private String desc;

		private Symbol(String sym, String desc) {
			this.sym = sym;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.desc;
		}

		public String getSym() {
			return sym;
		}

		public void setSym(String sym) {
			this.sym = sym;
		}

	}

	public QueryDto(String key, String value, Symbol sym) {
		super();
		this.key = key;
		this.value = value;
		this.sym = sym;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Symbol getSym() {
		return sym;
	}

	public void setSym(Symbol sym) {
		this.sym = sym;
	}

}
