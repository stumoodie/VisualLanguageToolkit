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

package org.pathwayeditor.businessobjects.exchange;

import java.io.IOException;
import java.io.Reader;

import org.exolab.castor.util.DTDResolver;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.xml.sax.EntityResolver;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;

/**
 * @author smoodie
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

		this.builder = new ModelBuilder(xmlInstance, notationPool);
		builder.buildNotation();
		builder.buildCanvas();
		builder.buildModel();
	}
	
	
	public ICompoundGraph getModel(){
		if(this.builder == null) throw new IllegalStateException("Canvas not build from XML document");
		
		return this.builder.getGraph();
	}
	
}
