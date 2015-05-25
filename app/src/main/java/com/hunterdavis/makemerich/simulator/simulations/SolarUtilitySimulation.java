package com.hunterdavis.makemerich.simulator.simulations;

import com.hunterdavis.makemerich.Constants;
import com.hunterdavis.makemerich.MainSimulatorView;
import com.hunterdavis.makemerich.simulator.SettingsActionDataItem;
import com.hunterdavis.makemerich.simulator.accounts.Account;
import com.hunterdavis.makemerich.simulator.accounts.ElectricAccountWithSolar;
import com.hunterdavis.makemerich.simulator.accounts.UtilityAccount;

import java.util.ArrayList;

/**
 * Created by hunter on 5/24/15.
 */
public class SolarUtilitySimulation extends SimpleUtilitySimulation{

    public SolarUtilitySimulation() {
        retAccounts =  new ArrayList<>();
        retAccounts.add(ElectricAccountWithSolar.getBasicElectricAccount(150, .02f, 150, 15000));
    }

    @Override
    public String getName() {
        return "Electric And Solar Simulation";
    }


    @Override
    public ArrayList<SettingsActionDataItem> getSettingsItems() {
        ArrayList<SettingsActionDataItem> retList =  new ArrayList<>();

        for(final Account account : retAccounts) {
            if(account instanceof ElectricAccountWithSolar) {
                final ElectricAccountWithSolar uAccount = (ElectricAccountWithSolar) account;

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

                SettingsActionDataItem solarSavingsItem = new SettingsActionDataItem(uAccount.name + " (solar savings)",
                        0, 100, new SettingsActionDataItem.RunnableWithFloatValue(uAccount.solarSavingsPerMonth) {
                    @Override
                    public void run() {
                        uAccount.solarSavingsPerMonth = f;
                    }

                    ;
                });

                retList.add(solarSavingsItem);


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
