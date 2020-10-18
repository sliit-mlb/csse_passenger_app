package com.example.passangerapp;

public class Item {
    private String date;
    private String from;
    private String to;
    private String departureTime;
    private String arrivalTime;
    private String amount;

    public Item(){

    }

    public Item(String date, String from, String to, String departureTime, String arrivalTime, String amount) {
        this.setDate(date);
        this.setFrom(from);
        this.setTo(to);
        this.setDepartureTime(departureTime);
        this.setArrivalTime(arrivalTime);
        this.setAmount(amount);
    }


    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
