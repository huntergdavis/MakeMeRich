package com.hunterdavis.makemerich.simulator.simulations;

import com.hunterdavis.makemerich.Constants;
import com.hunterdavis.makemerich.MainSimulatorView;
import com.hunterdavis.makemerich.simulator.SettingsActionDataItem;
import com.hunterdavis.makemerich.simulator.SimulationWorld;
import com.hunterdavis.makemerich.simulator.accounts.Account;
import com.hunterdavis.makemerich.simulator.accounts.UtilityAccount;

import java.util.ArrayList;

/**
 * Created by hunter on 5/24/15.
 */
public class SimpleUtilitySimulation extends SimulationWorld {


    ArrayList<Account> retAccounts;


    public SimpleUtilitySimulation() {
        retAccounts =  new ArrayList<>();
        retAccounts.add(UtilityAccount.getBasicUtilityAccountMonthlyWithYearlyIncrease(150, .02f));
    }

    @Override
    public String getName() {
        return "SimpleUtilitySimulation";
    }

    public ArrayList<Account> getSimulatorAccounts() {
        return retAccounts;
    }

    public ArrayList<SettingsActionDataItem> getSettingsItems() {
        ArrayList<SettingsActionDataItem> retList =  new ArrayList<>();

        for(final Account account : retAccounts) {
            if(account instanceof UtilityAccount) {
                final UtilityAccount uAccount = (UtilityAccount) account;

                SettingsActionDataItem accountInterestItem = new SettingsActionDataItem(uAccount.name + " (yearly increase)",
                        0, 100, new SettingsActionDataItem.RunnableWithFloatValue(uAccount.yearlyIncreasePercentage) {
                    @Override
                    public void run() {
                        uAccount.yearlyIncreasePercentage = f;
                    }

                    ;
                });

                retList.add(accountInterestItem);

                SettingsActionDataItem accountCostItem = new SettingsActionDataItem(uAccount.name + " (monthly cost)",
                        0, 100, new SettingsActionDataItem.RunnableWithFloatValue(uAccount.monthlyCost) {
                    @Override
                    public void run() {
                        uAccount.monthlyCost = f;
                    }

                    ;
                });

                retList.add(accountCostItem);


                SettingsActionDataItem simulationTimeItem = new SettingsActionDataItem("Simulation Time (Years)",
                        0,100,new SettingsActionDataItem.RunnableWithFloatValue(1f) {
                    @Override
                    public void run() {
                        MainSimulatorView.TheSimulator.runToTime((long)(Constants.MILLIS_IN_YEAR * f));
                    };
                });

                retList.add(simulationTimeItem);

            }
        }

        return retList;
    }
}
