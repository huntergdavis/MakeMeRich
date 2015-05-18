package com.hunterdavis.makemerich.simulator.simulations;

import com.hunterdavis.makemerich.simulator.Account;
import com.hunterdavis.makemerich.simulator.SettingsActionDataItem;
import com.hunterdavis.makemerich.simulator.SimulationWorld;

import java.util.ArrayList;

/**
 * Created by hunter on 5/17/15.
 */
public class AdjustableRatesSimulationWorld extends SimulationWorld {

    ArrayList<Account> retAccounts;

    public AdjustableRatesSimulationWorld() {
        retAccounts = new ArrayList<>();
        retAccounts.add(createTestAccount1());
    }

    @Override
    public String getName() {
        return "Adjustable Rates Simulation World";
    }

    public ArrayList<Account> getSimulatorAccounts() {
        return retAccounts;
    }

    private Account createTestAccount1() {
        // 2% interest calculated daily, not 20%
        return Account.createAccountWithSimpleInterestCalculatedDaily(0.02f, "Sliding Scale", "BECU", 5000);
    }

    public ArrayList<SettingsActionDataItem> getSettingsItems() {
        ArrayList<SettingsActionDataItem> retList =  new ArrayList<>();

        for(final Account account : retAccounts) {
            SettingsActionDataItem accountInterestItem = new SettingsActionDataItem(account.name,
              0,100,new SettingsActionDataItem.RunnableWithFloatValue(account.interestItems.get(0).interestYearly) {
                @Override
                public void run() {
                    account.interestItems.get(0).interestYearly = f;
                };
            });

            retList.add(accountInterestItem);
        }

        return retList;
    }
}