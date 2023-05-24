package physic.classes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.KepSiModel;
import physic.KepSiKeplerObject;
import physic.KepSiVector;
import view.Drawable;

public class KepSiSatTrail implements Drawable {
	ArrayList<KepSiTrailPos> positions = new ArrayList();

	public void addPosition(KepSiVector position, KepSiKeplerObject parent) {
		KepSiTrailPos trailPos = new KepSiTrailPos(KepSiVector.subtract(position, parent.getPosition()), parent);
		positions.add(trailPos);
	}

	public void clearTrail() {
		this.positions = new ArrayList();
	}

	public ArrayList<KepSiTrailPos> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<KepSiTrailPos> positions) {
		this.positions = positions;
	}

	@Override
	public void draw(Graphics2D g2, double xC, double yC, KepSiModel model, KepSiVector center) {
		// TODO Auto-generated method stub
		g2.setColor(Color.white);
		double x1 = 0, y1 = 0;
		double rate;
		if (positions.size() != 0) {
			double x2 = KepSiVector.add(positions.get(0).position, positions.get(0).parent.getPosition()).getX();
			double y2 = KepSiVector.add(positions.get(0).position, positions.get(0).parent.getPosition()).getY();
			x2 = (xC + (x2 - center.getX()) / model.getZoom());
			y2 = (yC + (y2 - center.getY()) / model.getZoom());
			for (int i = 1; i < positions.size(); i++) {
				double x = KepSiVector.add(positions.get(i).position, positions.get(i).parent.getPosition()).getX();
				double y = KepSiVector.add(positions.get(i).position, positions.get(i).parent.getPosition()).getY();
				x1 = (xC + (x - center.getX()) / model.getZoom());
				y1 = (yC + (y - center.getY()) / model.getZoom());
				rate = positions.get(i).position.getLength() - positions.get(i - 1).position.getLength();
				/*
				 * if(rate < 0) { g2.setColor(Color.red); } else { g2.setColor(Color.green); }
				 */
				if (positions.get(i).parent == positions.get(i - 1).parent)
					g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
				x2 = x1;
				y2 = y1;

			}
		}
	}
}
