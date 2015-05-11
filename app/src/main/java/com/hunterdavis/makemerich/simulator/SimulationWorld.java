package com.hunterdavis.makemerich.simulator;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by hunter on 5/10/15.
 */
public abstract class SimulationWorld {

    public String name = null;
    public long id = 0;
    
    public ArrayList<SimulatorEventRunnable> getPreTimeRunnables() {
        return new ArrayList<SimulatorEventRunnable>();
    }

    public ArrayList<SimulatorEventRunnable> getOnTimeRunnables() {
        return new ArrayList<SimulatorEventRunnable>();
    }
    public ArrayList<SimulatorEventRunnable> getPostTimeRunnables() {
        return new ArrayList<SimulatorEventRunnable>();
    }
    public ArrayList<SettingsActionDataItem> getSettingsItems() {
        return new ArrayList<SettingsActionDataItem>();
    }

    public ArrayList<Account> getSimulatorAccounts() {
        return new ArrayList<Account>();
    }

    public String describeWorld(Context context) {
        String accountString = "";

        for(Account account : getSimulatorAccounts()) {

            accountString += account.toString();
            accountString += "\n";
        }

        return accountString;
    }

}
