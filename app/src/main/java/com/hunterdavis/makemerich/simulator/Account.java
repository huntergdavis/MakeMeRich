package com.hunterdavis.makemerich.simulator;

import com.hunterdavis.makemerich.Constants;

import java.util.ArrayList;

/**
 * Created by hunter on 5/10/15.
 */
public class Account {
    public String name;
    public String institution;
    public float currentValue;
    public float initialValue;
    public long initialTime;
    public long currentTime;
    public ArrayList<AccountInterestItem> interestItems;

    public Account(String name, String institution, float currentValue, long currentTime, ArrayList<AccountInterestItem> interestItems) {
        this.name = name;
        this.institution = institution;
        this.currentValue = currentValue;
        this.interestItems = interestItems;
        this.currentTime = currentTime;
        this.initialTime = currentTime;
        this.initialValue = currentValue;
    }

    public static Account createAccountWithSimpleInterestCalculatedDaily(float interest, String name, String institution, float currentValue) {
        ArrayList<AccountInterestItem> interestItems = new ArrayList<>();

        AccountInterestItem regularInterest = new AccountInterestItem(interest, -1, Long.MAX_VALUE, Constants.MILLIS_IN_DAY);
        interestItems.add(regularInterest);

        Account account = new Account(name,institution,currentValue,0, interestItems);

        return account;
    }

    public static Account createAccountWithSimpleInterestCalculatedDaily(float interest) {
        ArrayList<AccountInterestItem> interestItems = new ArrayList<AccountInterestItem>();

        AccountInterestItem regularInterest = new AccountInterestItem(interest, -1, Long.MAX_VALUE, Constants.MILLIS_IN_DAY);
        interestItems.add(regularInterest);

        Account account = new Account("test1 - Savings","BECU",5000,0, interestItems);

        return account;
    }

    public void reset() {
        this.currentTime = initialTime;
        this.currentValue = initialValue;
    }

    public void runToTimeInFuture(long time) {
        this.currentValue = getValueAtTimeInFuture(time);
        this.currentTime = time;
    }

    // returns value at time, or current if past
    public float getValueAtTimeInFuture(long time) {


        if (time < currentTime) {
            return currentValue;
        }

        float miniSimulationPrincipal = currentValue;
        for (AccountInterestItem item : interestItems) {

            miniSimulationPrincipal = item.giveMePrincipalAtThisTime(miniSimulationPrincipal, time);
        }

        return miniSimulationPrincipal;
    }

    @Override
    public String toString() {
        String items = "";

        for(AccountInterestItem interestItem : interestItems) {
            items += interestItem.toString();
            items += "\n";
        }

        items += "Account Balance = " + currentValue;
        items += "\n";
        items += "Name = " + name;
        items += "\n";
        items += "Institution: " + institution;
        items += "\n";
        items += "Current Time: " + currentTime;
        items += "\n";

        return items;
    }
}
