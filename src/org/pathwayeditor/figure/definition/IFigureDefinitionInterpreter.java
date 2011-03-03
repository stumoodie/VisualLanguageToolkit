/*
Copyright 2009-2011, Court of the University of Edinburgh
 */

package org.pathwayeditor.figure.definition;

/**
 * IFigureDefinitionInterpreter
 *
 * @author Stuart Moodie
 *
 */
public interface IFigureDefinitionInterpreter {

	ICompiledFigureDefinition getCompiledFigureDefinition();

	IInterpreterErrorHandler getErrorHandler();

	void setBindInteger(String name, Integer value);

	void setBindBoolean(String name, Boolean value);

	void setBindDouble(String name, Double value);

	void setBindString(String name, String value);

	IOpCodeHandler getOpCodeHandler();

	void execute();

	Boolean getBindBooleanValue(String name);

	Integer getBindIntegerValue(String name);

	Double getBindDoubleValue(String name);

	String getBindStringValue(String name);

}