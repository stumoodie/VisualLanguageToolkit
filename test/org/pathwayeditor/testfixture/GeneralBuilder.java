/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.testfixture;


/**
 * @author Stuart Moodie
 *
 */
public class GeneralBuilder<T> implements IRedefinableBuilder<T> {
	private final String elementId;
	private final IObjectConstructor<T> defaultConstructor;
	private IObjectConstructor<T> overridingConstructor;
	private T createdObject;

	public GeneralBuilder(String elementId, IObjectConstructor<T> constructor){
		this.elementId = elementId;
		this.defaultConstructor = constructor;
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#getElementId()
	 */
	@Override
	public String getElementId() {
		return this.elementId;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#create()
	 */
	@Override
	public void create() {
		if(overridingConstructor == null || (createdObject = overridingConstructor.create()) == null){
			createdObject = defaultConstructor.create();
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IObjectBuilder#buildDependencies()
	 */
	@Override
	public void buildDependencies() {
		if(overridingConstructor == null || overridingConstructor.build() == false){
			defaultConstructor.build();
		}
	}

	@Override
	public IObjectConstructor<T> getOverridingConstructor() {
		return this.overridingConstructor;
	}

	@Override
	public void setOverridingConstructor(IObjectConstructor<T> nodeConstructor) {
		this.overridingConstructor = nodeConstructor;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.testfixture.IShapeNodeBuilder#getShapeNode()
	 */
	@Override
	public T getCreatedObject() {
		return this.createdObject;
	}

}
