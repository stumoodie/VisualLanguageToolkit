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

import org.pathwayeditor.businessobjects.impl.RootAttribute;
import org.pathwayeditor.businessobjects.management.INotationSubsystemPool;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;

import uk.ac.ed.inf.graph.compound.ICompoundGraph;
import uk.ac.ed.inf.graph.compound.newimpl.CompoundGraph;

/**
 * @author smoodie
 *
 */
public class FileXmlCanvasPersistenceManager implements IXmlPersistenceManager {
	private ICompoundGraph canvas = null;
	private final INotationSubsystemPool subsystemPool;
	
	public FileXmlCanvasPersistenceManager(INotationSubsystemPool subsystemPool){
		this.subsystemPool = subsystemPool;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.XmlPersistenceManager#createNewCanvasStream(org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem)
	 */
	@Override
	public void createNewCanvasStream(final INotationSubsystem notationSubsystem, String canvasName) {
//		NonPersistentCanvasFactory fact = NonPersistentCanvasFactory.getInstance();
//		fact.setCanvasName(canvasName);
//		fact.setNotationSubsystem(notationSubsystem);
//		IHibNotationFactory hibNotationFactory = new XmlNotationFactory(new INotationDelegate() {
//
//			@Override
//			public INotation getNotation() {
//				return notationSubsystem.getNotation();
//			}
//
//			@Override
//			public int numObjectTypes() {
//				return notationSubsystem.getSyntaxService().numObjectTypes();
//			}
//
//			@Override
//			public Iterator<IObjectInfo> objectTypeIterator() {
//				final Iterator<IObjectType> objectTypeIter = notationSubsystem.getSyntaxService().objectTypeIterator();
//				return new Iterator<IObjectInfo>() {
//
//					@Override
//					public boolean hasNext() {
//						return objectTypeIter.hasNext();
//					}
//
//					@Override
//					public IObjectInfo next() {
//						final IObjectType xmlObjectType = objectTypeIter.next();
//						return new IObjectInfo() {
//
//							@Override
//							public ObjectTypeClassification getClassification() {
//								ObjectTypeClassification classn = ObjectTypeClassification.SHAPE;
//								if (xmlObjectType instanceof IRootObjectType) {
//									classn = ObjectTypeClassification.ROOTOBJECT;
//								} else if (xmlObjectType instanceof IShapeObjectType) {
//									classn = ObjectTypeClassification.LINK;
//								}
//								return classn;
//							}
//
//							@Override
//							public String getDescription() {
//								return xmlObjectType.getDescription();
//							}
//
//							@Override
//							public String getName() {
//								return xmlObjectType.getName();
//							}
//
//							@Override
//							public int getUniqueId() {
//								return xmlObjectType.getUniqueId();
//							}
//
//						};
//					}
//
//					@Override
//					public void remove() {
//						throw new UnsupportedOperationException("Removal is not supported");
//					}
//
//				};
//			}
//
//		});
		canvas = new CompoundGraph(new RootAttribute(canvasName, notationSubsystem.getSyntaxService().getRootObjectType()));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.XmlPersistenceManager#readCanvasStream(java.io.InputStream)
	 */
	@Override
	public void readCanvasFromStream(InputStream in) throws IOException {
		CanvasUnmarshaller unmarshaller = new CanvasUnmarshaller(this.subsystemPool);
		unmarshaller.read(new BufferedReader(new InputStreamReader(in)));
		in.close();
		unmarshaller.buildCanvas();
		this.canvas = unmarshaller.getCanvas();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.exchange.XmlPersistenceManager#streamCanvas()
	 */
	@Override
	public InputStream writeCanvasToStream() throws IOException {
		CanvasMarshaller marshaller = new CanvasMarshaller();
		marshaller.setCanvas(this.canvas);
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
	@Override
	public ICompoundGraph getCurrentCanvas() {
		return this.canvas;
	}

}
