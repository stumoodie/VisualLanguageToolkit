package org.pathwayeditor.businessobjects.hibernate.pojos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.pathwayeditor.businessobjects.repository.IFolder;
import org.pathwayeditor.businessobjects.repository.IRepositoryItem;


public class LevelOrderTreeIterator implements Iterator<IRepositoryItem> {
	private final Queue<IRepositoryItem> queue;
	
	public LevelOrderTreeIterator(IFolder rootNode) {
		if(rootNode == null) throw new IllegalArgumentException("root node cannot be null");
		
		this.queue = new LinkedList<IRepositoryItem>();
		queue.add(rootNode);
	}
	
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	public IRepositoryItem next() {
		IRepositoryItem retVal = queue.remove();
		if(retVal instanceof HibFolder){
			readChildren((HibFolder)retVal);
		}
		return retVal;
	}
	
	private void readChildren(HibFolder parent){
		for(HibFolder folder : parent.getSubFolders()){
			this.queue.offer(folder);
		}
		for(HibMap map : parent.getMapDiagrams()){
			this.queue.offer(map);
		}
	}

	public void remove() {
		throw new UnsupportedOperationException("removal not supported by this iterator");
	}

}
