package com.software.businessruleengine;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
