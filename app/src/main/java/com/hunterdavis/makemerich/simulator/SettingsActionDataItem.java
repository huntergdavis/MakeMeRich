package com.hunterdavis.makemerich.simulator;

/**
 * Created by hunter on 5/10/15.
 */
public class SettingsActionDataItem {
    public String name;// (use this for shared preferences automatically storing float in settings);
    public float minValue;
    public float maxValue;
    public RunnableWithFloatValue runMeOnUpdate;


    public SettingsActionDataItem(String name, float min, float max, RunnableWithFloatValue runMeOnChange) {
        this.name = name;
        this.minValue = min;
        this.maxValue = max;
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
