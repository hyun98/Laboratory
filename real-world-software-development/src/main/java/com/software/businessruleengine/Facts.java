package com.software.businessruleengine;

import java.util.HashMap;
import java.util.Map;

public class Facts {
    
    private Map<String, String> facts = new HashMap<>();

    public void setFacts(String name, String value) {
        this.facts.put(name, value);
    }

    public String getFacts(final String name) {
        return facts.get(name);
    }
}
