package physic.classes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.KepSiModel;
import physic.KepSiKeplerObject;
import physic.KepSiKeplerOrbit;
import physic.KepSiNewtonObject;
import physic.KepSiVector;
import view.Drawable;

// object moving on kepler-orbits with 
public class KepSiTestSat {

	private ArrayList<KepSiKeplerOrbit> path;

	// list of all Planets
	private ArrayList<KepSiKeplerObject> planets;
	private KepSiKeplerObject parent;

	public void update(double time) {

	}

}
