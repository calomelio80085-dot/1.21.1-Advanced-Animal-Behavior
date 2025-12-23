package com.example.examplemod.attachment;

import com.example.examplemod.util.NeedSeverity;

import static com.example.examplemod.util.NeedSeverity.CASUAL;

public class SurvivalState {

    //Normalized: 0.0 = worst, 1.0 = best
    public float hunger = 1.0f;
    public float hydration = 1.0f;

    /*
    * 0.5 = optimal temperature
    * 0.0 = freezing, 1.0 = overheating
     */
    public float temperature = 0.5f;

    // Severity Helpers
     public NeedSeverity hungerSeverity() {
         if (hunger < 0.3f) return NeedSeverity.CRITICAL;
         if (hunger < 0.6f) return NeedSeverity.ROUTINE;
         if (hunger < 0.8f) return CASUAL;
         return NeedSeverity.COMFORTABLE;
     }

    public NeedSeverity hydrationSeverity() {
        if (hydration < 0.3f) return NeedSeverity.CRITICAL;
        if (hydration < 0.6f) return NeedSeverity.ROUTINE;
        if (hydration < 0.8f) return CASUAL;
        return NeedSeverity.COMFORTABLE;
    }

    public NeedSeverity temperatureSeverity() {
         float delta = Math.abs(temperature - 0.5f);

         if (delta > 0.3f) return NeedSeverity.CRITICAL;
         if (delta > 0.2f) return NeedSeverity.ROUTINE;
         if (delta > 0.1f) return CASUAL;
         return NeedSeverity.COMFORTABLE;
    }

    public void tick() {
         hunger = Math.max(0f, hunger - 0.01f);
         hydration = Math.max(0f, hydration - 0.015f);
    }
}
