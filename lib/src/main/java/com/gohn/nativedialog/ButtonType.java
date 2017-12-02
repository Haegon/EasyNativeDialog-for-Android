package com.gohn.nativedialog;

/**
 * Created by gohn on 2017. 11. 28..
 */

public enum ButtonType {
    NO_BUTTON(0), ONE_BUTTON(1), TWO_BUTTON(1 | 2), THREE_BUTTON(1 | 2 | 4);

    private final int value;

    ButtonType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
