package com.hunterdavis.makemerich.simulator;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hunter on 5/10/15.
 */
public class Simulator {

    // for string and preference lookup
    Context appContext;

    public SimulatorState simulatorState;

    public ArrayList<SimulationWorld> simulationWorlds = new ArrayList<>();

    public void registerSimulationWorld(SimulationWorld event) {
        simulationWorlds.add(event);
    }

    public void unregisterSimulationWorld(long eventId, boolean removeAllFound) {
        for (Iterator eventRemovalIterator = simulationWorlds.iterator(); eventRemovalIterator.hasNext();) {
            SimulationWorld event = (SimulationWorld) eventRemovalIterator.next();

            if(event.id == eventId) {
                eventRemovalIterator.remove();

                if(!removeAllFound) {
                    break;
                }
            }
        }
    }

    public String describeWorlds() {
        String descriptionString = "";

        for(SimulationWorld world : simulationWorlds) {
            descriptionString += world.describeWorld(appContext);
            descriptionString += "\n";
        }

        return descriptionString;
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
        for(SimulationWorld simEvent : simulationWorlds) {
            for(SimulatorEventRunnable runMe : simEvent.getPreTimeRunnables()) {
                runMe.updateStateAndRun(simulatorState);
            }
            // execute plugin pre-time logic
        }

        // add more time to simulator
        simulatorState.simulatorTime += simulatorState.simulationGranularity;

        // do simulator stuff on-time
        for(SimulationWorld simEvent : simulationWorlds) {
            // execute plugin on-time logic
            for(SimulatorEventRunnable runMe : simEvent.getOnTimeRunnables()) {
                runMe.updateStateAndRun(simulatorState);
            }
        }

        // do simulator stuff post-time
        for(SimulationWorld simEvent : simulationWorlds) {
            // execute plugin post-time logic
            for (SimulatorEventRunnable runMe : simEvent.getPostTimeRunnables()) {
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

        // we've passed this time already
        if(simulatorState.simulatorTime > timeToRunTo) {
            return;
        }

        while(simulatorState.simulatorTime < timeToRunTo) {
            tick();
        }

        for(SimulationWorld world : simulationWorlds) {
            for(Account account : world.getSimulatorAccounts()) {
                account.runToTimeInFuture(timeToRunTo);
            }
        }
    }
}
