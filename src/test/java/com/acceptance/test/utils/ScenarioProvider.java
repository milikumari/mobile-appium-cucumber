package com.acceptance.test.utils;

import io.cucumber.java.Scenario;

public class ScenarioProvider {

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
    private Scenario scenario;
}
