package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.KepSiFrame;

public class KepSiStopSim implements ActionListener {
	private KepSiFrame frame;

	public KepSiStopSim(KepSiFrame frame) {
		this.frame = frame;
		this.frame.getStopButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.getModel().setSimulate(false);
	}
}
