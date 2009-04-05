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

import java.beans.PropertyChangeListener;

/**
 * Model objects need to implement this sort of interface in order to be able to 
 * notify presentation layer of model changes - based on java.beans property change system
 * 
 * Typical listeners will be visual elements e.g., edit parts in the current application
 * 
 * @author Richard Adams  - NH-  I copied this class after un-randomising the number of blank lines......
 *
 */
public interface IPropertyChangeSupport {
	
		void addPropertyChangeListener(PropertyChangeListener listener);
		
		void firePropertyChange(String property,Object oldValue,Object newValue);
		
		void removePropertyChangeListener(PropertyChangeListener listener);

}
