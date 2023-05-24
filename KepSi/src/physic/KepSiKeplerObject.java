package physic;

public class KepSiKeplerObject {
	// "Parent" planet which the orbit is based on
	KepSiKeplerObject parent;
	private final double micro, soi, mass;
	private double offset;
	private int hierarchy;
	private final KepSiKeplerOrbit orbit;
	private String name;

	private KepSiVector velocity, position;
	private final KepSiVector p0, v0;
	static final double G = 6.674e-11;

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public double getSoi() {
		return soi;
	}

	public KepSiKeplerObject getParent() {
		return parent;
	}

	public double getMicro() {
		return micro;
	}

	public KepSiKeplerOrbit getOrbit() {
		return orbit;
	}

	public int getHierarchy() {
		return hierarchy;
	}

	public KepSiVector getVelocity() {
		return velocity;
	}

	public void setVelocity(KepSiVector velocity) {
		this.velocity = velocity;
	}

	public KepSiVector getPosition() {
		return position;
	}

	public void setPosition(KepSiVector position) {
		this.position = position;
	}

	public double getMass() {
		return mass;
	}

	public KepSiVector getP0() {
		return p0;
	}

	public KepSiVector getV0() {
		return v0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// page 62/63
	public KepSiVector getVelocity(double time) {
		if (orbit != null) {
			KepSiVector vr = KepSiVector.rotateRad(KepSiVector.normalize(orbit.getPoint(0)),
					orbit.calculateTrueAnomaly(time + offset) + Math.PI / 2);
			double vrMagnitude = Math.sqrt(micro / orbit.getP()) * orbit.getEccentricity().getLength()
					* Math.sin(orbit.calculateTrueAnomaly(time + offset));
			vr = KepSiVector.multiply(vr, vrMagnitude);

			KepSiVector v0 = KepSiVector.rotateRad(KepSiVector.normalize(orbit.getPoint(0)),
					orbit.calculateTrueAnomaly(time + offset));
			double v0Magnitude = (orbit.getH() / orbit.getP())
					* (1 + orbit.getEccentricity().getLength() * Math.cos(orbit.calculateTrueAnomaly(time + offset)));
			v0 = KepSiVector.multiply(v0, v0Magnitude);
			// somehow the magnitude is correct but the direction is off by exactly 90
			// degrees
			// -> rotate the vector by 90 degrees
			v0 = KepSiVector.rotateRad(v0, time + orbit.getPeriapsis() - Math.PI / 2);
			// return orbit.getVelocity(time, offset);
			v0 = KepSiVector.add(v0, parent.getVelocity(time));
			return v0;
		} else {
			return new KepSiVector();
		}
	}

	public KepSiKeplerObject(KepSiVector position, KepSiVector velocity, double mass, KepSiKeplerObject parent,
			String name) {
		this.parent = parent;
		this.name = name;
		this.offset = 0;
		this.micro = mass * G;
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
		this.p0 = position;
		this.v0 = velocity;
		if (this.getParent() != null) {
			this.orbit = new KepSiKeplerOrbit(this, getParent());
			this.soi = orbit.getA() * Math.pow(this.micro / parent.getMicro(), 0.4);
			KepSiKeplerObject checkObject = this.getParent();
			int hierarchy = 1;
			while (checkObject.getParent() != null) {
				checkObject = checkObject.getParent();
				hierarchy++;
			}
			this.hierarchy = hierarchy;
		} else {
			hierarchy = 0;
			soi = Double.MAX_VALUE;
			this.orbit = null;
		}
	}

	public void update(double time) {
		KepSiVector position;
		KepSiVector velocity;
		if (this.orbit != null) {
			position = this.orbit.getPoint(this.orbit.calculateTrueAnomaly(time + offset));
			position = KepSiVector.add(position, parent.getPosition());
			velocity = this.getVelocity(this.orbit.calculateTrueAnomaly(time));
		} else {
			position = new KepSiVector();
			velocity = new KepSiVector();
		}
		this.setVelocity(velocity);
		this.setPosition(position);
	}

}
