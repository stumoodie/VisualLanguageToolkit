/**
 * 
 */
package org.pathwayeditor.bussinessobjects.stubs.notationsubsystem;

import java.util.Iterator;
import java.util.Set;

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
public abstract class StubLinkAttributeDefaults implements ILinkAttributeDefaults {
	public   int EXPECTED_DEFAULT_LINE_WIDTH = 3;
	public   String EXPECTED_DEFAULT_NAME = "link name";
	public   PrimitiveShapeType EXPECTED_DEFAULT_SHAPE_TYPE = PrimitiveShapeType.ELLIPSE;
	public   Size EXPECTED_DEFAULT_SIZE = new Size(15,25);
	public static   String EXPECTED_DEFAULT_URL = "http://www.google.com";
	public   String EXPECTED_DEFAULT_DESCRIPTION = "descn";
	public   String EXPECTED_DEFAULT_DETAILED_DESCRIPTION = "detailed descn";
	public   RGB EXPECTED_DEFAULT_FILL_COLOUR = new RGB(1,2,3);
	public   RGB EXPECTED_DEFAULT_LINE_COLOUR = new RGB(4,5, 6);
	public   LineStyle EXPECTED_DEFAULT_LINE_STYLE = LineStyle.DASH_DOT;
	public   Location EXPECTED_INITIAL_LOCATION = new Location(235,5543);
	public  static  ConnectionRouter EXPECTED_DEFAULT_ROUTER = ConnectionRouter.SHORTEST_PATH;

//	private  ILinkTerminusDefaults sourceTermDefaults = new StubSourceTerminusDefaults();
//	private  ILinkTerminusDefaults targetTermDefaults = new StubTargetTerminusDefaults();

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
		return getpropdefns().iterator();
	}

	/**
	 * @return
	 */
	protected abstract Set<IPropertyDefinition> getpropdefns() ;
	
}
