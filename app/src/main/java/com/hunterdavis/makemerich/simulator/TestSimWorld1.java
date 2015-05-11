package com.hunterdavis.makemerich.simulator;

import java.util.ArrayList;

/**
 * Created by hunter on 5/10/15.
 */
public class TestSimWorld1 extends SimulationWorld {


    public ArrayList<Account> getSimulatorAccounts() {
        ArrayList<Account> retAccounts =  new ArrayList<>();

        retAccounts.add(createTestAccount1());
        return retAccounts;
    }

    private Account createTestAccount1() {
        return Account.createAccountWithSimpleInterestCalculatedDaily(0.2f);
    }
}
