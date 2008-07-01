/**
 * 
 */
package org.pathwayeditor.businessobjects.bolayer;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvas;
import org.pathwayeditor.businessobjects.repository.IMap;
import org.pathwayeditor.businessobjects.repository.IRepository;

/**
 * @author smoodie
 *
 */
public class BusinessObjectFactory implements IBusinessObjectFactory {
	private static BusinessObjectFactory anInstance;
	
	public static IBusinessObjectFactory getInstance(){
		if(anInstance == null){
			anInstance = new BusinessObjectFactory();
		}
		return anInstance;
	}
	
	private BusinessObjectFactory(){
		
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getRepository()
	 */
	public synchronized IRepository getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#createTransientCanvas()
	 */
	public synchronized ICanvas createTransientCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#getCanvas(org.pathwayeditor.businessobjects.repository.IMap)
	 */
	public synchronized ICanvas getCanvas(IMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseCanvas(org.pathwayeditor.businessobjects.drawingprimitives.ICanvas)
	 */
	public synchronized void synchroniseCanvas(ICanvas canvas) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.bolayer.IBusinessObjectFactory#synchroniseRepository(org.pathwayeditor.businessobjects.repository.IRepository)
	 */
	public synchronized void synchroniseRepository(IRepository repository) {
		// TODO Auto-generated method stub
		
	}

}
