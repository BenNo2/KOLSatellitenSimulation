package control;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.KepSiModel;
import physic.KepSiKeplerObject;
import physic.KepSiNewtonObject;
import physic.KepSiVector;
import physic.classes.KepSiPlanet;
import physic.classes.KepSiSatellite;
import physic.classes.KepSiTestSat;
import view.Drawable;
import view.KepSiFrame;

public class KepSiShow {

	ArrayList<KepSiKeplerObject> keplerObjects = new ArrayList<>();
	ArrayList<Drawable> drawables = new ArrayList<>();
	KepSiSatellite sat;
	KepSiTestSat testSat;
	KepSiModel model;
	KepSiFrame frame;

	public void show() {

		model = new KepSiModel();
		initLists();
		model.setKeplerObjects(keplerObjects);
		sat = new KepSiSatellite(1, new KepSiVector(0, 1.5e11 + 6878000), new KepSiVector(29742 + 7612.41, 0), model);
		drawables.add(sat);
		model.setDrawables(drawables);
		model.getDrawables().add(model.getTrail());
		model.setSat(sat);

		frame = new KepSiFrame(model);
		KepSiStartSim startSim = new KepSiStartSim(frame);
		KepSiStopSim stopSim = new KepSiStopSim(frame);
		KepSiRun control = new KepSiRun(frame);
		KepSiZoomChanged zoomChanged = new KepSiZoomChanged(frame);
		KepSiManeuver maneuver = new KepSiManeuver(frame);
		KepSiTrailCleared trailCleared = new KepSiTrailCleared(frame);
		frame.setVisible(true);
	}

	public void initLists() {

		KepSiPlanet sun = new KepSiPlanet(new KepSiVector(0, 0), new KepSiVector(), 1.988e30, null, Color.yellow,
				1392700000, "sun");
		KepSiPlanet mercury = new KepSiPlanet(new KepSiVector(0, 5.7e10), new KepSiVector(47360, 0), 3.301e23, sun,
				Color.gray, 4879400, "mercury");
		KepSiPlanet venus = new KepSiPlanet(new KepSiVector(0, 10.816e10), new KepSiVector(35020, 0), 4.875e24, sun,
				Color.white.darker(), 12103600, "venus");
		KepSiPlanet earth = new KepSiPlanet(new KepSiVector(0, 1.5e11), new KepSiVector(29742, 0), 5.972e24, sun,
				Color.blue, 12600000, "earth");
		KepSiPlanet moon = new KepSiPlanet(new KepSiVector(0, 1.5e11 + 384000000), new KepSiVector(1022, 0), 7.342e22,
				earth, Color.gray, 3474000, "moon");

		KepSiPlanet mars = new KepSiPlanet(new KepSiVector(0, 2.2799e11), new KepSiVector(24123.671, 0), 6.417e23, sun,
				Color.red.brighter(), 6792000, "mars");
		KepSiPlanet test = new KepSiPlanet(new KepSiVector(0, 2.2799e11 + 111380000), new KepSiVector(620.09, 0),
				7.342e22, mars, Color.gray, 3474000, "xD");
		KepSiPlanet phobos = new KepSiPlanet(new KepSiVector(0, 2.2799e11 + 9378000), new KepSiVector(2139, 0),
				1.072e16, mars, Color.gray, 26800, "phobos");
		KepSiPlanet deimos = new KepSiPlanet(new KepSiVector(0, 2.2799e11 + 23459000), new KepSiVector(1351, 0), 1.8e15,
				mars, Color.gray, 15000, "deimos");

		KepSiPlanet jupiter = new KepSiPlanet(new KepSiVector(0, 7.78507e+11), new KepSiVector(13060, 0), 1.899e27, sun,
				new Color(225, 198, 153), 142984000, "jupiter");
		KepSiPlanet saturn = new KepSiPlanet(new KepSiVector(0, 1.4334e12), new KepSiVector(9680, 0), 5.683e26, sun,
				Color.yellow, 120536000, "saturn");
		KepSiPlanet uranus = new KepSiPlanet(new KepSiVector(0, 2.8724e12), new KepSiVector(6810, 0), 8.681e25, sun,
				Color.blue.brighter(), 51118000, "uranus");
		KepSiPlanet neptune = new KepSiPlanet(new KepSiVector(0, 4.495e12), new KepSiVector(5430, 0), 1.024e26, sun,
				Color.blue.darker(), 49528000, "neptune");

		System.out.println("Bahnparameter: " + test.getOrbit().getP() + " m		 Exzentrizität: "
				+ test.getOrbit().getEccentricity().getLength());
		System.out.println(test.getOrbit().getOrbitalPeriod());
		keplerObjects.add(sun);
		keplerObjects.add(mercury);
		keplerObjects.add(venus);
		keplerObjects.add(earth);
		keplerObjects.add(moon);
		keplerObjects.add(mars);
		keplerObjects.add(phobos);
		keplerObjects.add(deimos);
		keplerObjects.add(jupiter);
		keplerObjects.add(saturn);
		keplerObjects.add(uranus);
		keplerObjects.add(neptune);
		keplerObjects.add(phobos);

		// moon.setOffset(864000);
		mars.setOffset(7282334.627);
		drawables.add(sun);
		drawables.add(mercury);
		drawables.add(venus);
		drawables.add(earth);

		drawables.add(moon);
		drawables.add(mars);
		drawables.add(phobos);
		drawables.add(deimos);
		drawables.add(jupiter);
		drawables.add(saturn);
		drawables.add(uranus);
		drawables.add(neptune);

	}
}
