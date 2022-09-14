package com.software.businessruleengine;

import java.util.HashMap;
import java.util.Map;


/*
    실제 정보를 의미한다.
 */
public class Facts {
    
    private Map<String, String> facts = new HashMap<>();

    public void setFact(String name, String value) {
        this.facts.put(name, value);
    }

    public String getFact(final String name) {
        return facts.get(name);
    }
}
