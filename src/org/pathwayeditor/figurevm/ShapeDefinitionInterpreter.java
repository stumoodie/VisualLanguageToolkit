package org.pathwayeditor.figurevm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ShapeDefinitionInterpreter {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private final Map<String, Value> variableLookup;
	private final Map<String, Value> bindingLookup;
	private final InstructionExecutor executor;
	
	public ShapeDefinitionInterpreter(InstructionList instructions, IOpCodeHandler opCodehandler,
										IInterpreterErrorHandler errorHandler) {
		this.variableLookup  = new HashMap<String, Value>();
		this.bindingLookup  = new HashMap<String, Value>();
		this.executor = new InstructionExecutor(instructions, this.variableLookup, this.bindingLookup, opCodehandler, errorHandler);
	}
	
	public InstructionList getInstructions(){
		return this.executor.getInstructions();
	}
	
	public IInterpreterErrorHandler getErrorHandler(){
		return this.executor.getErrorHandler();
	}
	
	public void setBindInteger(String name, Integer value){
		this.bindingLookup.put(name, new Value(value));
	}
	
	public void setBindBoolean(String name, Boolean value){
		this.bindingLookup.put(name, new Value(value));
	}
	
	public void setBindDouble(String name, Double value){
		this.bindingLookup.put(name, new Value(value));
	}
	
	public void setBindString(String name, String value){
		this.bindingLookup.put(name, Value.createStringLiteral(value));
	}

	public IOpCodeHandler getOpCodeHandler() {
		return this.executor.getOpCodeHandler();
	}
	
	public void execute() {
		logger.debug("Starting interpreter.");
		this.executor.execute();
		logger.debug("Interpreter completed.");
	}
}
