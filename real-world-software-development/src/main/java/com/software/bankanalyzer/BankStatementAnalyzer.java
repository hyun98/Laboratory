package com.software.bankanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    
    private static final String RESOURCES = "./src/main/resources/";


    public void analyze(final String fileName,
                        final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions
                = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor
                = new BankStatementProcessor(bankTransactions);

        final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(
                b -> b.getDate().getMonth() == Month.FEBRUARY &&
                        b.getAmount() >= 1000);

        collectSummary(bankStatementProcessor);
    }

    public static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println(bankStatementProcessor.summarizeTransactions());
        System.out.println(bankStatementProcessor.summarizeTransactions(b -> b.getDate().getMonth() == Month.JANUARY));
        System.out.println(bankStatementProcessor.summarizeTransactions(b -> b.getDate().getMonth() == Month.FEBRUARY));
        System.out.println(bankStatementProcessor.summarizeTransactions(b -> b.getDescription().equals("Salary")));
    }
}
