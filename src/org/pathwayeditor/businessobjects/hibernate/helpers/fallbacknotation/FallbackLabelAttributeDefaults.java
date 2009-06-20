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
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LabelLocationPolicy;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.figure.figuredefn.GenericFont;
import org.pathwayeditor.figure.figuredefn.IFont;

/**
 * @author smoodie
 *
 */
public class FallbackLabelAttributeDefaults implements ILabelAttributeDefaults {
	private static final Size SIZE = null;
	private static final int LINE_WIDTH = 0;
	private static final LineStyle LINE_STYLE = LineStyle.SOLID;
	private static final RGB LINE_COLOUR = RGB.BLACK;
	private static final RGB FILL_COLOUR = RGB.WHITE;

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getFillColour()
	 */
	public RGB getFillColour() {
		return FILL_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLineColour()
	 */
	public RGB getLineColour() {
		return LINE_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLineWidth()
	 */
	public int getLineWidth() {
		return LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getSize()
	 */
	public Size getSize() {
		return SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getLabelLocationPolicy()
	 */
	public LabelLocationPolicy getLabelLocationPolicy() {
		return LabelLocationPolicy.CENTRE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults#getFont()
	 */
	public IFont getFont() {
		return new GenericFont();
	}

}
