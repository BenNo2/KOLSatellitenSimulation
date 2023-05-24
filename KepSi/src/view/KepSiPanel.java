package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Timer;

import javax.print.attribute.SupportedValuesAttribute;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import model.KepSiModel;
import model.KepsiModelListener;
import physic.KepSiKeplerObject;
import physic.KepSiVector;

public class KepSiPanel extends JPanel implements Runnable {

	Thread runThread;

	private KepSiModel model;

	public KepSiPanel(KepSiModel model) {
		this.model = model;
		this.setVisible(true);
		this.runThread = new Thread(this);
	}

	public KepSiModel getModel() {
		return model;
	}

	public void setModel(KepSiModel model) {
		this.model = model;
	}

	public void start() {
		runThread.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.setBackground(Color.black);
		// x and y values for the center of the panel
		double xC = this.getWidth() / 2;
		double yC = this.getHeight() / 2;
		for (Drawable drawable : model.getDrawables()) {
			drawable.draw(g2, xC, yC, model, model.getKeplerObjects().get(model.getFocus()).getPosition());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}

	}
}
