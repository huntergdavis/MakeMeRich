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
    public long currentTime;
    public ArrayList<AccountInterestItem> interestItems;

    public Account(String name, String institution, float currentValue, long currentTime, ArrayList<AccountInterestItem> interestItems) {
        this.name = name;
        this.institution = institution;
        this.currentValue = currentValue;
        this.interestItems = interestItems;
        this.currentTime = currentTime;
    }


    public static Account createAccountWithSimpleInterestCalculatedDaily(float interest) {
        ArrayList<AccountInterestItem> interestItems = new ArrayList<AccountInterestItem>();

        AccountInterestItem regularInterest = new AccountInterestItem(interest, -1, Long.MAX_VALUE, Constants.MILLIS_IN_DAY);
        interestItems.add(regularInterest);

        Account account = new Account("test1 - Savings","BECU",5000,0, interestItems);

        return account;
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

        // the time for this little mini sim
        long miniSimulationTime = currentTime;
        float miniSimulationPrincipal = currentValue;

        while (miniSimulationTime < time) {

            for (AccountInterestItem item : interestItems) {

                // if this item is 'on'
                if (item.isThisTimeWithinCalculationBoundery(miniSimulationTime)) {
                    // if this is an interest calculation boundry time
                    if (item.thisThisATimeExactlyWhenWeCalculateInterest(miniSimulationTime)) {
                        miniSimulationPrincipal += item.giveMeInterestNow(miniSimulationPrincipal);
                    }
                }
            }

            miniSimulationTime++;
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
