package physic.classes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.KepSiModel;
import physic.KepSiKeplerObject;
import physic.KepSiNewtonObject;
import physic.KepSiVector;
import view.Drawable;

public class KepSiSatellite extends KepSiNewtonObject implements Drawable {

	public KepSiSatellite(double mass, KepSiVector position, KepSiVector velocity, KepSiModel model) {
		super(mass, position, velocity, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2, double xC, double yC, KepSiModel model, KepSiVector center) {
		// TODO Auto-generated method stub
		double x = xC + (this.getPosition().getX() - center.getX()) / model.getZoom();
		double y = yC + (this.getPosition().getY() - center.getY()) / model.getZoom();

		g2.setColor(Color.white);
		g2.fillOval((int) (x - 2), (int) (y - 2), 4, 4);
		g2.drawOval((int) (x - 4), (int) (y - 4), 8, 8);
		g2.setColor(new Color(255, 255, 255, 120));
		g2.drawLine((int) x, (int) y,
				(int) (x + KepSiVector.subtract(this.getVelocity(), this.getParent().getVelocity()).getX() / 1000),
				(int) (y + KepSiVector.subtract(this.getVelocity(), this.getParent().getVelocity()).getY() / 1000));
		g2.setColor(Color.green);
		g2.drawLine((int) x, (int) y, (int) (x + this.getForceDraw().getX()), (int) (y + this.getForceDraw().getY()));

	}
}
