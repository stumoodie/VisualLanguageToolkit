/*
 * Copyright 2009-2011, Court of the University of Edinburgh
 */
package org.pathwayeditor.figure.definition;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * FigureDefinitionInterpreter
 *
 * @author Stuart Moodie
 *
 */
public class FigureDefinitionInterpreter implements IFigureDefinitionInterpreter {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private final Map<String, Value> variableLookup;
	private final Map<String, Value> bindingLookup;
	private final InstructionExecutor executor;
	
	public FigureDefinitionInterpreter(ICompiledFigureDefinition instructions, IOpCodeHandler opCodehandler,
										IInterpreterErrorHandler errorHandler) {
		this.variableLookup  = new HashMap<String, Value>();
		this.bindingLookup  = new HashMap<String, Value>();
		this.executor = new InstructionExecutor(instructions, this.variableLookup, this.bindingLookup, opCodehandler, errorHandler);
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getInstructions()
	 */
	@Override
	public ICompiledFigureDefinition getCompiledFigureDefinition(){
		return this.executor.getInstructions();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getErrorHandler()
	 */
	@Override
	public IInterpreterErrorHandler getErrorHandler(){
		return this.executor.getErrorHandler();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#setBindInteger(java.lang.String, java.lang.Integer)
	 */
	@Override
	public void setBindInteger(String name, Integer value){
		this.bindingLookup.put(name, new Value(value));
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#setBindBoolean(java.lang.String, java.lang.Boolean)
	 */
	@Override
	public void setBindBoolean(String name, Boolean value){
		this.bindingLookup.put(name, new Value(value));
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#setBindDouble(java.lang.String, java.lang.Double)
	 */
	@Override
	public void setBindDouble(String name, Double value){
		this.bindingLookup.put(name, new Value(value));
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#setBindString(java.lang.String, java.lang.String)
	 */
	@Override
	public void setBindString(String name, String value){
		this.bindingLookup.put(name, Value.createStringLiteral(value));
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getOpCodeHandler()
	 */
	@Override
	public IOpCodeHandler getOpCodeHandler() {
		return this.executor.getOpCodeHandler();
	}
	
	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#execute()
	 */
	@Override
	public void execute() {
		logger.debug("Starting interpreter.");
		this.executor.execute();
		logger.debug("Interpreter completed.");
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getBindBooleanValue(java.lang.String)
	 */
	@Override
	public Boolean getBindBooleanValue(String name) {
		Boolean retVal = null;
		Value val = this.bindingLookup.get(name);
		if(val != null){
			retVal = val.getBoolean();
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getBindIntegerValue(java.lang.String)
	 */
	@Override
	public Integer getBindIntegerValue(String name) {
		Integer retVal = null;
		Value val = this.bindingLookup.get(name);
		if(val != null){
			retVal = val.getInteger();
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getBindDoubleValue(java.lang.String)
	 */
	@Override
	public Double getBindDoubleValue(String name) {
		Double retVal = null;
		Value val = this.bindingLookup.get(name);
		if(val != null){
			retVal = val.getDouble();
		}
		return retVal;
	}

	/* (non-Javadoc)
	 * @see org.pathwayeditor.figure.definition.IFigureDefinitionInterpreter#getBindStringValue(java.lang.String)
	 */
	@Override
	public String getBindStringValue(String name) {
		String retVal = null;
		Value val = this.bindingLookup.get(name);
		if(val != null){
			retVal = val.getStringLiteral();
		}
		return retVal;
	}
}
