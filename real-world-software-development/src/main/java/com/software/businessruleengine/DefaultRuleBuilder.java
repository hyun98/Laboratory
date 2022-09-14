package com.software.businessruleengine;

public class DefaultRuleBuilder implements RuleBuilder {
    
    private Condition condition;

    private Action action;

    private String title;

    private String description;

    @Override
    public RuleBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public RuleBuilder description(String des) {
        this.description = des;
        return this;
    }

    @Override
    public RuleBuilder when(Condition condition) {
        this.condition = condition;
        return this;
    }
    
    @Override
    public RuleBuilder then(Action action) {
        this.action = action;
        return this;
    }

    @Override
    public Rule createRule() {
        return new Rule(condition, action, title, description);
    }
}
