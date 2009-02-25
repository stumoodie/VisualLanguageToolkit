/**
 * 
 */
package org.pathwayeditor.businessobjects.management;

import java.util.HashMap;
import java.util.Map;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibCanvas;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibNotation;
import org.pathwayeditor.businessobjects.hibernate.pojos.HibObjectType;
import org.pathwayeditor.businessobjects.hibernate.pojos.ObjectTypeClassification;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotationSubsystem;
import org.pathwayeditor.businessobjects.typedefn.ILinkObjectType;
import org.pathwayeditor.businessobjects.typedefn.IObjectType;
import org.pathwayeditor.businessobjects.typedefn.IShapeObjectType;

import uk.ed.inf.graph.util.IndexCounter;

/**
 * @author smoodie
 *
 */
public class NonPersistentCanvasFactory {
	public static final String DEFAULT_REPO_NAME = "Non-persistent repository";
	private static NonPersistentCanvasFactory anInstance = null;
	private INotationSubsystem notationSubsystem;
	private IHibNotationFactory hibNotationFactory;
	private String canvasName;
	private IndexCounter inodeCounter = new IndexCounter();
	
	public static NonPersistentCanvasFactory getInstance(){
		if(anInstance == null){
			anInstance = new NonPersistentCanvasFactory();
		}
		return anInstance;
	}

	
	// method used for testing. Allow a new copy of the singleton to be created. 
	static void newInstance(){
		anInstance = new NonPersistentCanvasFactory();
	}
	
	private NonPersistentCanvasFactory(){
		
	}
	
	
	/**
	 * @return the notationSubsystem
	 */
	public INotationSubsystem getNotationSubsystem() {
		return this.notationSubsystem;
	}

	/**
	 * @param notationSubsystem the notationSubsystem to set
	 */
	public void setNotationSubsystem(INotationSubsystem notationSubsystem) {
		if(notationSubsystem == null) throw new IllegalArgumentException("notation subsystem cannot be null");
		if(notationSubsystem.isFallback()) throw new IllegalArgumentException("Cannot use a fallback notation subsystem to create a new canvas");
		
		this.notationSubsystem = notationSubsystem;
		this.hibNotationFactory = new StubHibNotationFactory(notationSubsystem);
	}

	/**
	 * @return the canvasName
	 */
	public String getCanvasName() {
		return this.canvasName;
	}
	
	
	public boolean isValidCanvasName(String canvasName){
		return HibCanvas.checkValidName(canvasName);
	}

	/**
	 * @param canvasName the canvasName to set
	 */
	public void setCanvasName(String canvasName) {
		if(!isValidCanvasName(canvasName)) throw new IllegalArgumentException("Canvas name is invalid");
		
		this.canvasName = canvasName;
	}

	
	public ICanvas createNewCanvas(){
		if(this.canvasName == null || this.notationSubsystem == null) throw new IllegalStateException("Canvas name and notation subsystem must be set");
		
		if(!this.hibNotationFactory.isInitialised()){
			this.hibNotationFactory.initialise();
		}
		ICanvas retVal = new HibCanvas(DEFAULT_REPO_NAME, inodeCounter.nextIndex(), this.hibNotationFactory, notationSubsystem, this.canvasName);
		return retVal;
	}
	
	
	
	private class StubHibNotationFactory implements IHibNotationFactory {
		private final HibNotation hibNotation;
		private final INotationSubsystem notationSubsystem;
		private final Map<IObjectType, HibObjectType> otMap;
		private boolean initialised = false;
		
		public StubHibNotationFactory(INotationSubsystem notationSubsystem){
			this.otMap = new HashMap<IObjectType, HibObjectType>();
			this.notationSubsystem = notationSubsystem;
			INotation notn = this.notationSubsystem.getNotation();
			this.hibNotation = new HibNotation(notn.getQualifiedName(), notn.getDisplayName(), notn.getDescription(), notn.getVersion());
		}
		
		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#containsObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
		 */
		public boolean containsObjectType(IObjectType objectType) {
			return this.notationSubsystem.getSyntaxService().containsObjectType(objectType.getUniqueId());
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotation()
		 */
		public HibNotation getNotation() {
			return this.hibNotation;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getNotationSubsystem()
		 */
		public INotationSubsystem getNotationSubsystem() {
			return this.notationSubsystem;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#getObjectType(org.pathwayeditor.businessobjects.typedefn.IObjectType)
		 */
		public HibObjectType getObjectType(IObjectType objectType) {
			HibObjectType retVal = this.otMap.get(objectType);
			if(retVal == null){
				ObjectTypeClassification otClassn = objectType instanceof IShapeObjectType ? ObjectTypeClassification.SHAPE
						: objectType instanceof ILinkObjectType ? ObjectTypeClassification.LINK : ObjectTypeClassification.ROOTOBJECT; 
				retVal = new HibObjectType(objectType.getUniqueId(), objectType.getName(), objectType.getDescription(), otClassn);
				this.otMap.put(objectType, retVal);
			}
			return retVal;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#initialise()
		 */
		public void initialise() {
			this.initialised = true;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isFallback()
		 */
		public boolean isFallback() {
			return false;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#hasInitialisationFailed()
		 */
		public boolean hasInitialisationFailed() {
			// can never fail in normal operation
			return false;
		}

		/* (non-Javadoc)
		 * @see org.pathwayeditor.businessobjects.hibernate.helpers.IHibNotationFactory#isInitialised()
		 */
		public boolean isInitialised() {
			return this.initialised;
		}
		
	}
}
