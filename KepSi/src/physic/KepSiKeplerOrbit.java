package physic;

public class KepSiKeplerOrbit {
	// semimajor axis
	private double a;
	// semiminor axis
	private double b;
	// normaldistance
	private double p;
	// hamiltonian integral
	private double h;
	// micro
	private double micro;
	// orbital period in seconds
	private double orbitalPeriod;
	// direction of travel (true = counterclockwise)
	private boolean direction;
	// true anomaly
	private double trueAnomaly;
	// eccentricity vector
	private KepSiVector eccentricity;
	// argument of periapsis
	private double periapsis;

	public KepSiKeplerOrbit(KepSiKeplerObject child, KepSiKeplerObject parent) {
		init(child, parent);
	}

	// initializes the shape of the orbit, depending on the parent and child
	private void init(KepSiKeplerObject child, KepSiKeplerObject parent) {

		// r0: using the position of the child to simplify values
		KepSiVector r0 = KepSiVector.subtract(child.getP0(), parent.getP0());
		// v0: using the speed of the child to simplify values
		KepSiVector v0 = child.getV0();
		// calculating the value of the moment of inertia, as it always will be along
		// the z-axis
		double h = KepSiVector.CrossProduct2D(r0, v0);
		KepSiVector eccentricity = KepSiVector.CrossProduct3D(v0, h);
		eccentricity = KepSiVector.multiply(eccentricity, 1 / parent.getMicro());
		// KepSiVector.subtract(eccentricity,KepSiVector.normalize(r0));
		eccentricity = new KepSiVector(eccentricity.getX() - KepSiVector.normalize(r0).getX(),
				eccentricity.getY() - KepSiVector.normalize(r0).getY());
		if (eccentricity.getLength() >= 1) {
			System.out.println("error: orbit too eccentric!");
		}
		this.setDirection((h > 0) ? true : false);
		// argument of periapsis
		double periapsis = Math.atan2(eccentricity.getY(), eccentricity.getX());
		// normaldistance
		double p = Math.pow(h, 2) / parent.getMicro();
		// semimajor axis
		double a = p / (1 - Math.pow(eccentricity.getLength(), 2));
		double orbitalPeriod = 2 * Math.PI * Math.sqrt(Math.pow(a, 3) / parent.getMicro());

		this.setH(h);
		this.setOrbitalPeriod(orbitalPeriod);
		this.setA(a);
		this.setPeriapsis(periapsis);
		this.setEccentricity(eccentricity);
		this.setP(p);
		this.setMicro(child.getMicro());
	}

	public double getMicro() {
		return micro;
	}

	public void setMicro(double micro) {
		this.micro = micro;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getOrbitalPeriod() {
		return orbitalPeriod;
	}

	public void setOrbitalPeriod(double orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public double getTrueAnomaly() {
		return trueAnomaly;
	}

	public void setTrueAnomaly(double trueAnomaly) {
		this.trueAnomaly = trueAnomaly;
	}

	public KepSiVector getEccentricity() {
		return eccentricity;
	}

	public void setEccentricity(KepSiVector eccentricity) {
		this.eccentricity = eccentricity;
	}

	public double getPeriapsis() {
		return periapsis;
	}

	public void setPeriapsis(double periapsis) {
		this.periapsis = periapsis;
	}

	public void getSpeed(double rad) {

	}

	public KepSiVector getPoint(double rad) {
		KepSiVector point = new KepSiVector();
		double r = this.getP() / (1 + (this.getEccentricity().getLength()) * Math.cos(rad - this.getPeriapsis()));
		point.setX(r);
		point = KepSiVector.rotateRad(point, rad);
		return point;
	}

	// calculating the difference of the true anomaly with the time (pages 71-72)
	public double calculateTrueAnomaly(double time) {
		double dir = (this.isDirection()) ? 1 : -1;
		double meanAnomaly = Math.PI * 2 * (time / this.getOrbitalPeriod()) * dir;
		double eccentricAnomaly = meanAnomaly;
		for (int i = 0; i < 5; i++) {
			eccentricAnomaly = eccentricAnomaly
					- ((eccentricAnomaly - (eccentricity.getLength() * Math.sin(eccentricAnomaly)) - meanAnomaly)
							/ (1 - eccentricity.getLength() * Math.cos(eccentricAnomaly)));
		}
		double anomaly = 2 * Math.atan((Math.sqrt((1 + eccentricity.getLength()) / (1 - eccentricity.getLength()))
				* Math.tan(eccentricAnomaly / 2)));

		return anomaly + this.getPeriapsis();
	}
}
