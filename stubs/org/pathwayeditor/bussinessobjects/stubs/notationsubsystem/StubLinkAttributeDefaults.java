/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.ConnectionRouter;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.LineStyle;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Location;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.PrimitiveShapeType;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.RGB;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Size;
import org.pathwayeditor.businessobjects.drawingprimitives.properties.IPropertyDefinition;
import org.pathwayeditor.businessobjects.typedefn.ILabelAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults;
import org.pathwayeditor.businessobjects.typedefn.ILinkTerminusDefaults;

/**
 * @author smoodie
 *
 */
public class StubLinkAttributeDefaults implements ILinkAttributeDefaults {
	public static final int EXPECTED_DEFAULT_LINE_WIDTH = 3;
	public static final String EXPECTED_DEFAULT_NAME = "link name";
	public static final PrimitiveShapeType EXPECTED_DEFAULT_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	public static final Size EXPECTED_DEFAULT_SIZE = new Size(15,25);
	public static final String EXPECTED_DEFAULT_URL = "http://www.google.com";
	public static final String EXPECTED_DEFAULT_DESCRIPTION = "descn";
	public static final String EXPECTED_DEFAULT_DETAILED_DESCRIPTION = "detailed descn";
	public static final RGB EXPECTED_DEFAULT_FILL_COLOUR = new RGB(1,2,3);
	public static final RGB EXPECTED_DEFAULT_LINE_COLOUR = new RGB(4,5, 6);
	public static final LineStyle EXPECTED_DEFAULT_LINE_STYLE = LineStyle.DASH_DOT;
	public static final Location EXPECTED_INITIAL_LOCATION = new Location(235,5543);
	public static final ConnectionRouter EXPECTED_DEFAULT_ROUTER = ConnectionRouter.SHORTEST_PATH;

//	private final ILinkTerminusDefaults sourceTermDefaults = new StubSourceTerminusDefaults();
//	private final ILinkTerminusDefaults targetTermDefaults = new StubTargetTerminusDefaults();

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDefaultLabelAttributes()
	 */
	public ILabelAttributeDefaults getDefaultLabelAttributes() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDescription()
	 */
	public String getDescription() {
		return EXPECTED_DEFAULT_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getDetailedDescription()
	 */
	public String getDetailedDescription() {
		return EXPECTED_DEFAULT_DETAILED_DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineColour()
	 */
	public RGB getLineColour() {
		return EXPECTED_DEFAULT_LINE_COLOUR;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineStyle()
	 */
	public LineStyle getLineStyle() {
		return EXPECTED_DEFAULT_LINE_STYLE;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getLineWidth()
	 */
	public int getLineWidth() {
		return EXPECTED_DEFAULT_LINE_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IShapeAttributeDefaults#getName()
	 */
	public String getName() {
		return EXPECTED_DEFAULT_NAME;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getLinkSource()
	 */
	public ILinkTerminusDefaults getLinkSource() {
		throw new UnsupportedOperationException("not implemented");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getLinkTarget()
	 */
	public ILinkTerminusDefaults getLinkTarget() {
		throw new UnsupportedOperationException("not implemented");
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getRouter()
	 */
	public ConnectionRouter getRouter() {
		return EXPECTED_DEFAULT_ROUTER;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.IDefaultLinkAttributes#getUrl()
	 */
	public String getUrl() {
		return EXPECTED_DEFAULT_URL;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.typedefn.ILinkAttributeDefaults#propertyIterator()
	 */
	public Iterator<IPropertyDefinition> propertyDefinitionIterator() {
		Collection<IPropertyDefinition> collection = Collections.emptySet();
		return collection.iterator();
	}
	
}
