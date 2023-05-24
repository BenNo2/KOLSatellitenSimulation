package model;

import java.util.ArrayList;

import physic.KepSiKeplerObject;
import physic.KepSiNewtonObject;
import physic.KepSiVector;
import physic.classes.KepSiSatTrail;
import physic.classes.KepSiSatellite;
import physic.classes.KepSiTestSat;
import physic.classes.KepSiTrailPos;
import view.Drawable;

public class KepSiModel {
	ArrayList<KepsiModelListener> listeners = new ArrayList<>();

	private boolean simulate;

	private double time = 0;
	private double timeFactor = 1;
	private double zoom = 1000000;

	private int focus = 0;

	ArrayList<KepSiKeplerObject> keplerObjects = new ArrayList<>();
	ArrayList<Drawable> drawables = new ArrayList<>();

	private boolean trailenabled = false;
	KepSiSatTrail trail = new KepSiSatTrail();

	KepSiNewtonObject sat;

	KepSiTestSat testSat;

	public KepSiModel() {
		drawables.add(trail);
	}

	public boolean isTrailenabled() {
		return trailenabled;
	}

	public void setTrailenabled(boolean trailenabled) {
		this.trailenabled = trailenabled;
	}

	public KepSiSatTrail getTrail() {
		return trail;
	}

	public void setTrail(KepSiSatTrail trail) {
		this.trail = trail;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
		notifyListeners();
	}

	public ArrayList<KepSiKeplerObject> getKeplerObjects() {
		return keplerObjects;
	}

	public void setKeplerObjects(ArrayList<KepSiKeplerObject> keplerObjects) {
		this.keplerObjects = keplerObjects;
	}

	public KepSiNewtonObject getSat() {
		return sat;
	}

	public void setSat(KepSiNewtonObject sat) {
		this.sat = sat;
	}

	public boolean isSimulate() {
		return simulate;
	}

	public void setSimulate(boolean simulate) {
		this.simulate = simulate;
		notifyListeners();
	}

	public void addListener(KepsiModelListener listener) {
		this.listeners.add(listener);
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
		// notifyListeners();
	}

	public double getTimeFactor() {
		return timeFactor;
	}

	public void setTimeFactor(double timeFactor) {
		this.timeFactor = timeFactor;
	}

	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}

	public void setDrawables(ArrayList<Drawable> drawables) {
		this.drawables = drawables;
	}

	public int getFocus() {
		return focus;
	}

	public void setFocus(int focus) {
		this.focus = focus;
		notifyListeners();
	}

	public KepSiTestSat getTestSat() {
		return testSat;
	}

	public void setTestSat(KepSiTestSat testSat) {
		this.testSat = testSat;
	}

	public void update() {
		notifyListeners();
	}

	private void notifyListeners() {
		for (KepsiModelListener listener : listeners) {
			listener.modelChanged();
		}
	}
}
