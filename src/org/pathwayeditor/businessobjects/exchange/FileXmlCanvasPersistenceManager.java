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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Iterator;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.management.NonPersistentCanvasFactory;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

/**
 * @author smoodie
 *
 */
public class FileXmlCanvasPersistenceManager implements IXmlPersistenceManager {
	private ICanvas canvas = null;
	private final INotationSubsystemPool subsystemPool;
	
	public FileXmlCanvasPersistenceManager(INotationSubsystemPool subsystemPool){
		this.subsystemPool = subsystemPool;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.XmlPersistenceManager#createNewCanvasStream(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)
	 */
	public void createNewCanvasStream(final INotationSubsystem notationSubsystem, String canvasName) {
		NonPersistentCanvasFactory fact = NonPersistentCanvasFactory.getInstance();
		fact.setCanvasName(canvasName);
		fact.setNotationSubsystem(notationSubsystem);
		IHibNotationFactory hibNotationFactory = new XmlNotationFactory(new INotationDelegate() {

			public INotation getNotation() {
				return notationSubsystem.getNotation();
			}

			public int numObjectTypes() {
				return notationSubsystem.getSyntaxService().numObjectTypes();
			}

			public Iterator<IObjectInfo> objectTypeIterator() {
				final Iterator<IObjectType> objectTypeIter = notationSubsystem.getSyntaxService().objectTypeIterator();
				return new Iterator<IObjectInfo>() {

					public boolean hasNext() {
						return objectTypeIter.hasNext();
					}

					public IObjectInfo next() {
						final IObjectType xmlObjectType = objectTypeIter.next();
						return new IObjectInfo() {

							public ObjectTypeClassification getClassification() {
								ObjectTypeClassification classn = ObjectTypeClassification.SHAPE;
								if (xmlObjectType instanceof IRootObjectType) {
									classn = ObjectTypeClassification.ROOTOBJECT;
								} else if (xmlObjectType instanceof IShapeObjectType) {
									classn = ObjectTypeClassification.LINK;
								}
								return classn;
							}

							public String getDescription() {
								return xmlObjectType.getDescription();
							}

							public String getName() {
								return xmlObjectType.getName();
							}

							public int getUniqueId() {
								return xmlObjectType.getUniqueId();
							}

						};
					}

					public void remove() {
						throw new UnsupportedOperationException("Removal is not supported");
					}

				};
			}

		});
		canvas = new HibCanvas(canvasName, 0, hibNotationFactory, notationSubsystem, canvasName);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.XmlPersistenceManager#readCanvasStream(java.io.InputStream)
	 */
	public void readCanvasFromStream(InputStream in) throws IOException {
		CanvasUnmarshaller unmarshaller = new CanvasUnmarshaller(this.subsystemPool, "XMLFile", 0);
		unmarshaller.read(new BufferedReader(new InputStreamReader(in)));
		in.close();
		unmarshaller.buildCanvas();
		this.canvas = unmarshaller.getCanvas();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.XmlPersistenceManager#streamCanvas()
	 */
	public InputStream writeCanvasToStream() throws IOException {
		CanvasMarshaller marshaller = new CanvasMarshaller();
		marshaller.setCanvas((HibCanvas)this.canvas);
		marshaller.buildCanvas();
		File tmpFile = null;
		Writer tmpFileOut = null;
		try {
			tmpFile = File.createTempFile("canvasFile", "pwe");
			tmpFileOut = new FileWriter(tmpFile);
			marshaller.write(new BufferedWriter(tmpFileOut));
			return new FileInputStream(tmpFile);
		}
		finally{
			if(tmpFileOut != null){
				tmpFileOut.close();
			}
			if(tmpFile != null){
				tmpFile.delete();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.IXmlPersistenceManager#getCurrentCanvas()
	 */
	public ICanvas getCurrentCanvas() {
		return this.canvas;
	}

}
