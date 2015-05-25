package com.hunterdavis.makemerich.simulator.accounts;

import com.hunterdavis.makemerich.Constants;

/**
 * Created by hunter on 5/24/15.
 */
public class ElectricAccountWithSolar extends UtilityAccount {
    public float solarSavingsPerMonth = 0.0f;
    public float originalCost = 0.0f;

    public ElectricAccountWithSolar(String name, String institution, float currentValue, long currentTime, float monthlyCost, float yearlyIncrease, float solarSavingsPerMonth, float originalCost) {
        super(name, institution, currentValue, currentTime, monthlyCost, yearlyIncrease);
        this.solarSavingsPerMonth = solarSavingsPerMonth;
        this.originalCost = originalCost;
    }

    public static ElectricAccountWithSolar getBasicElectricAccount(float monthlyCost, float yearlyIncrease,float electricSavings, float originalCost) {
        return new ElectricAccountWithSolar("test1 - Electric","Seattle City Light",0,0, monthlyCost,yearlyIncrease,electricSavings, originalCost);
    }

    @Override
    public float getValueAtTimeInFuture(long time) {
        float totalValue = super.getValueAtTimeInFuture(time);

        float totalSavings = solarSavingsPerMonth * (time / Constants.MILLIS_IN_MONTH);

        return totalValue + totalSavings;

    }

    @Override
    public String toString() {
        String retString = super.toString();

        retString += "Original Cost " + originalCost + "\n";
        retString += "Solar Pays For Itself in " + originalCost/solarSavingsPerMonth/12 + " years" + "\n";

        return retString;
    }
}
