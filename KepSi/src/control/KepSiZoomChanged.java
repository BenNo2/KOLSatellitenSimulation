package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.KepSiFrame;

public class KepSiZoomChanged implements KeyListener {
	KepSiFrame frame;

	public KepSiZoomChanged(KepSiFrame frame) {
		this.frame = frame;
		this.frame.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_DOWN) {
			frame.getModel().setZoom(frame.getModel().getZoom() * 1.5);
		} else if (key == KeyEvent.VK_UP) {
			frame.getModel().setZoom(frame.getModel().getZoom() / 1.5);
		} else if (key == KeyEvent.VK_RIGHT) {
			if (frame.getModel().getFocus() < frame.getModel().getDrawables().size() - 3) {
				frame.getModel().setFocus(frame.getModel().getFocus() + 1);
				System.out.println(frame.getModel().getFocus());
			} else {
				frame.getModel().setFocus(0);
			}

		} else if (key == KeyEvent.VK_LEFT) {

			if (frame.getModel().getFocus() > 0) {
				frame.getModel().setFocus(frame.getModel().getFocus() - 1);
			} else {
				frame.getModel().setFocus(frame.getModel().getDrawables().size() - 3);
			}
		} else if (key == KeyEvent.VK_COMMA) {

			frame.getModel().setTimeFactor(frame.getModel().getTimeFactor() * 2);
		} else if (key == KeyEvent.VK_PERIOD) {
			frame.getModel().setTimeFactor(frame.getModel().getTimeFactor() / 2);
		} else if (key == KeyEvent.VK_MINUS) {
			frame.getModel().setTimeFactor(frame.getModel().getTimeFactor() * -1);

		} else if (key == KeyEvent.VK_F) {
			frame.getModel().setTrailenabled(!frame.getModel().isTrailenabled());
		}
		frame.getModel().update();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("a");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
