package com.hunterdavis.makemerich.simulator.accounts;

import com.hunterdavis.makemerich.Constants;
import com.hunterdavis.makemerich.simulator.AccountInterestItem;

import java.util.ArrayList;

/**
 * Created by hunter on 5/24/15.
 */
public class UtilityAccount extends Account {

    public float monthlyCost = 0.0f;
    public float yearlyIncreasePercentage = 0.0f;

    public UtilityAccount(String name, String institution, float currentValue, long currentTime, float monthlyCost, float yearlyIncrease) {
        super(name, institution, currentValue, currentTime, new ArrayList<AccountInterestItem>());
        this.monthlyCost = monthlyCost;
        this.yearlyIncreasePercentage = yearlyIncrease;
    }

    public static UtilityAccount getBasicUtilityAccountMonthly(float monthlyCost) {
        return new UtilityAccount("test1 - Electric","Seattle City Light",0,0, monthlyCost,0);
    }

    public static UtilityAccount getBasicUtilityAccountMonthlyWithYearlyIncrease(float monthlyCost, float yearlyIncrease) {
        return new UtilityAccount("test1 - Electric","Seattle City Light",0,0, monthlyCost,yearlyIncrease);
    }

    @Override
    public float getValueAtTimeInFuture(long time) {
        float simValue = 0;
        float simMonthlyCost = monthlyCost;
        float yearCost = 0;

        int numYears = (int)(time / Constants.MILLIS_IN_YEAR);
        for(int i = 0;i<numYears;i++) {
            yearCost = simMonthlyCost * 12;
            simValue -= yearCost;
            simMonthlyCost = simMonthlyCost * (1 + yearlyIncreasePercentage);
        }

        int months = (int) (time / Constants.MILLIS_IN_MONTH);
        months = months - (numYears * 12);

        simValue -= (simMonthlyCost * months);

        return simValue;
    }
}
