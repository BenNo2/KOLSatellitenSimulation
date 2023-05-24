package physic;

public class KepSiVector {
	private double x, y;

	public KepSiVector() {
		// TODO Auto-generated constructor stub
		this.x = 0;
		this.y = 0;
	}

	public KepSiVector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;

	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	// length of vector
	public double getLength() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}

	// rotating vector
	public static KepSiVector rotateRad(KepSiVector a, double rad) {
		double x1 = a.getX() * Math.cos(rad) - a.getY() * Math.sin(rad);
		double y1 = a.getX() * Math.sin(rad) + a.getY() * Math.cos(rad);
		return new KepSiVector(x1, y1);
	}

	public static KepSiVector rotateDeg(KepSiVector a, double deg) {
		return KepSiVector.rotateRad(a, Math.toRadians(deg));
	}

	public static KepSiVector add(KepSiVector a, KepSiVector b) {
		return new KepSiVector(a.getX() + b.getX(), a.getY() + b.getY());
	}

	public static KepSiVector multiply(KepSiVector a, double magnitude) {
		return new KepSiVector(a.getX() * magnitude, a.getY() * magnitude);
	}

	public static KepSiVector subtract(KepSiVector a, KepSiVector b) {
		double x = a.getX() - b.getX();
		double y = a.getY() - b.getY();
		return new KepSiVector(x, y);
	}

	static double getDistance(KepSiVector a, KepSiVector b) {
		KepSiVector c = KepSiVector.subtract(a, b);
		return c.getLength();
	}

	// gets the theoretical length of the cross product, as it is always in the
	// z-direction, since the vectors are in 2D-space
	static double CrossProduct2D(KepSiVector a, KepSiVector b) {
		return (a.getX() * b.getY()) - (a.getY() * b.getX());
	}

	// a version of the crossProduct where the cross-product with the z-axis is
	// calculated
	// used mainly in orbital calculations
	static KepSiVector CrossProduct3D(KepSiVector a, double z) {
		double x = a.getY() * z;
		double y = 0 - a.getX() * z;
		KepSiVector crossProduct = new KepSiVector(x, y);
		return crossProduct;
	}

	public static double getAngle(KepSiVector a, KepSiVector b) {
		return Math.acos((a.getX() * b.getX() + a.getY() * b.getY()) / (b.getLength() * a.getLength()));
	}

	public static KepSiVector normalize(KepSiVector a) {
		double x = a.getX() / a.getLength();
		double y = a.getY() / a.getLength();
		return new KepSiVector(x, y);
	}

	public String toString() {
		return "KepSiVector{" + "x=" + x + ", y=" + y + ", length =" + this.getLength() + '}';
	}

}
