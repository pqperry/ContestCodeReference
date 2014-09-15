//# keepclass
package geom;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class Pt extends Point2D.Double {		
	
	public Pt() {
		super();
	}
	
	public Pt(double x, double y) {
		super(x, y);
	}
	
	Pt transform(AffineTransform at) {
		Point2D temp = at.transform(this, null);
		return new Pt(temp.getX(), temp.getY());
	}
	
}