//$Id: Name.java 11654 2007-06-07 22:48:34Z steve.ebersole@jboss.com $
package org.hibernate.test.hql;

/**
 * @author Gavin King
 */
public class Name {
	private String first;
	private Character initial;
	private String last;
	
	protected Name() {}
	
	public Name(String first, Character initial, String last) {
		this.first = first;
		this.initial = initial;
		this.last = last;
	}

	public Name(String first, char initial, String last) {
		this( first, new Character( initial ), last );
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public Character getInitial() {
		return initial;
	}

	public void setInitial(Character initial) {
		this.initial = initial;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
}
