package com.example.reactive.service;

import java.util.AbstractList;
import java.util.ArrayList;

public interface Test {
    private static void temp1() {
        System.out.println("temp1");
    }

    default void tempDefault() {
        System.out.println("tempDefault");
    }

    void tempPublic();
}
