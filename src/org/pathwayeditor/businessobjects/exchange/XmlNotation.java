/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.businessobjects.exchange;

import org.pathwayeditor.businessobjects.drawingprimitives.attributes.Version;
import org.pathwayeditor.businessobjects.exchange.castor.Notation;
import org.pathwayeditor.businessobjects.notationsubsystem.INotation;

/**
 * @author Stuart Moodie
 *
 */
public class XmlNotation implements INotation {
	private final Notation notn;

	public XmlNotation(Notation xmlNotation){
		this.notn = xmlNotation;
	}
	
	@Override
	public String getDescription() {
		return notn.getDescription();
	}

	@Override
	public String getDisplayName() {
		return notn.getDisplayName();
	}

	@Override
	public String getQualifiedName() {
		return notn.getQualifiedName();
	}

	@Override
	public Version getVersion() {
		return new Version(notn.getMajorVersion(), notn.getMinorVersion(), notn.getPatchVersion());
	}

	@Override
	public int compareTo(INotation o) {
		int retVal = notn.getQualifiedName().compareTo(o.getQualifiedName());
		if(retVal == 0){
			retVal = this.getVersion().compareTo(o.getVersion());
		}
		return retVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getQualifiedName() == null) ? 0 : this.getQualifiedName().hashCode());
		result = prime * result + ((this.getVersion() == null) ? 0 : this.getVersion().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof INotation))
			return false;
		INotation other = (INotation) obj;
		if (this.getQualifiedName() == null) {
			if (other.getQualifiedName() != null)
				return false;
		} else if (!this.getQualifiedName().equals(other.getQualifiedName()))
			return false;
		if (this.getVersion() == null) {
			if (other.getVersion() != null)
				return false;
		} else if (!this.getVersion().equals(other.getVersion()))
			return false;
		return true;
	}

}
