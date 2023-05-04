import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private static final long serialversionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldN;
    private JTextField textFieldQ;
    private JTextField textFieldSimulationInterval;
    private JTextField textFieldMinArrivalTime;
    private JTextField textFieldMaxArrivalTime;
    private JTextField textFieldMinServiceTime;
    private JTextField textFieldMaxServiceTime;
    private JTextArea textArea;
    private JPanel panel;
    private int WIDTH = 600, HEIGHT = 600;

    private boolean simulationStarted = false;

    public SimulationFrame(){
        panel = new JPanel();
        this.add(panel);
        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNumberOfClients = new JLabel("Number of Clients:");
        lblNumberOfClients.setBounds(30, 30, 150, 25);
        contentPane.add(lblNumberOfClients);

        textFieldN = new JTextField();
        textFieldN.setBounds(200, 30, 150, 25);
        contentPane.add(textFieldN);
        textFieldN.setColumns(10);

        JLabel lblNumberOfQueues = new JLabel("Number of Queues:");
        lblNumberOfQueues.setBounds(30, 80, 150, 25);
        contentPane.add(lblNumberOfQueues);

        textFieldQ = new JTextField();
        textFieldQ.setBounds(200, 80, 150, 25);
        contentPane.add(textFieldQ);
        textFieldQ.setColumns(10);

        JLabel lblSimulationInterval = new JLabel("Simulation Interval:");
        lblSimulationInterval.setBounds(30, 130, 150, 25);
        contentPane.add(lblSimulationInterval);

        textFieldSimulationInterval = new JTextField();
        textFieldSimulationInterval.setBounds(200, 130, 150, 25);
        contentPane.add(textFieldSimulationInterval);
        textFieldSimulationInterval.setColumns(10);

        JLabel lblMinArrivalTime = new JLabel("Minimum Arrival Time:");
        lblMinArrivalTime.setBounds(30, 180, 150, 25);
        contentPane.add(lblMinArrivalTime);

        textFieldMinArrivalTime = new JTextField();
        textFieldMinArrivalTime.setBounds(200, 180, 150, 25);
        contentPane.add(textFieldMinArrivalTime);
        textFieldMinArrivalTime.setColumns(10);

        JLabel lblMaxArrivalTime = new JLabel("Maximum Arrival Time:");
        lblMaxArrivalTime.setBounds(30, 230, 150, 25);
        contentPane.add(lblMaxArrivalTime);

        textFieldMaxArrivalTime = new JTextField();
        textFieldMaxArrivalTime.setBounds(200, 230, 150, 25);
        contentPane.add(textFieldMaxArrivalTime);
        textFieldMaxArrivalTime.setColumns(10);

        JLabel lblMinServiceTime = new JLabel("Minimum Service Time:");
        lblMinServiceTime.setBounds(30, 280, 150, 25);
        contentPane.add(lblMinServiceTime);

        textFieldMinServiceTime = new JTextField();
        textFieldMinServiceTime.setBounds(200, 280, 150, 25);
        contentPane.add(textFieldMinServiceTime);
        textFieldMinServiceTime.setColumns(10);

        JLabel lblMaxServiceTime = new JLabel("Maximum Service Time:");
        lblMaxServiceTime.setBounds(30, 330, 150, 25);
        contentPane.add(lblMaxServiceTime);

        textFieldMaxServiceTime = new JTextField();
        textFieldMaxServiceTime.setBounds(200, 330, 150, 25);
        contentPane.add(textFieldMaxServiceTime);
        textFieldMaxServiceTime.setColumns(10);

        JButton btnSimulate = new JButton("Simulate");
        btnSimulate.addActionListener(e -> {
            // display results in text area
            simulationStarted = true;
        });
        btnSimulate.setBounds(500, 200, 100, 50);
        contentPane.add(btnSimulate);
    }

    public boolean isSimulationStarted() {
        return simulationStarted;
    }

    public JTextField getTextFieldN() {
        return textFieldN;
    }

    public JTextField getTextFieldQ() {
        return textFieldQ;
    }

    public JTextField getTextFieldSimulationInterval() {
        return textFieldSimulationInterval;
    }

    public JTextField getTextFieldMinArrivalTime() {
        return textFieldMinArrivalTime;
    }

    public JTextField getTextFieldMaxArrivalTime() {
        return textFieldMaxArrivalTime;
    }

    public JTextField getTextFieldMinServiceTime() {
        return textFieldMinServiceTime;
    }

    public JTextField getTextFieldMaxServiceTime() {
        return textFieldMaxServiceTime;
    }

    public void displayData(Task[][] tasks){

    }
}
