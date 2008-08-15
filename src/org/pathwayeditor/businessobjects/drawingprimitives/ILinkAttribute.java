package org.pathwayeditor.businessobjects.drawingprimitives;

import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.IBendPoint;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotatedObject;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IAnnotationProperty;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;

public interface ILinkAttribute extends IZOrderedObject, IAnnotatedObject, ICanvasObject {

	ICanvas getCanvas();
	
	int getCreationSerial();
	
	ILinkObjectType getObjectType();
	
	ILinkEndDecorator getLinkSourceDecoration();

	ILinkEndDecorator getLinkTargetDecoration();

	ConnectionRouter getRouter();
	
	void setRouter(ConnectionRouter router);
	
	Iterator<IBendPoint> bendPointIterator();
	
	int numBendPoints();
	
	void addBendPoint(IBendPoint newBendPoint);
	
	boolean containsBendPoint(IBendPoint bendPoint);
	
	void removeBendPoint(IBendPoint bendPoint);
	
	RGB getLineColor () ;
	void setLineColor ( RGB newColor ) ;
	
	
	LineStyle getLineStyle () ;
	void setLineStyle ( LineStyle style );
	
	String getUrl () ;
	void setUrl ( String url) ;
	
	void addLinkProperty ( String name , IAnnotationProperty toAdd ) ;
	IAnnotationProperty getProperty ( String propName );
}
