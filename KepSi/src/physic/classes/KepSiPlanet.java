package physic.classes;

import java.awt.Color;
import java.awt.Graphics2D;

import model.KepSiModel;
import physic.KepSiKeplerObject;
import physic.KepSiVector;
import view.Drawable;

public class KepSiPlanet extends KepSiKeplerObject implements Drawable {

	Color orbitColor, planetColor, soiColor;
	double diameter;

	public KepSiPlanet(KepSiVector position, KepSiVector velocity, double mass, KepSiKeplerObject parent, Color color,
			double diameter, String name) {
		super(position, velocity, mass, parent, name);
		// TODO Auto-generated constructor stub
		this.planetColor = color;
		this.orbitColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 120);
		this.soiColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 60);
		this.diameter = diameter;
	}

	public Color getOrbitColor() {
		return orbitColor;
	}

	public void setOrbitColor(Color orbitColor) {
		this.orbitColor = orbitColor;
	}

	public Color getPlanetColor() {
		return planetColor;
	}

	public void setPlanetColor(Color planetColor) {
		this.planetColor = planetColor;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	@Override
	public void draw(Graphics2D g2, double xC, double yC, KepSiModel model, KepSiVector center) {
		// TODO Auto-generated method stub
		if (this.getOrbit() != null) {
			g2.setColor(orbitColor);
			double xi = this.getOrbit().getPoint(Math.toRadians(0)).getX() + this.getParent().getPosition().getX();
			double yi = this.getOrbit().getPoint(Math.toRadians(0)).getY() + this.getParent().getPosition().getY();
			xi = (xC + (xi - center.getX()) / model.getZoom());
			yi = (yC + (yi - center.getY()) / model.getZoom());
			for (int i = 0; i < 361; i++) {
				// values of the point
				double x = this.getOrbit().getPoint(Math.toRadians(i + 1)).getX()
						+ this.getParent().getPosition().getX();
				double y = this.getOrbit().getPoint(Math.toRadians(i + 1)).getY()
						+ this.getParent().getPosition().getY();
				// "converted values" (zoom, center, etc.)

				double x1 = (xC + (x - center.getX()) / model.getZoom());
				double y1 = (yC + (y - center.getY()) / model.getZoom());

				g2.drawLine((int) xi, (int) yi, (int) x1, (int) y1);
				xi = x1;
				yi = y1;
			}
		}
		g2.setColor(planetColor);
		double x = xC + (this.getPosition().getX() / model.getZoom() - center.getX() / model.getZoom());
		double y = yC + (this.getPosition().getY() / model.getZoom() - center.getY() / model.getZoom());
		if ((int) (diameter / model.getZoom()) >= 2) {
			g2.fillOval((int) (x - (diameter / model.getZoom()) / 2), (int) (y - (diameter / model.getZoom()) / 2),
					(int) (diameter / model.getZoom()), (int) (diameter / model.getZoom()));
		} else {
			g2.drawOval((int) x - 3, (int) y - 3, 6, 6);
		}
		// g2.drawLine((int)x, (int)y, (int)(x+this.getVelocity().getX()),
		// (int)(y+this.getVelocity().getY()));
		g2.setColor(soiColor);
		double xsoi = xC + (this.getPosition().getX() / model.getZoom() - center.getX() / model.getZoom())
				- (getSoi() / model.getZoom());
		double ysoi = yC + (this.getPosition().getY() / model.getZoom() - center.getY() / model.getZoom())
				- (getSoi() / model.getZoom());
		g2.fillOval((int) xsoi, (int) ysoi, (int) (getSoi() * 2 / model.getZoom()),
				(int) (getSoi() * 2 / model.getZoom()));

	}

}
