package com.hunterdavis.makemerich.simulator;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hunter on 5/10/15.
 */
public class Simulator {

    // for string and preference lookup
    Context appContext;

    public long ticks = 0;
    public long simulatorTime = 0;
    public SimulatorState simulatorState= new SimulatorState();

    public AtomicBoolean silentMode = new AtomicBoolean(false);

    public ArrayList<SimulatorEvent> eventPlugins = new ArrayList<>();

    public void registerEventPlugin() {

    }

    public Simulator(Context context) {
        this.appContext = context;
    }

    public void tick() {
        ticks++;

        // do simulator stuff pre-time
        for(SimulatorEvent simEvent : eventPlugins) {
            for(SimulatorEventRunnable runMe : simEvent.getPreTimeRunnables()) {
                runMe.updateStateAndRun(simulatorState);
            }
            // execute plugin pre-time logic
        }

        // do simulator stuff on-time
        for(SimulatorEvent simEvent : eventPlugins) {
            // execute plugin on-time logic
            for(SimulatorEventRunnable runMe : simEvent.getOnTimeRunnables()) {
                runMe.updateStateAndRun(simulatorState);
            }
        }

        // do simulator stuff post-time
        for(SimulatorEvent simEvent : eventPlugins) {
            // execute plugin post-time logic
            for(SimulatorEventRunnable runMe : simEvent.getPostTimeRunnables()) {
                runMe.updateStateAndRun(simulatorState);
            }
        }


    }

    public void regenerateToCurrentTimeWithNewValues() {
        silentMode.set(true);



        silentMode.set(false);
    }

    public void runToTime(long timeToRunTo) {

    }
}
