package com.software.businessruleengine;

@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
