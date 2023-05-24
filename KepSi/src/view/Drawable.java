package view;

import java.awt.Graphics2D;

import model.KepSiModel;
import physic.KepSiVector;

public interface Drawable {
	abstract void draw(Graphics2D g2, double xC, double yC, KepSiModel model, KepSiVector center);
}
