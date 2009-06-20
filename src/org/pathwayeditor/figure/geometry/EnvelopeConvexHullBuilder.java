/**
 * 
 */
package org.pathwayeditor.figure.geometry;



/**
 * @author smoodie
 *
 */
public class EnvelopeConvexHullBuilder  {
	private static final Point DEFAULT_ORIGIN = new Point(0, 0);
	private static final Dimension DEFAULT_SIZE = new Dimension(0, 0);
	private Envelope envelope;
	
	public EnvelopeConvexHullBuilder(Envelope bounds){
		this.envelope = bounds;
	}

	public EnvelopeConvexHullBuilder(){
		this.envelope = new Envelope(DEFAULT_ORIGIN, DEFAULT_SIZE);
	}
	
	public void setEnvelope(Envelope newEnvelope){
		this.envelope = newEnvelope;
	}

	public Envelope getEnvelope(){
		return this.envelope;
	}
	
	public IConvexHull getConvexHull(){
		ConvexHullBuilder builder = new ConvexHullBuilder();
		builder.addPoint(envelope.getOrigin());
		builder.addPoint(envelope.getHorizontalCorner());
		builder.addPoint(envelope.getDiagonalCorner());
		builder.addPoint(envelope.getVerticalCorner());
		return builder.getConvexHull();
	}
}
