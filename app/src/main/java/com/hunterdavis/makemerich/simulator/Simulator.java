package com.hunterdavis.makemerich.simulator;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by hunter on 5/10/15.
 */
public class Simulator {

    // for string and preference lookup
    Context appContext;

    public SimulatorState simulatorState;

    public ArrayList<SimulatorEvent> eventPlugins = new ArrayList<>();

    public void registerEventPlugin(SimulatorEvent event) {
        eventPlugins.add(event);
    }

    public void unregisterEventPlugin(long eventId, boolean removeAllFound) {
        for (Iterator eventRemovalIterator = eventPlugins.iterator(); eventRemovalIterator.hasNext();) {
            SimulatorEvent event = (SimulatorEvent) eventRemovalIterator.next();

            if(event.id == eventId) {
                eventRemovalIterator.remove();

                if(!removeAllFound) {
                    break;
                }
            }
        }
    }

    public Simulator(Context context) {
        this.appContext = context;
        resetToZeroStateWithCurrentPlugins();
    }

    public void resetToZeroStateWithCurrentPlugins() {
        simulatorState= new SimulatorState(appContext);
    }

    public void tick() {
        simulatorState.ticks++;

        // do simulator stuff pre-time
        for(SimulatorEvent simEvent : eventPlugins) {
            for(SimulatorEventRunnable runMe : simEvent.getPreTimeRunnables()) {
                runMe.updateStateAndRun(simulatorState);
            }
            // execute plugin pre-time logic
        }

        // add more time to simulator
        simulatorState.simulatorTime++;

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
        simulatorState.silentMode.set(true);

        final long oldCurrentTime = simulatorState.simulatorTime;
        resetToZeroStateWithCurrentPlugins();
        runToTime(oldCurrentTime);
        simulatorState.silentMode.set(false);
    }

    public void runToTime(long timeToRunTo) {
        final long runToMe = timeToRunTo;

        // we've passed this time already
        if(simulatorState.simulatorTime > runToMe) {
            return;
        }

        while(simulatorState.simulatorTime < timeToRunTo) {
            tick();
        }
    }
}
