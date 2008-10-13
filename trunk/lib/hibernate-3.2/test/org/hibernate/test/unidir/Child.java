//$Id: Child.java 14092 2007-10-17 05:08:57Z steve.ebersole@jboss.com $
package org.hibernate.test.unidir;


/**
 * @author Gavin King
 */
public class Child {
	private String name;
	private int age;

	Child() {
	}

	public Child(String name) {
		this( name, 0 );
	}

	public Child(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
