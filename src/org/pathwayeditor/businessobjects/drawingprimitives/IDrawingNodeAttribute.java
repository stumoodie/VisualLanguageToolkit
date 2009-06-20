/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives;

import org.pathwayeditor.figure.geometry.Dimension;
import org.pathwayeditor.figure.geometry.Envelope;
import org.pathwayeditor.figure.geometry.IConvexHull;
import org.pathwayeditor.figure.geometry.Point;

/**
 * @author smoodie
 *
 */
public interface IDrawingNodeAttribute extends ICanvasAttribute {

	Point getLocation();
	
	void setLocation(Point newLocation);
	
	Dimension getSize();
	
	void setSize(Dimension newSize);
	
	Envelope getBounds();
	
	void setBounds(Envelope newBounds);

	IConvexHull getConvexHull();
	
	void setConvexHull(IConvexHull newHull);
}
