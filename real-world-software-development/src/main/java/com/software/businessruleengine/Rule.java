package com.software.businessruleengine;

public class Rule {

    private Condition condition;
    private Action action;

    private String title;

    private String description;

    public Rule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    public Rule(Condition condition, Action action, String title, String description) {
        this.condition = condition;
        this.action = action;
        this.title = title;
        this.description = description;
    }

    public void perform(Facts facts) {
        if (condition.evaluate(facts)) {
            action.execute(facts);
        }
    }
    
}
