package com.example.demcom.wheter.enums;

/**
 * Created by Demcom on 5/7/2017.
 */

public enum SectionEnum {
    WINE("WINE"),
    BEER("BEER");

    private final String name;

    SectionEnum(String section) {
        name = section;
    }

    public boolean equalsName(String otherName) {
        return (otherName != null) && name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
