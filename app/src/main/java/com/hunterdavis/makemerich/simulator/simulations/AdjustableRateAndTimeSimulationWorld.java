package com.hunterdavis.makemerich.simulator.simulations;

import com.hunterdavis.makemerich.Constants;
import com.hunterdavis.makemerich.MainSimulatorView;
import com.hunterdavis.makemerich.simulator.SettingsActionDataItem;

import java.util.ArrayList;

/**
 * Created by hunter on 5/17/15.
 */
public class AdjustableRateAndTimeSimulationWorld extends AdjustableRatesSimulationWorld {

    @Override
    public ArrayList<SettingsActionDataItem> getSettingsItems() {
        ArrayList<SettingsActionDataItem> retList =  super.getSettingsItems();


            SettingsActionDataItem simulationTimeItem = new SettingsActionDataItem("Simulation Time (Years)",
                    0,100,new SettingsActionDataItem.RunnableWithFloatValue(1f) {
                @Override
                public void run() {
                    MainSimulatorView.TheSimulator.runToTime((long)(Constants.MILLIS_IN_YEAR * f));
                };
            });

            retList.add(simulationTimeItem);


        return retList;
    }
}
