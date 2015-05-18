package com.hunterdavis.makemerich.simulator;

/**
 * Created by hunter on 5/10/15.
 */
public class SettingsActionDataItem {
    String name;// (use this for shared preferences automatically storing float in settings);
    float minValue;
    float maxValue;
    float step;// (1.0 for int data etc);
    RunnableWithFloatValue runMeOnUpdate;


    public SettingsActionDataItem(String name, float min, float max, float step, RunnableWithFloatValue runMeOnChange) {
        this.name = name;
        this.minValue = min;
        this.maxValue = max;
        this.step = step;
        this.runMeOnUpdate = runMeOnChange;
    }


    public static class RunnableWithFloatValue implements Runnable {

        public Float f;

        public RunnableWithFloatValue(Float f) {
            this.f = f;
        }

        @Override
        public void run() {

        }
    }
}
