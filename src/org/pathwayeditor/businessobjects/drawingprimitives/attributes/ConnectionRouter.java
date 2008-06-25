package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

public enum ConnectionRouter {
 SHORTEST_PATH(1), FAN(2);
 //, MANHATTAN(3) not supported graphically yet.
 int code;

public int getCode() {
	return code;
}

private ConnectionRouter(int code) {
	this.code = code;
}
/**
 * Returns a String [] of Connection router names in the order they are declared.
 * @return A <code>String []</code>.
 */
public static  String [] getStringArray () {
	String [] rc = new String [values().length];
	int i = 0;
	for(ConnectionRouter router: values()) {
		rc[i] = router.toString();
	    i++;	
	}
	return rc;
  }
}
