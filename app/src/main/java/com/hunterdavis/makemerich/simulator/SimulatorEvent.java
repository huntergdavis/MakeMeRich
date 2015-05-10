package com.hunterdavis.makemerich.simulator;

import java.util.ArrayList;

/**
 * Created by hunter on 5/10/15.
 */
public interface SimulatorEvent {

    public String name = null;
    public long id = 0;

    
    ArrayList<SimulatorEventRunnable> getPreTimeRunnables();
    ArrayList<SimulatorEventRunnable> getOnTimeRunnables();
    ArrayList<SimulatorEventRunnable> getPostTimeRunnables();
    ArrayList<SettingsActionDataItem> getSettingsItems();

}
