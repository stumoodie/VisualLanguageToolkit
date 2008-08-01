/**
 * 
 */
package org.pathwayeditor.businessobjects.pojos;

/**
 * @author smoodie
 *
 */
public class BoMapping<F, D> {
	private final F boFacade;
	private final D dataObject;
	
	public BoMapping(F boFacade, D dataObject){
		this.boFacade = boFacade;
		this.dataObject = dataObject;
	}
	
	public F getBoFacade(){
		return this.boFacade;
	}
	
	public D getdataObject(){
		return this.dataObject;
	}
}
