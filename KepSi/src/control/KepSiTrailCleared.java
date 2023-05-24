package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.KepSiModel;
import view.KepSiFrame;

public class KepSiTrailCleared implements ActionListener {

	KepSiFrame frame;

	public KepSiTrailCleared(KepSiFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		frame.getTrailClearButton().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.getModel().getTrail().clearTrail();
	}

}
