package com.hunterdavis.makemerich.simulator;

import android.content.Context;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hunter on 5/10/15.
 */
public class SimulatorState {

    // our simulator variables
    public long ticks;
    public long simulatorTime;
    public AtomicBoolean silentMode;

    // how fine grained is our simulation
    public float simulationGranularity;

    public SimulatorState(Context context) {
        ticks = 0;
        simulatorTime = 0;
        silentMode = new AtomicBoolean(false);

    }

}
