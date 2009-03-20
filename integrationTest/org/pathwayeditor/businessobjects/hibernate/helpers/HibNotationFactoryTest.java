/**
 * 
 */
package org.pathwayeditor.businessobjects.hibernate.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;
import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationAutolayoutService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationConversionService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationExportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationImportService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationPluginService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationValidationService;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IRootObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotation;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubNotationSubSystem;
import org.pathwayeditor.bussinessobjects.stubs.notationsubsystem.StubShapeAObjectType;
import org.pathwayeditor.testutils.PojoTester;

/**
 * @author smoodie
 *
 */
public class HibNotationFactoryTest extends PojoTester {
	private static final String SOURCE_DATA = "integrationTest/DbSourceData/DbSourceDataRefData.xml";
	private static final String EXPECTED_QUALIFIED_NAME = "org.pathwayeditor.stub.notation";
	private static final String EXPECTED_NOTATION_NAME = "notation name";
	private static final Version EXPECTED_NOTATION_VERSION = new Version(100, 100, 100);
	private static final String NEW_NOTATION_DATA = "integrationTest/org/pathwayeditor/businessobjects/hibernate/helpers/DbHibObjectTypeIntersionData.xml";
	private HibNotationFactory testUnloadedInstance; 
	private HibNotationFactory testLoadedNotationInstance; 
	
	@Override
	protected void additionalSetup(){
		this.testUnloadedInstance = new HibNotationFactory(getHibFactory(), new StubNotationSubSystem());
		this.testLoadedNotationInstance = new HibNotationFactory(getHibFactory(), new StubLoadedNotationSubsystem());
	}
	
	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibNotationFactory#initialise()}.
	 * @throws Exception 
	 */
	@Test
	public final void testInitialiseUnloaded() throws Exception {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testUnloadedInstance.initialise();
		sess.getTransaction().commit();
		compareDatabase(SOURCE_DATA, NEW_NOTATION_DATA);
	}

	@Test
	public final void testInitialiseLoaded() throws Exception {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		try{
			this.testLoadedNotationInstance.initialise();
		}
		finally{
			sess.getTransaction().commit();
		}
		compareDatabase(SOURCE_DATA);
	}

	@Test
	public final void testUnloadedNotation() throws InconsistentNotationDefinitionException {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testUnloadedInstance.initialise();
		HibNotation notation = this.testUnloadedInstance.getNotation();
		assertEquals("Notation id correct", StubNotation.EXPECTED_QUALIFIED_NAME, notation.getQualifiedName());
		sess.getTransaction().commit();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibNotationFactory#getNotation()}.
	 * @throws InconsistentNotationDefinitionException 
	 */
	@Test
	public final void testUnloadedGetNotation() throws InconsistentNotationDefinitionException {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testUnloadedInstance.initialise();
		HibNotation notation = this.testUnloadedInstance.getNotation();
		String expectedId = StubNotation.EXPECTED_QUALIFIED_NAME;
		sess.getTransaction().commit();
		assertEquals("Notation id correct", expectedId, notation.getQualifiedName());
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)}.
	 * @throws InconsistentNotationDefinitionException 
	 */
	@Test
	public final void testGetObjectType() throws InconsistentNotationDefinitionException {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testUnloadedInstance.initialise();
		IObjectType objectType = this.testUnloadedInstance.getNotationSubsystem().getSyntaxService().getObjectType(StubShapeAObjectType.UNIQUE_ID);
		HibObjectType actualObjectType = this.testUnloadedInstance.getObjectType(objectType);
		assertEquals("correct object type", StubShapeAObjectType.UNIQUE_ID, actualObjectType.getUniqueId());
		sess.getTransaction().commit();
	}

	/**
	 * Test method for {@link org.pathwayeditor.businessobjects.hibernate.helpers.HibNotationFactory#getNotationSubsystem()}.
	 * @throws InconsistentNotationDefinitionException 
	 */
	@Test
	public final void testGetLoadedNotationSubsystem() throws InconsistentNotationDefinitionException {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testLoadedNotationInstance.initialise();
		INotation actual = this.testLoadedNotationInstance.getNotation();
		sess.getTransaction().commit();
		assertTrue("notation loaded subsystem exists", actual != null);
	}

	@Test
	public final void testGetUnloadedNotationSubsystem() throws InconsistentNotationDefinitionException {
		Session sess = getHibFactory().getCurrentSession();
		sess.beginTransaction();
		this.testUnloadedInstance.initialise();
		INotation actual = this.testUnloadedInstance.getNotation();
		sess.getTransaction().commit();
		assertTrue("notation unloaded subsystem exists", actual != null);
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testutils.PojoTester#getDbUnitDataFilePath()
	 */
	@Override
	protected String getDbUnitDataFilePath() {
		return SOURCE_DATA;
	}

	private static class StubLoadedNotationSubsystem implements INotationSubsystem {
		private final INotation notation;
		
		public StubLoadedNotationSubsystem(){
			this.notation = new StubLoadedNotation();
		}
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getAutolayoutService()
		 */
		public INotationAutolayoutService getAutolayoutService() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getConversionServices()
		 */
		public Set<INotationConversionService> getConversionServices() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getExportServices()
		 */
		public Set<INotationExportService> getExportServices() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getImportServices()
		 */
		public Set<INotationImportService> getImportServices() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getNotation()
		 */
		public INotation getNotation() {
			return this.notation;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getPluginServices()
		 */
		public Set<INotationPluginService> getPluginServices() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getSyntaxService()
		 */
		public INotationSyntaxService getSyntaxService() {
			return new StubLoadedNotationSyntaxService(this.notation);
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#getValidationService()
		 */
		public INotationValidationService getValidationService() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem#isFallback()
		 */
		public boolean isFallback() {
			return false;
		}
		
	}
	
	private static class StubLoadedNotation implements INotation {

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getDescription()
		 */
		public String getDescription() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getDisplayName()
		 */
		public String getDisplayName() {
			return EXPECTED_NOTATION_NAME;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getName()
		 */
		public String getQualifiedName() {
			return EXPECTED_QUALIFIED_NAME;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotation#getVersion()
		 */
		public Version getVersion() {
			return EXPECTED_NOTATION_VERSION;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(INotation o) {
			int retVal = this.getQualifiedName().compareTo(o.getQualifiedName());
			return retVal == 0 ? this.getVersion().compareTo(o.getVersion()) : retVal;
		}

	}
	
	private static class StubLoadedNotationSyntaxService implements INotationSyntaxService {
		private final INotation notation;
		private final IObjectType objectType1;
		private final IObjectType objectType2;
		private final Map<Integer, IObjectType> mapping;
		
		public StubLoadedNotationSyntaxService(INotation notation){
			this.notation = notation;
			this.objectType1 = new StubObjectType1();
			this.objectType2 = new StubObjectType2();
			this.mapping = new HashMap<Integer, IObjectType>();
			this.mapping.put(objectType1.getUniqueId(), objectType1);
			this.mapping.put(objectType2.getUniqueId(), objectType2);
		}
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsLinkObjectType(int)
		 */
		public boolean containsLinkObjectType(int uniqueID) {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsObjectType(int)
		 */
		public boolean containsObjectType(int uniqueID) {
			return mapping.containsKey(uniqueID);
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#containsShapeObjectType(int)
		 */
		public boolean containsShapeObjectType(int uniqueID) {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getLinkObjectType(int)
		 */
		public ILinkObjectType getLinkObjectType(int uniqueId) {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getObjectType(int)
		 */
		public IObjectType getObjectType(int uniqueId) {
			return this.mapping.get(uniqueId);
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getRootObjectType()
		 */
		public IRootObjectType getRootObjectType() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#getShapeObjectType(int)
		 */
		public IShapeObjectType getShapeObjectType(int uniqueId) {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#linkTypeIterator()
		 */
		public Iterator<ILinkObjectType> linkTypeIterator() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#objectTypeIterator()
		 */
		public Iterator<IObjectType> objectTypeIterator() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#shapeTypeIterator()
		 */
		public Iterator<IShapeObjectType> shapeTypeIterator() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotation()
		 */
		public INotation getNotation() {
			return this.notation;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationService#getNotationSubsystem()
		 */
		public INotationSubsystem getNotationSubsystem() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#findLinkObjectTypeByName(java.lang.String)
		 */
		public ILinkObjectType findLinkObjectTypeByName(String name) {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#findShapeObjectTypeByName(java.lang.String)
		 */
		public IShapeObjectType findShapeObjectTypeByName(String name) {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#numLinkObjectTypes()
		 */
		public int numLinkObjectTypes() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#numObjectTypes()
		 */
		public int numObjectTypes() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.notationsubsystem.INotationSyntaxService#numShapeObjectTypes()
		 */
		public int numShapeObjectTypes() {
			throw new UnsupportedOperationException("Not implemented");
		}
		
	}
	
	private static class StubObjectType1 implements IObjectType {
		public static final int UNIQUE_ID = 1;
		public static final String NAME = "objectName";
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
		 */
		public String getDescription() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
		 */
		public String getName() {
			return NAME;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
		 */
		public INotationSyntaxService getSyntaxService() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getUniqueId()
		 */
		public int getUniqueId() {
			return UNIQUE_ID;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(IObjectType o) {
			return this.getUniqueId() < o.getUniqueId() ? -1 : this.getUniqueId() > o.getUniqueId() ? 1 : 0;
		}
		
	}

	private static class StubObjectType2 implements IObjectType {
		public static final int UNIQUE_ID = 2;
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getDescription()
		 */
		public String getDescription() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getName()
		 */
		public String getName() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getSyntaxService()
		 */
		public INotationSyntaxService getSyntaxService() {
			throw new UnsupportedOperationException("Not implemented");
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.typedefn.IObjectType#getUniqueId()
		 */
		public int getUniqueId() {
			return UNIQUE_ID;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(IObjectType o) {
			return this.getUniqueId() < o.getUniqueId() ? -1 : this.getUniqueId() > o.getUniqueId() ? 1 : 0;
		}
		
	}
}
