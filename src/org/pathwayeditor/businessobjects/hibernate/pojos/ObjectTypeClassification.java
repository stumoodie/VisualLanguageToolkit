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

/**
 * @author smoodie
 *
 */
public enum ObjectTypeClassification {
	ROOTOBJECT(0), SHAPE(1), LINK(2);
	
	private final int id; 
	
	private ObjectTypeClassification(int id){
		this.id = id;
	}
	
	
	public int toInt(){
		return this.id;
	}
	
	public ObjectTypeClassification fromInt(int id){
		ObjectTypeClassification retVal = null;
		switch(id){
		case 0:
			retVal = ROOTOBJECT;
			break;
		case 1:
			retVal = SHAPE;
			break;
		case 2:
			retVal = LINK;
			break;
		default:
			throw new IllegalArgumentException("Type id of, " + id + ", is unknown");
		}
		return retVal;
	}
}
