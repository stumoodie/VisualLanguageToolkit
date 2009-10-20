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
package org.pathwayeditor.businessobjects.hibernate.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasAttribute;
import org.pathwayeditor.businessobjects.hibernate.helpers.InconsistentNotationDefinitionException;
import org.pathwayeditor.testutils.PojoXlsXmlTester;

/**
 * @author smoodie
 *
 */
public class HibLabelAttributeTest extends PojoXlsXmlTester {
	private static final String DB_SOURCE_NAME = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xls";

	private static final long TEST_LABEL_ID = 100024L;
	private static final int EXPECTED_NODE_IDX = 15;

	private static final int EXPECTED_PROPERTY_OWNING_ATTR_SERIAL = 3;
	private static final Long EXPECTED_PROPERTY_ID = 100007L;
	
	private HibLabelAttribute testInstance;
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute#getHibObjectType()}.
	 */
	@Test
	public final void testGetHibObjectType() {
		assertNull("hibObjectTypePresent", this.testInstance.getHibObjectType());
	}

	@Test
	public final void testIsValid() throws InconsistentNotationDefinitionException {
//		INotationSubsystem stubNotationSubsystem = new StubNotationSubSystem();
//		INodeObjectType labelObjectType = new LabelObjectType(stubNotationSubsystem.getSyntaxService());
		this.testInstance.injectObjectType(null);
		assertTrue("valid", this.testInstance.isValid());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute#getProperty()}.
	 */
	@Test
	public final void testGetCurrentDrawingElement() {
		assertNotNull("current drawing element set", this.testInstance.getCurrentDrawingElement());
		assertTrue("reciprocal relationship exists", this.testInstance.getCurrentDrawingElement().getAttribute() == this.testInstance);
		assertEquals("expected label node", EXPECTED_NODE_IDX, this.testInstance.getCurrentDrawingElement().getIndex());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute#getProperty()}.
	 */
	@Test
	public final void testGetVisualisableProperty() {
		HibProperty actualProp = this.testInstance.getProperty();
		ICanvasAttribute owningAttribute = actualProp.getOwner();
		assertEquals("expected owning attribute serial", EXPECTED_PROPERTY_OWNING_ATTR_SERIAL, owningAttribute.getCreationSerial());
		assertEquals("expected property", EXPECTED_PROPERTY_ID, actualProp.getId());
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalSetUp()
	 */
	@Override
	protected void additionalSetup() {
		Session sess = this.getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testInstance = (HibLabelAttribute)sess.createQuery("from HibLabelAttribute where id = :id").setLong("id", TEST_LABEL_ID).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#doAdditionalTearDown()
	 */
	@Override
	protected void additionalTeardown() {
		Session sess = this.getHibFactory().getCurrentSession();
		sess.getTransaction().commit();
		this.testInstance = null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.GenericTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return DB_SOURCE_NAME;
	}

}
