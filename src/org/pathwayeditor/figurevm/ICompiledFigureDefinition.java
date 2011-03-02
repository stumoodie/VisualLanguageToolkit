/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figurevm;

import java.util.Iterator;
import java.util.Set;

/**
 * 
 * IFigureDefinition is an interface that defines a compiled figure definition. This contains instructions that can be efficiently interpreted
 * to render a figure.
 *
 * @author Stuart Moodie
 *
 */
public interface ICompiledFigureDefinition {

	/**
	 * Provide a new iterator to iterate over all the instructions.
	 * @return a new iterator of instructions.
	 */
	Iterator<Instruction> iterator();

	/**
	 * Gets the bind variable names that have been specified in the figure definition. 
	 * @return the set of bind variable names, which cannot be null.
	 */
	Set<String> getBindVariableNames();

	/**
	 * Gets the number of instructions in the figure definition.
	 * @return the number of instructions.
	 */
	int size();

	/**
	 * Gets the instruction at a given position in the list of instructions.
	 * @param index the index position (starting at 0 to N-1).
	 * @return the instruction, which cannot be null.
	 * @throws IndexOutOfBoundsException if the <code>index</code> is out of the lists bounds. 
	 */
	Instruction get(int index);

}