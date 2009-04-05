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
