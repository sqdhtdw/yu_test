package com.Lmall.common;

/**
 * @author yourname

 * @apiNote 分类级别
 */
public enum LouMallCategoryLevelEnum {

    DEFAULT(0, "ERROR"),
    LEVEL_ONE(1, "一级分类"),
    LEVEL_TWO(2, "二级分类"),
    LEVEL_THREE(3, "三级分类");

    private int level;

    private String name;

    LouMallCategoryLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public static LouMallCategoryLevelEnum getLouMallOrderStatusEnumByLevel(int level) {
        for (LouMallCategoryLevelEnum louMallCategoryLevelEnum : LouMallCategoryLevelEnum.values()) {
            if (louMallCategoryLevelEnum.getLevel() == level) {
                return louMallCategoryLevelEnum;
            }
        }
        return DEFAULT;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
