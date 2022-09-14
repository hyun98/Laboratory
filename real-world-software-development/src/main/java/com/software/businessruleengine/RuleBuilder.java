package com.software.businessruleengine;

public interface RuleBuilder {


    RuleBuilder title(String title);

    RuleBuilder description(String des);
    
    RuleBuilder when(Condition condition);

    RuleBuilder then(Action action);

    Rule createRule();
    
}
