package com.hunterdavis.makemerich.simulator;

import java.util.ArrayList;

/**
 * Created by hunter on 5/10/15.
 */
public class TestSimWorld1 extends SimulationWorld {

    ArrayList<Account> retAccounts;


    public TestSimWorld1() {
        retAccounts =  new ArrayList<>();
        retAccounts.add(createTestAccount1());
    }

    public ArrayList<Account> getSimulatorAccounts() {
       return retAccounts;
    }

    private Account createTestAccount1() {
        // 2% interest calculated daily, not 20%
        return Account.createAccountWithSimpleInterestCalculatedDaily(0.02f);
    }
}
