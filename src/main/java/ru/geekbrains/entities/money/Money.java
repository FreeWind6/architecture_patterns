package ru.geekbrains.entities.money;

public class Money {
    private long amount;
    private Currency currency;
    private static final int fractionDigits = 100;

    public Money(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money(double amount, Currency currency) {
        this.amount = Math.round(amount * fractionDigits);
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }


}
