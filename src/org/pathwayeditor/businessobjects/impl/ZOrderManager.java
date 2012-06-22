/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute;
import org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager;

/**
 * ZorderManager
 *
 * @author Stuart Moodie
 *
 */
public class ZOrderManager implements IZOrderManager {
	private ICanvasElementAttribute canvasAtt;
	private final LinkedList<ICanvasElementAttribute> zordering = new LinkedList<ICanvasElementAttribute>();

	public ZOrderManager(ICanvasElementAttribute canvasAtt){
		this.canvasAtt = canvasAtt;
	}
	
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#toFront(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public void toFront(ICanvasElementAttribute att) {
		if(!this.zordering.getLast().equals(att)){
			this.zordering.remove(att);
			this.zordering.addLast(att);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#toBack(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public void toBack(ICanvasElementAttribute att) {
		if(!this.zordering.getFirst().equals(att)){
			this.zordering.remove(att);
			this.zordering.addFirst(att);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#moveForwardOne(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public void moveForwardOne(ICanvasElementAttribute att) {
		int i = this.zordering.indexOf(att);
		if(i < 0) throw new NoSuchElementException("Attribute not found: " + att);
		if(i < this.zordering.size()-1){
			this.zordering.remove(i);
			this.zordering.add(i+1, att);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#moveBackOne(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public void moveBackOne(ICanvasElementAttribute att) {
		int i = this.zordering.indexOf(att);
		if(i < 0) throw new NoSuchElementException("Attribute not found: " + att);
		if(i > 0){
			this.zordering.remove(i);
			this.zordering.add(i-1, att);
		}
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#orderedIterator()
	 */
	@Override
	public Iterator<ICanvasElementAttribute> orderedIterator() {
		return this.zordering.iterator();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#getOwningAttribute()
	 */
	@Override
	public ICanvasElementAttribute getOwningAttribute() {
		return this.canvasAtt;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#addToFront(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public void addToFront(ICanvasElementAttribute att) {
		this.zordering.add(att);
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#canMoveBack(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public boolean canMoveBack(ICanvasElementAttribute att) {
		boolean retVal = false;
		int i = this.zordering.indexOf(att);
		if(i > 0){
			retVal = true;
		}
		return retVal;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#canMoveForward(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public boolean canMoveForward(ICanvasElementAttribute att) {
		boolean retVal = false;
		int i = this.zordering.indexOf(att);
		if(i >= 0 && i < this.zordering.size()-1){
			// i will be -1 if element doesn't exists in list
			retVal = true;
		}
		return retVal;
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#numAttributes()
	 */
	@Override
	public int numAttributes() {
		return this.zordering.size();
	}


	/* (non-Javadoc)
	 * @see org.pathwayeditor.businessobjects.drawingprimitives.IZOrderManager#remove(org.pathwayeditor.businessobjects.drawingprimitives.ICanvasElementAttribute)
	 */
	@Override
	public void remove(ICanvasElementAttribute attribute) {
		this.zordering.remove(attribute);
	}
	
}
