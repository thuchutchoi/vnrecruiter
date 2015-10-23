package com.spr.service;

/**
 * This exception is thrown when a todo entry is not found by
 * using the given id.
 *
 * @author Petri Kainulainen
 */
public class ShopNotFoundException extends RuntimeException {

    private final int id;

    public ShopNotFoundException(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
