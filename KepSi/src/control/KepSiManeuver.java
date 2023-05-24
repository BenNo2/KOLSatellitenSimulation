package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import physic.KepSiVector;
import view.KepSiFrame;

public class KepSiManeuver implements ActionListener {
	KepSiFrame frame;

	public KepSiManeuver(KepSiFrame frame) {
		this.frame = frame;
		frame.getManeuverButton().addActionListener(this);
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(frame.getModel().getSat().getVelocity());
		frame.getModel().getSat().getParent().update(frame.getModel().getTime());
		KepSiVector satV = frame.getModel().getSat().getVelocity();
		KepSiVector parentV = frame.getModel().getSat().getParent().getVelocity();
		KepSiVector deltaVNorm = KepSiVector.normalize(KepSiVector.subtract(satV, parentV));
		KepSiVector addSpeed = KepSiVector.multiply(deltaVNorm, Double.parseDouble(frame.getManeuverField().getText()));
		frame.getModel().getSat().setVelocity(KepSiVector.add(satV, addSpeed));
		System.out.println(frame.getModel().getSat().getVelocity());
		frame.getModel().update();
	}
}
