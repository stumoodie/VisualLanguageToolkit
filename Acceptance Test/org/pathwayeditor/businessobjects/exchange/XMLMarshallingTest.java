/**
 * 
 */
package org.pathwayeditor.businessobjects.exchange;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.exchange.castor.Canvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.management.IMapPersistenceManager;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;
import org.pathwayeditor.testutils.GenericXlsTester;


/**
 * @author smoodie
 *
 */
public class XMLMarshallingTest extends GenericXlsTester {
	private static final String REPOSITORY_NAME ="repo name" ;
	private static final String MAP_PATH = "/subfolder1/Diagram name";
	private IRepository repository;
	private ICanvas dbCanvas ;

	@Override
	protected String getDbUnitDataFilePath() {
		return "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xls";
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericXlsTester#doAdditionalSetUp()
	 */
	@Override
	protected void doAdditionalSetUp() {
		repository = this.getRepositoryPersistenceManager().getRepository();
		IMap map = (IMap)repository.findRepositoryItemByPath(MAP_PATH);
		
		IMapPersistenceManager map1Manager = this.getRepositoryPersistenceManager().getMapPersistenceManager(map);
		map1Manager.open() ;
		this.dbCanvas = map1Manager.getCanvas();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericXlsTester#doAdditionalTearDown()
	 */
	@Override
	protected void doAdditionalTearDown() {
		this.repository =null;
		this.dbCanvas = null;
	}

	@Override
	protected String getTestRepositoryName() {
		return REPOSITORY_NAME ;
	}
	
	
	@Test
	public void testMarshalling() throws IOException, MarshalException, ValidationException{
		Writer out = null;
		try {
			CanvasMarshaller builder = new CanvasMarshaller();
			builder.setCanvas((HibCanvas)dbCanvas);
			builder.buildCanvas();
			Canvas xmlCanvas = builder.getCanvas();
			xmlCanvas.validate();
			assertTrue("valid XML", xmlCanvas.isValid());
			out = new FileWriter("test.xml");
			builder.write(out);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
