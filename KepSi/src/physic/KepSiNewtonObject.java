package physic;

import java.util.ArrayList;

import model.KepSiModel;

public class KepSiNewtonObject {
	private KepSiVector position, velocity, acceleration, force;

	private double mass;
	private double elaptime = 0;
	private KepSiModel model;
	private KepSiKeplerObject parent;
	private KepSiVector forceDraw;

	public KepSiNewtonObject(double mass, KepSiVector position, KepSiVector velocity, KepSiModel model) {

		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.force = new KepSiVector();
		this.acceleration = new KepSiVector();
		this.model = model;
		setParent();
		forceDraw = new KepSiVector();
	}

	public KepSiKeplerObject getParent() {
		return parent;
	}

	public void setParent() {

		KepSiKeplerObject obj = model.getKeplerObjects().get(0);
		for (KepSiKeplerObject a : model.getKeplerObjects()) {
			if (a.getHierarchy() > obj.getHierarchy()
					&& KepSiVector.getDistance(this.position, a.getPosition()) < a.getSoi())
				obj = a;
		}
		parent = obj;
	}

	public KepSiVector getPosition() {
		return position;
	}

	public void setPosition(KepSiVector position) {
		this.position = position;
	}

	public KepSiVector getVelocity() {
		return velocity;
	}

	public void setVelocity(KepSiVector velocity) {
		this.velocity = velocity;
	}

	public KepSiVector getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(KepSiVector acceleration) {
		this.acceleration = acceleration;
	}

	public KepSiVector getForce() {
		return force;
	}

	public void setForce(KepSiVector force) {
		this.force = force;
	}

	public KepSiVector getForceDraw() {
		return forceDraw;
	}

	public void setForceDraw(KepSiVector forceDraw) {
		this.forceDraw = forceDraw;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public void applyForce(double timeStep) {

		position = KepSiVector.add(position, KepSiVector.multiply(velocity, timeStep));

	}

	public void gravity(double timeStep, KepSiKeplerObject obj) {
		double magnitude = 6.6743e-11 * (this.mass * obj.getMass())
				/ Math.pow(KepSiVector.subtract(obj.getPosition(), position).getLength(), 2);
		force = KepSiVector.normalize(KepSiVector.subtract(obj.getPosition(), position));
		force = KepSiVector.multiply(force, magnitude);
		if (parent == obj) {
			forceDraw = force;
		}
		acceleration = KepSiVector.multiply(force, 1 / mass);
		elaptime += timeStep;
		velocity = KepSiVector.add(velocity, KepSiVector.multiply(acceleration, timeStep));
	}

}
