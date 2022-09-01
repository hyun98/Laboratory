package com.software.bankanalyzer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions() {
        return bankTransactions.stream()
                .map(BankTransaction::getAmount)
                .reduce((double) 0, Double::sum);
    }
    
    public double summarizeTransactions(Predicate<BankTransaction> predicate) {
        return bankTransactions.stream()
                .filter(predicate)
                .map(BankTransaction::getAmount)
                .reduce((double) 0, Double::sum);
    }
    
//    public double calculateTotalAmount() {
//        return bankTransactions.stream()
//                .map(BankTransaction::getAmount)
//                .reduce((double) 0, Double::sum);
//    }
//
//    public double calculateTotalInMonth(final Month month) {
//        return bankTransactions.stream()
//                .filter((b) -> b.getDate().getMonth() == month)
//                .map(BankTransaction::getAmount)
//                .reduce((double) 0, Double::sum);
//    }
//
//    public double calculateTotalForCategory(final String category) {
//        return bankTransactions.stream()
//                .filter((b) -> b.getDescription().equals(category))
//                .map(BankTransaction::getAmount)
//                .reduce((double) 0, Double::sum);
//    }

    public List<BankTransaction> findTransactions(Predicate<BankTransaction> predicate) {
        return bankTransactions.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
