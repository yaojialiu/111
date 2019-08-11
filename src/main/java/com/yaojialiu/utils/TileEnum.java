package com.yaojialiu.utils;
/**
 * 标题样式
 * @ClassName: TileEnum 
 * @Description: TODO
 * @author: charles
 * @date: 2019年7月30日 上午9:01:55
 */
public enum TileEnum  {
	
	COLOR_RED("color:red","标红"),
	FONTWEIGHT("font-weight:800","加粗"),
	FONTSTYLE("font-style:itialic","斜体"),
	STYLE_NONE("text_decoration:none;color:inherit","无样式");

	private String code;
	private String value;
	TileEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static void main(String[] args) {
		TileEnum[] enums = TileEnum.values();
		for (TileEnum e : enums) {
			System.out.println(e.getCode() +":"+e.getValue());
		}
		System.out.println();
	}

}
