package com.software.bankanalyzer;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {

    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(LocalDate date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        BankTransaction that = (BankTransaction) obj;
        return Double.compare(that.amount, amount) == 0 &&
                date.equals(that.date) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
    }

    public Notification validate() {

        final Notification notification = new Notification();
        if (this.description.length() > 100) {
            notification.addError("The description is too Long");
        }

        final LocalDate parsedDate;
        return notification;
    }
}
