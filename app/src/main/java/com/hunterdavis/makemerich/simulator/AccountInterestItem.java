package com.hunterdavis.makemerich.simulator;

import com.hunterdavis.makemerich.Constants;

public class AccountInterestItem {
    public float interestYearly;
    public long startTime;
    public long endTime;
    public long calculationFrequencyInMilliseconds;

    public AccountInterestItem(float interestYearly, long startTime, long endTime, long calculationFrequencyInMilliseconds) {
        this.interestYearly = interestYearly;
        this.startTime = startTime;
        this.endTime = endTime;
        this.calculationFrequencyInMilliseconds = calculationFrequencyInMilliseconds;
    }

    public boolean isThisTimeWithinCalculationBoundery(long time) {
        return ((startTime < time) && (endTime > time));
    }

    public boolean thisThisATimeExactlyWhenWeCalculateInterest(long time) {
        return (calculationFrequencyInMilliseconds % time) == 0;
    }

    public float giveMeInterestNow(float currentPrincipal) {
        return currentPrincipal * ((interestYearly / Constants.MILLIS_IN_YEAR)  * calculationFrequencyInMilliseconds );
    }

    @Override
    public String toString() {
        String retString = "Yearly Interest: " + interestYearly;
        retString += "\n";
        retString += startTime + "  through " + endTime ;
        retString += "\n";
        retString += "(calculated every " + calculationFrequencyInMilliseconds + " milliseconds";
        retString += "\n";

        return retString;
    }
}
