/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers.fallbacknotation;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LinkEndDecoratorShape;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;
import org.pathwayeditor.businessobjects.typedefn.IPropertyDefinitionFilter;

/**
 * @author smoodie
 *
 */
public class FallbackLinkTerminusDefaults implements ILinkTerminusDefaults {
	private static final LinkEndDecoratorShape DEFAULT_ARROWHEAD = LinkEndDecoratorShape.NONE;
	private static final Size DEFAULT_END_SIZE = new Size(0, 0);
	private static final short DEFAULT_GAP = 0;
	private static final RGB DEFAULT_COLOUR = new RGB(0, 0, 0);
	private static final IPropertyDefinitionFilter DEFAULT_PROPERTY_DEFINITION = null;
	private static final PrimitiveShapeType DEFAULT_TERM_DECORATOR = PrimitiveShapeType.RECTANGLE;
	private static final Size DEFAULT_TERM_SIZE = new Size(0, 0);
	private static final ILabelAttributeDefaults LABEL_DEFAULTS = new FallbackLabelAttributeDefaults();
	
	public FallbackLinkTerminusDefaults(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		return LABEL_DEFAULTS;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndDecoratorType()
	 */
	public LinkEndDecoratorShape getEndDecoratorType() {
		return DEFAULT_ARROWHEAD;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getEndSize()
	 */
	public Size getEndSize() {
		return DEFAULT_END_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getGap()
	 */
	public short getGap() {
		return DEFAULT_GAP;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getPropertiesFilter()
	 */
	public IPropertyDefinitionFilter getPropertiesFilter() {
		return DEFAULT_PROPERTY_DEFINITION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermColour()
	 */
	public RGB getTermColour() {
		return DEFAULT_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermDecoratorType()
	 */
	public PrimitiveShapeType getTermDecoratorType() {
		return DEFAULT_TERM_DECORATOR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults#getTermSize()
	 */
	public Size getTermSize() {
		return DEFAULT_TERM_SIZE;
	}

}
