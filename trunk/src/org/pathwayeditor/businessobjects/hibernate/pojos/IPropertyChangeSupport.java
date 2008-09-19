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
