package com.software.businessruleengine;

public class Main {

    public static void main(String[] args) {
        final Facts env = new Facts();
        env.setFact("name", "Bob");
        env.setFact("jobTitle", "CEO");

        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(env);

        final Rule ceoRule = new DefaultRuleBuilder()
                .title("CEO Rule")
                .description("Send Email to Sales when CEO")
                .when(facts -> "CEO".equals(env.getFact("jobTitle")))
                .then(facts -> {
                    final String name = facts.getFact("name");
                    System.out.println("Relevant customer : " + name);
                })
                .createRule();

        businessRuleEngine.addRule(ceoRule);
        businessRuleEngine.run();
    }
}
