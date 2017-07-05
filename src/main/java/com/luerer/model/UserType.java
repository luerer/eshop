package com.luerer.model;

/**
 * Created by luerer on 05/07/2017.
 */
public enum UserType {
    //admin
    ADMIN(0),
    //seller
    SELLER(1),
    //custom
    CUSTOM(2);

    private int type;

    UserType(int type) {
        this.type = type;
    }

    public static UserType valueOf(int type) {
        switch (type) {
            case 0:
                return ADMIN;
            case 1:
                return SELLER;
            case 2:
                return CUSTOM;
            default:
                return null;
        }
    }

    public int toInt() {
        return type;
    }
}