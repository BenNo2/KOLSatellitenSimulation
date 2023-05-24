package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import model.KepSiModel;
import view.KepSiFrame;

public class KepSiStartSim implements ActionListener {
	private KepSiFrame frame;

	public KepSiStartSim(KepSiFrame frame) {
		this.frame = frame;
		this.frame.getStartButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.getModel().setSimulate(true);
	}

}
