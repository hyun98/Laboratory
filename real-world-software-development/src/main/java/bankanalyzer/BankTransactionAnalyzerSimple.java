package main.java.bankanalyzer;

import java.io.IOException;

public class BankTransactionAnalyzerSimple {
    

    public static void main(String[] args) throws IOException {

        final BankStatementAnalyzer bankStatementAnalyzer 
                = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser 
                = new BankStatementCSVParser();
        
        bankStatementAnalyzer.analyze("bank-data.csv", bankStatementParser);
    }
}
