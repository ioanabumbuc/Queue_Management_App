package view;

import controller.SimulationListener;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private final Font f = new Font("Roboto",Font.PLAIN,16);
    private final JLabel title = new JLabel("Queue management simulator");
    private final JLabel nbClients = new JLabel("Enter number of clients: ");
    private final JLabel nbQueues = new JLabel("Enter number of queues: ");
    private final JLabel simulationTime = new JLabel("Enter max simulation time: ");
    private final JLabel arrivalTimes = new JLabel("Arrival time bounds: ");
    private final JLabel serviceTimes = new JLabel("Service time bounds: ");
    private final JLabel min = new JLabel("min: ");
    private final JLabel max = new JLabel("max: ");
    private final JLabel min2 = new JLabel("min: ");
    private final JLabel max2 = new JLabel("max: ");
    private final JTextField nbClientsField = new JTextField();
    private final JTextField nbQueuesField = new JTextField();
    private final JTextField minArriveTime = new JTextField();
    private final JTextField maxArriveTime = new JTextField();
    private final JTextField minServiceTime = new JTextField();
    private final JTextField maxServiceTime = new JTextField();
    private final JTextField maxSimulationTime = new JTextField();
    private final JButton startSimulation = new JButton("Start simulation!");
    private final String outFile;

    public View(String outFile){
        this.outFile=outFile;
        setSize(450,550);
        setLayout(null);
        setTitle("Queues Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponents();
    }

    private void addComponents(){
        title.setBounds(70,20,300,40);
        title.setFont(new Font("Roboto",Font.BOLD,20));
        add(title);

        nbClients.setBounds(50,80,350,30);
        nbClients.setFont(f);
        add(nbClients);

        nbClientsField.setBounds(50,110,350,30);
        nbClientsField.setFont(f);
        add(nbClientsField);

        nbQueues.setBounds(50,140,350,30);
        nbQueues.setFont(f);
        add(nbQueues);

        nbQueuesField.setBounds(50,170,350,30);
        nbQueuesField.setFont(f);
        add(nbQueuesField);

        simulationTime.setBounds(50,200,350,30);
        simulationTime.setFont(f);
        add(simulationTime);

        maxSimulationTime.setBounds(50,230,350,30);
        maxSimulationTime.setFont(f);
        add(maxSimulationTime);

        arrivalTimes.setBounds(50,260,350,30);
        arrivalTimes.setFont(f);
        add(arrivalTimes);

        min.setBounds(50,290,50,30);
        min.setFont(f);
        add(min);

        minArriveTime.setBounds(100,290,50,30);
        minArriveTime.setFont(f);
        add(minArriveTime);

        max.setBounds(200,290,50,30);
        max.setFont(f);
        add(max);

        maxArriveTime.setBounds(250,290,50,30);
        maxArriveTime.setFont(f);
        add(maxArriveTime);
        ///service times////
        serviceTimes.setBounds(50,320,300,30);
        serviceTimes.setFont(f);
        add(serviceTimes);

        min2.setBounds(50,350,50,30);
        min2.setFont(f);
        add(min2);

        minServiceTime.setBounds(100,350,50,30);
        minServiceTime.setFont(f);
        add(minServiceTime);

        max2.setBounds(200,350,50,30);
        max2.setFont(f);
        add(max2);

        maxServiceTime.setBounds(250,350,50,30);
        maxServiceTime.setFont(f);
        add(maxServiceTime);

        startSimulation.setBounds(120,420,200,30);
        startSimulation.setFont(new Font("Roboto",Font.BOLD,16));
        startSimulation.addActionListener(new SimulationListener(this));
        add(startSimulation);

    }

    public String getOutFile() {
        return outFile;
    }

    public int getNbClients(){
        return Integer.parseInt(nbClientsField.getText());
    }

    public int getNbQueues(){
        return Integer.parseInt(nbQueuesField.getText());
    }

    public int getSimulationTime(){
        return Integer.parseInt(maxSimulationTime.getText());
    }

    public int getMinArrivalTime(){
        return Integer.parseInt(minArriveTime.getText());
    }

    public int getMaxArrivalTime(){
        return Integer.parseInt(maxArriveTime.getText());
    }

    public int getMinServiceTime(){
        return Integer.parseInt(minServiceTime.getText());
    }

    public int getMaxServiceTime(){
        return Integer.parseInt(maxServiceTime.getText());
    }
}
