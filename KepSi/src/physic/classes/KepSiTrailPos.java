package physic.classes;

import physic.KepSiKeplerObject;
import physic.KepSiVector;

public class KepSiTrailPos {
	public KepSiVector position;
	public KepSiKeplerObject parent;

	public KepSiTrailPos(KepSiVector position, KepSiKeplerObject parent) {
		this.position = position;
		this.parent = parent;
	}

}
