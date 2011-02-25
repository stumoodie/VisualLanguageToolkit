/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import java.io.IOException;
import java.io.Reader;

import org.exolab.castor.util.DTDResolver;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.pathwayeditor.businessobjects.drawingprimitives.IModel;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.management.ModelFactory;
import org.xml.sax.EntityResolver;

/**
 * @author Stuart Moodie
 *
 */
public class ModelUnmarshaller {
	private Canvas xmlInstance = null;
	private INotationSubsystemPool notationPool;
	private ModelBuilder builder = null;

	public ModelUnmarshaller(INotationSubsystemPool notationPool){
		this.notationPool = notationPool;
//		this.repoName = name;
//		this.iNode = iNode;
	}
	
	public void read(Reader writer) throws IOException{
		XMLContext ctx = new XMLContext();
		Unmarshaller m = ctx.createUnmarshaller();
		
		EntityResolver resolver = new CanvasSchemaResolver();
		m.setEntityResolver(new DTDResolver(resolver));
		try {
			m.setClass(Canvas.class);
			xmlInstance = (Canvas)m.unmarshal(writer);
		} catch (MarshalException e) {
			throw new RuntimeException("A bug was detected during xml schema generation", e);
		} catch (ValidationException e) {
			throw new RuntimeException("A bug was detected during xml schema generation", e);
		}
	}


	public void build(){
		if(this.xmlInstance == null) throw new IllegalStateException("File has not been read");

		this.builder = new ModelBuilder(xmlInstance, notationPool, new ModelFactory());
		builder.buildNotation();
		builder.buildCanvas();
		builder.buildModel();
	}
	
	
	public IModel getModel(){
		if(this.builder == null) throw new IllegalStateException("Canvas not build from XML document");
		
		return this.builder.getGraph();
	}
	
}
