package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.security.KeyStore.PrivateKeyEntry;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.KepSiRun;
import model.KepSiModel;
import model.KepsiModelListener;
import physic.KepSiVector;

public class KepSiFrame extends JFrame implements KepsiModelListener {
	private JPanel controlPanel, infoPanel;
	private KepSiPanel simPanel;
	private JButton startButton;
	private JButton stopButton;
	private JButton trailStartButton, trailClearButton;
	JLabel parentLabel, relativeSpeedLabel, globalSpeedLabel, distanceLabel, timeZoomLabel, timeLabel, angleLabel;
	JTextField maneuverField;
	JButton maneuverButton;

	private DecimalFormat doubleFormat = new DecimalFormat("0.00");

	int i = 0;
	private KepSiModel model;

	public KepSiFrame(KepSiModel model) {
		this.model = model;

		initUI();

		model.addListener(this);
	}

	private void initUI() {

		this.setSize(800, 400);
		this.setTitle("KepSi");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		infoPanel = createInfoPanel();
		controlPanel = createControlPanel();

		simPanel = new KepSiPanel(model);

		simPanel.start();
		panel.add(simPanel, BorderLayout.CENTER);
		panel.add(controlPanel, BorderLayout.EAST);
		panel.add(infoPanel, BorderLayout.SOUTH);

		this.getContentPane().add(panel);
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		JPanel startStopPanel = new JPanel();

		startButton = new JButton();
		startButton.setText("Start");
		startButton.setSize(100, 30);

		stopButton = new JButton();
		stopButton.setText("Stop");
		stopButton.setSize(100, 30);

		maneuverField = new JTextField();
		maneuverButton = new JButton("maneuver");
		trailClearButton = new JButton("clear Trail");

		startStopPanel.setLayout(new BoxLayout(startStopPanel, BoxLayout.X_AXIS));
		startStopPanel.add(startButton);
		startStopPanel.add(Box.createHorizontalGlue());
		startStopPanel.add(stopButton);

		controlPanel.add(startStopPanel);
		controlPanel.add(maneuverField);
		controlPanel.add(maneuverButton);
		controlPanel.add(trailClearButton);
		// controlPanel.add(helpButton);
		return controlPanel;

	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, 1));
		parentLabel = new JLabel("Parent: " + model.getSat().getParent().getName());
		relativeSpeedLabel = new JLabel("Relative Speed: "
				+ doubleFormat.format(KepSiVector
						.subtract(model.getSat().getVelocity(), model.getSat().getParent().getVelocity()).getLength())
				+ " m/s");
		globalSpeedLabel = new JLabel(
				"Global Speed: " + doubleFormat.format(model.getSat().getVelocity().getLength()) + " m/s");
		timeLabel = new JLabel("Elapsed time: " + doubleFormat.format(model.getTime()));
		timeZoomLabel = new JLabel("Timefactor: " + doubleFormat.format(model.getTimeFactor()));
		distanceLabel = new JLabel("Distance: " + KepSiVector
				.subtract(model.getSat().getPosition(), model.getSat().getParent().getPosition()).getLength() + " m");
		angleLabel = new JLabel("Angle: " + Math.toDegrees(KepSiVector.getAngle(
				KepSiVector.subtract(this.getModel().getSat().getPosition(),
						this.getModel().getSat().getParent().getPosition()),
				this.getModel().getSat().getParent().getVelocity())));
		infoPanel.add(parentLabel);
		infoPanel.add(relativeSpeedLabel);
		infoPanel.add(globalSpeedLabel);
		infoPanel.add(timeLabel);
		infoPanel.add(timeZoomLabel);
		infoPanel.add(distanceLabel);
		infoPanel.add(angleLabel);
		return infoPanel;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JButton getTrailStartButton() {
		return trailStartButton;
	}

	public void setTrailStartButton(JButton trailStartButton) {
		this.trailStartButton = trailStartButton;
	}

	public JButton getTrailClearButton() {
		return trailClearButton;
	}

	public void setTrailClearButton(JButton trailClearButton) {
		this.trailClearButton = trailClearButton;
	}

	public KepSiModel getModel() {
		return model;
	}

	public void setModel(KepSiModel model) {
		this.model = model;
	}

	public KepSiPanel getSimPanel() {
		return simPanel;
	}

	public void setSimPanel(KepSiPanel simPanel) {
		this.simPanel = simPanel;
	}

	public JTextField getManeuverField() {
		return maneuverField;
	}

	public void setManeuverField(JTextField maneuverField) {
		this.maneuverField = maneuverField;
	}

	public JButton getManeuverButton() {
		return maneuverButton;
	}

	public void setManeuverButton(JButton maneuverButton) {
		this.maneuverButton = maneuverButton;
	}

	@Override
	public void modelChanged() {

		// TODO Auto-generated method stuff
		parentLabel.setText("Parent: " + model.getSat().getParent().getName());
		relativeSpeedLabel.setText("Relative Speed: "
				+ doubleFormat.format(KepSiVector
						.subtract(model.getSat().getVelocity(), model.getSat().getParent().getVelocity()).getLength())
				+ " m/s");
		globalSpeedLabel
				.setText("Global Speed: " + doubleFormat.format(model.getSat().getVelocity().getLength()) + " m/s");
		distanceLabel.setText("Distance: " + KepSiVector
				.subtract(model.getSat().getPosition(), model.getSat().getParent().getPosition()).getLength() + " m");
		timeLabel.setText("Elapsed time: " + doubleFormat.format(model.getTime()) + " s");
		timeZoomLabel.setText("Timefactor: " + doubleFormat.format(model.getTimeFactor()));
		angleLabel.setText("Angle: " + Math.toDegrees(KepSiVector.getAngle(
				KepSiVector.subtract(this.getModel().getSat().getPosition(),
						this.getModel().getSat().getParent().getPosition()),
				this.getModel().getSat().getParent().getVelocity())));

	}
}
