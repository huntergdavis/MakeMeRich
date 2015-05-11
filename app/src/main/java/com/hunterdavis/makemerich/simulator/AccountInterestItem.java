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

    public float giveMePrincipalAtThisTime(float principal, long timeInMS) {
        float timeTotalFraction = timeInMS / Constants.MILLIS_IN_YEAR;

        return (float)( principal * Math.pow((1 + (interestYearly / 365) ), (365 * timeTotalFraction)));
    }

    @Override
    public String toString() {
        String retString = "Yearly Interest: " + interestYearly;
        retString += "\n";
        retString += startTime + "  through " + endTime ;
        retString += "\n";
        retString += "(calculated every " + calculationFrequencyInMilliseconds + ") milliseconds";
        retString += "\n";

        return retString;
    }
}
