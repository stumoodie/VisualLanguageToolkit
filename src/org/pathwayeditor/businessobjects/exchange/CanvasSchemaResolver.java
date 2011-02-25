/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Stuart Moodie
 *
 */
class CanvasSchemaResolver implements EntityResolver {
	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		InputStream in = this.getClass().getResourceAsStream("Canvas.xsd");
		return new InputSource(in);
	}
}


