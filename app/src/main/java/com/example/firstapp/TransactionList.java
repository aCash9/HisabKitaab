package com.example.firstapp;

public class TransactionList {
    private int amount;
    private String note;
    private String date;
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionList() {
    }

    @Override
    public String toString() {
        return "TransactionList{" +
                "amount=" + amount +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public TransactionList(int amount, String note, String date, String type) {
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
