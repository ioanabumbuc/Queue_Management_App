package controller;

import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationListener implements ActionListener {
    private final View view;

    public SimulationListener(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimulationManager simulationManager = new SimulationManager(view);
        simulationManager.run();
    }
}
