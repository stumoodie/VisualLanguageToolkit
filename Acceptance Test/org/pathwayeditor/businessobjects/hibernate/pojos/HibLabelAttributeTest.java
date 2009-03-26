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
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.INodeObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author smoodie
 *
 */
public class HibLabelAttributeTest extends PojoTester {
	private static final String DB_SOURCE_NAME = "Acceptance Test/DBConsistencyTestSourceData/DBSourceData.xml";

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
		INotationSubsystem stubNotationSubsystem = new StubNotationSubSystem();
		INodeObjectType labelObjectType = new LabelObjectType(stubNotationSubsystem.getSyntaxService());
		this.testInstance.injectObjectType(labelObjectType);
		assertTrue("valid", this.testInstance.isValid());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.pojos.HibLabelAttribute#getVisualisableProperty()}.
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
		HibProperty actualProp = this.testInstance.getVisualisableProperty();
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
