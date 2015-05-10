package com.hunterdavis.makemerich.simulator;

/**
 * Created by hunter on 5/10/15.
 */
public class SettingsActionDataItem {
    String name;// (use this for shared preferences automatically storing float in settings);
    float minValue;
    float maxValue;
    float step;// (1.0 for int data etc);
    SettingsActionDataItemRunnable whatToDoWithResult;

    public static class SettingsActionDataItemRunnable implements Runnable {

        float updatedValue;

        public SettingsActionDataItemRunnable(float newValue) {
            updatedValue = newValue;
        };


        public void run() {

        };
    }

    public SettingsActionDataItem(String name, float min, float max, float step) {
        this.name = name;
        this.minValue = min;
        this.maxValue = max;
        this.step = step;
        this.whatToDoWithResult = new SettingsActionDataItemRunnable(step);
    }

}
