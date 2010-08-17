package org.pathwayeditor.figurevm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.pathwayeditor.figure.geometry.PointList;
import org.pathwayeditor.figurevm.IInterpreterErrorHandler.ErrorCode;
import org.pathwayeditor.figurevm.IOpCodeHandler.TextAlignment;
import org.pathwayeditor.figurevm.Instruction.InstructionType;
import org.pathwayeditor.figurevm.Instruction.OpCodes;
import org.pathwayeditor.figurevm.Value.ValueType;

public class InstructionExecutor {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private interface IOpCodeLookup {
		void processOpCode();
	}
	
	private interface ArithmaticOp {
		
		Integer integerOp(Integer a, Integer b);
		
		Double doubleOp(Double a, Double b);

		String getOpName();
		
	}

	private static final int GREATER_VAL = 1;
	private static final int EQUAL_VAL = 0;
	private static final int LESSER_VAL = -1;
	private static final String CHOP_HULL_ANCHOR_CODE = "C";
	private static final String SEMI_FIXED_ANCHOR_CODE = "S";
	
	private final IOpCodeHandler opCodeHandler;
	private InstructionReader reader;
	private final Stack<Value> valueStack;
	private final Map<OpCodes, IOpCodeLookup> opCodeLookup;
	private final Map<String, Value> variableLookup;
	private final Map<String, Value> bindLookup;
	private final IInterpreterErrorHandler errorHandler;
	private final IFigureDefinition instructions;
	private boolean exitCalled = false;

	public InstructionExecutor(IFigureDefinition instructions,
			Map<String, Value> variableLookup, Map<String, Value> bindLookup, 
			IOpCodeHandler opCodehandler, IInterpreterErrorHandler errorHandler) {
		this(instructions, new Stack<Value>(), variableLookup, bindLookup, opCodehandler, errorHandler);
	}
		
	public InstructionExecutor(IFigureDefinition instructions, Stack<Value> operandStack,
			Map<String, Value> variableLookup, Map<String, Value> bindLookup, 
			IOpCodeHandler opCodehandler, IInterpreterErrorHandler errorHandler) {
		this.opCodeLookup = new HashMap<OpCodes, IOpCodeLookup>();
		this.variableLookup = new HashMap<String, Value>(variableLookup);
		this.bindLookup = bindLookup;
		this.valueStack = operandStack;
		this.opCodeHandler = opCodehandler;
		this.errorHandler = errorHandler;
		this.instructions = instructions;
		initOpCodes();
	}
	
	public IFigureDefinition getInstructions(){
		return this.instructions;
	}
	
	public IInterpreterErrorHandler getErrorHandler(){
		return this.errorHandler;
	}
	
	private void initOpCodes(){
		this.opCodeLookup.put(OpCodes.ARC, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processArc();
			}
		});
		this.opCodeLookup.put(OpCodes.OVAL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processOval();
			}
		});
		this.opCodeLookup.put(OpCodes.RECT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processRect();
			}
		});
		this.opCodeLookup.put(OpCodes.RRECT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processRRect();
			}
		});
		this.opCodeLookup.put(OpCodes.DEF, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processDef();
			}
		});
		this.opCodeLookup.put(OpCodes.LINE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processLine();
			}
		});
		this.opCodeLookup.put(OpCodes.POINT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processPoint();
			}
		});
		this.opCodeLookup.put(OpCodes.PGON, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processPgon();
			}
		});
		this.opCodeLookup.put(OpCodes.PLINE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processPline();
			}
		});
		this.opCodeLookup.put(OpCodes.TEXT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processText();
			}
		});
		this.opCodeLookup.put(OpCodes.EXIT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processExit();
			}
		});
		this.opCodeLookup.put(OpCodes.EQ, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processEq();
			}
		});
		this.opCodeLookup.put(OpCodes.NE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processNe();
			}
		});
		this.opCodeLookup.put(OpCodes.LT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processLt();
			}
		});
		this.opCodeLookup.put(OpCodes.GT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processGt();
			}
		});
		this.opCodeLookup.put(OpCodes.LE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processLe();
			}
		});
		this.opCodeLookup.put(OpCodes.GE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processGe();
			}
		});
		this.opCodeLookup.put(OpCodes.IFELSE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processIfelse();
			}
		});
		this.opCodeLookup.put(OpCodes.IF, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processIf();
			}
		});
		this.opCodeLookup.put(OpCodes.SET_FILL_COL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSetfillcol();
			}
		});
		this.opCodeLookup.put(OpCodes.CUR_FILL_COL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processCurfillcol();
			}
		});
		this.opCodeLookup.put(OpCodes.SET_FONT_SIZE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSetfontsize();
			}
		});
		this.opCodeLookup.put(OpCodes.CUR_FONT_SIZE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processCurfontsize();
			}
		});
		this.opCodeLookup.put(OpCodes.SET_FONT_STYLE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSetfontstyle();
			}
		});
		this.opCodeLookup.put(OpCodes.CUR_FONT_STYLE, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processCurfontstyle();
			}
		});
		this.opCodeLookup.put(OpCodes.SET_LINE_COL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSetlinecol();
			}
		});
		this.opCodeLookup.put(OpCodes.CUR_LINE_COL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processCurlinecol();
			}
		});
		this.opCodeLookup.put(OpCodes.ADD, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processAdd();
			}
		});
		this.opCodeLookup.put(OpCodes.SUB, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSub();
			}
		});
		this.opCodeLookup.put(OpCodes.MUL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processMul();
			}
		});
		this.opCodeLookup.put(OpCodes.DIV, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processDiv();
			}
		});
		this.opCodeLookup.put(OpCodes.AND, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processAnd();
			}
		});
		this.opCodeLookup.put(OpCodes.NOT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processNot();
			}
		});
		this.opCodeLookup.put(OpCodes.OR, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processOr();
			}
		});
		this.opCodeLookup.put(OpCodes.EXCH, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processExch();
			}
		});
		this.opCodeLookup.put(OpCodes.DUP, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processDup();
			}
		});
		this.opCodeLookup.put(OpCodes.GPOP, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				popGraphicsState();
			}
		});
		this.opCodeLookup.put(OpCodes.GPUSH, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				pushGraphicsState();
			}
		});
		this.opCodeLookup.put(OpCodes.CURR_LINE_WIDTH, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				currLineWidth();
			}
		});
		this.opCodeLookup.put(OpCodes.SET_LINE_WIDTH, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				setLineWidth();
			}
		});
		this.opCodeLookup.put(OpCodes.SET_LINE_WIDTH, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				setLineWidth();
			}
		});
		this.opCodeLookup.put(OpCodes.FOR, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processFor();
			}
		});
		this.opCodeLookup.put(OpCodes.FORALL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processForall();
			}
		});
		this.opCodeLookup.put(OpCodes.REPEAT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processRepeat();
			}
		});
		this.opCodeLookup.put(OpCodes.LENGTH, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processLength();
			}
		});
		this.opCodeLookup.put(OpCodes.GET, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processGet();
			}
		});
		this.opCodeLookup.put(OpCodes.PUT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processPut();
			}
		});
		this.opCodeLookup.put(OpCodes.ARRAY, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processArray();
			}
		});
		this.opCodeLookup.put(OpCodes.SIN, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSin();
			}
		});
		this.opCodeLookup.put(OpCodes.COS, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processCos();
			}
		});
		this.opCodeLookup.put(OpCodes.ATAN, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processAtan();
			}
		});
		this.opCodeLookup.put(OpCodes.SQRT, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processSqrt();
			}
		});
		this.opCodeLookup.put(OpCodes.LN, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processLn();
			}
		});
		this.opCodeLookup.put(OpCodes.LOG, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processLog();
			}
		});
		this.opCodeLookup.put(OpCodes.EXP, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processExp();
			}
		});
		this.opCodeLookup.put(OpCodes.ROUND, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processRound();
			}
		});
		this.opCodeLookup.put(OpCodes.CEIL, new IOpCodeLookup(){
			@Override
			public void processOpCode() {
				processCeil();
			}
		});
		this.opCodeLookup.put(OpCodes.FLOOR, new IOpCodeLookup(){
			public void processOpCode() {
				processFloor();
			}
		});
		this.opCodeLookup.put(OpCodes.NEG, new IOpCodeLookup(){
			public void processOpCode() {
				processNeg();
			}
		});
		this.opCodeLookup.put(OpCodes.ABS, new IOpCodeLookup(){
			public void processOpCode() {
				processAbs();
			}
		});
		this.opCodeLookup.put(OpCodes.CUR_BOUNDS, new IOpCodeLookup(){
			public void processOpCode() {
				processCurBounds();
			}
		});
		this.opCodeLookup.put(OpCodes.ANCHOR, new IOpCodeLookup(){
			public void processOpCode() {
				processSetAnchor();
			}
		});
		this.opCodeLookup.put(OpCodes.CVS, new IOpCodeLookup(){
			public void processOpCode() {
				processCvs();
			}
		});
		this.opCodeLookup.put(OpCodes.TEXTBOUNDS, new IOpCodeLookup(){
			public void processOpCode() {
				processTextBounds();
			}
		});
	}
	
	/**
	 * 
	 */
	private void processTextBounds() {
		String text = this.valueStack.pop().getStringLiteral();
		List<Double> dimensions = this.opCodeHandler.getTextBounds(text);
		for(Double component : dimensions){
			this.valueStack.push(new Value(component));
		}
	}

	/**
	 * 
	 */
	private void processCvs() {
		String stringConversion = this.valueStack.pop().getValue().toString();
		Value stringValue = new Value(ValueType.STRING_LITERAL, stringConversion);
		this.valueStack.push(stringValue);
	}

	/**
	 * 
	 */
	private void processSetAnchor() {
		String anchorType = this.valueStack.pop().getStringLiteral();
		if(anchorType.equals(CHOP_HULL_ANCHOR_CODE)){
			this.opCodeHandler.setChopHullAnchor();
		}
		else if(anchorType.equals(SEMI_FIXED_ANCHOR_CODE)){
			ValueList array = this.valueStack.pop().getArray();
			List<Double> points = array.getDoubleList();
			this.opCodeHandler.setSemiFixedAnchorCode(PointList.createFromDoubles(points));
		}
	}

	/**
	 * 
	 */
	private void processCurBounds() {
		List<Double> bounds = this.opCodeHandler.getCurBounds();
		for(Double component : bounds){
			this.valueStack.push(new Value(component));
		}
	}

	private void processAbs() {
		Value operand = this.valueStack.pop();
		Value result = null;
		if(operand.getType().equals(ValueType.INTEGER)){
			result = new Value(Math.abs(operand.getInteger()));
		}
		else{
			result = new Value(Math.abs(operand.getDouble()));
		}
		this.valueStack.push(result);
	}

	private void processNeg() {
		Value operand = this.valueStack.pop();
		Value result = null;
		if(operand.getType().equals(ValueType.INTEGER)){
			result = new Value(-operand.getInteger());
		}
		else{
			result = new Value(-operand.getDouble());
		}
		this.valueStack.push(result);
	}

	private void processFloor() {
		double input = this.valueStack.pop().getDouble();
		double result = Math.floor(input);
		this.valueStack.push(new Value(result));
	}

	private void processCeil() {
		double input = this.valueStack.pop().getDouble();
		double result = Math.ceil(input);
		this.valueStack.push(new Value(result));
	}

	private void processRound() {
		double input = this.valueStack.pop().getDouble();
		long result = Math.round(input);
		this.valueStack.push(new Value((int)result));
	}

	private void processExp() {
		double input = this.valueStack.pop().getDouble();
		double result = Math.exp(input);
		this.valueStack.push(new Value(result));
	}

	private void processLog() {
		double input = this.valueStack.pop().getDouble();
		double result = Math.log10(input);
		this.valueStack.push(new Value(result));
	}

	private void processLn() {
		double input = this.valueStack.pop().getDouble();
		double result = Math.log(input);
		this.valueStack.push(new Value(result));
	}

	private void processSqrt() {
		double input = this.valueStack.pop().getDouble();
		double result = Math.sqrt(input);
		this.valueStack.push(new Value(result));
	}

	private void processAtan() {
		double angle = this.valueStack.pop().getDouble();
		double result = Math.atan(Math.toRadians(angle));
		this.valueStack.push(new Value(result));
		if(logger.isDebugEnabled()){
			logger.debug("processATan: angle=" + angle + ",result=" + result);
		}
	}

	private void processCos() {
		double angle = this.valueStack.pop().getDouble();
		double result = Math.cos(Math.toRadians(angle));
		this.valueStack.push(new Value(result));
		if(logger.isDebugEnabled()){
			logger.debug("processCos: angle=" + angle + ",result=" + result);
		}
	}

	private void processSin() {
		double angle = this.valueStack.pop().getDouble();
		double result = Math.sin(Math.toRadians(angle));
		this.valueStack.push(new Value(result));
		if(logger.isDebugEnabled()){
			logger.debug("processSin: angle=" + angle + ",result=" + result);
		}
	}

	private void processArray() {
		int arrayLen = this.valueStack.pop().getInteger();
		ValueList newList = new ValueList(arrayLen);
		this.valueStack.push(new Value(newList));
		if(logger.isDebugEnabled()){
			logger.debug("processArray: new array=" + newList);
		}
	}

	private void processPut() {
		Value val = this.valueStack.pop(); 
		int idx = this.valueStack.pop().getInteger();
		ValueList listObject = this.valueStack.pop().getArray();
		ValueList newList = listObject.put(idx, val);
		this.valueStack.push(new Value(newList));
		if(logger.isDebugEnabled()){
			logger.debug("procesPut: new array=" + newList + ",idx=" + idx + ",orig array=" + listObject);
		}
	}

	private void processGet() {
		int idx = this.valueStack.pop().getInteger();
		ValueList valueList = this.valueStack.pop().getArray();
		Value val = valueList.get(idx);
		this.valueStack.push(val);
		if(logger.isDebugEnabled()){
			logger.debug("procesGet: val=" + val + ",idx=" + idx + ",array=" + valueList);
		}
	}

	private void processLength() {
		Value value = this.valueStack.pop();
		Value newVal = null; 
		if(value.getType().equals(ValueType.ARRAY)){
			ValueList valueList = value.getArray();
			newVal = new Value(valueList.size());
		}
		else if(value.getType().equals(ValueType.STRING_LITERAL)){
			int len = value.getStringLiteral().length();
			newVal = new Value(len);
		}
		else {
			this.errorHandler.reportError(ErrorCode.UNEXPECTED_TYPE, value);
			newVal = new Value();
		}
		this.valueStack.push(newVal);
		if(logger.isDebugEnabled()){
			logger.debug("processLength:length=" + newVal + " for list=" + value);
		}
	}

	private void processRepeat() {
		IFigureDefinition block = this.valueStack.pop().getPackedArray();
		int repeatCount = this.valueStack.pop().getInteger();
		InstructionExecutor procExec = new InstructionExecutor(block, this.valueStack, this.variableLookup,
				this.bindLookup, this.opCodeHandler, this.errorHandler);
		for(int i = 0; i < repeatCount && !procExec.wasExitCalled(); i++){
			procExec.execute();
		}
	}

	private void processForall() {
		IFigureDefinition block = this.valueStack.pop().getPackedArray();
		ValueList valList = this.valueStack.pop().getArray();
		InstructionExecutor procExec = new InstructionExecutor(block, this.valueStack, this.variableLookup,
				this.bindLookup, this.opCodeHandler, this.errorHandler);
		Iterator<Value> iter = valList.iterator();
		while(iter.hasNext() && !procExec.wasExitCalled()){
			Value val = iter.next();
			this.valueStack.push(val);
			procExec.execute();
		}
	}

	private void processFor() {
		IFigureDefinition block = this.valueStack.pop().getPackedArray();
		final Value limit = this.valueStack.pop();
		final Value increment = this.valueStack.pop();
		final Value initial = this.valueStack.pop();
		InstructionExecutor procExec = new InstructionExecutor(block, this.valueStack, this.variableLookup,
				this.bindLookup, this.opCodeHandler, this.errorHandler);
		for(double i = initial.getDouble();
		// if increment +ve then stop when this is exceeded otherwise stop when value is below limit
			(increment.getDouble() >= 0 ? (i < limit.getDouble()) : (i > limit.getDouble())) && !procExec.wasExitCalled();
				i += increment.getDouble()){
				if(limit.getType().equals(increment.getType()) && increment.getType().equals(initial.getType()) && initial.getType().equals(ValueType.INTEGER)){
					// if all integers then add the counter as an int 
					this.valueStack.push(new Value((int)i));
				}
				else{
					// if not all integers then add the counter as a float 
					this.valueStack.push(new Value(i));
				}
				procExec.execute();
			}
	}

	private boolean wasExitCalled() {
		return this.exitCalled ;
	}

	private void setLineWidth() {
		double lineWidth = this.valueStack.pop().getDouble();
		this.opCodeHandler.setLineWidth(lineWidth);
	}

	private void currLineWidth() {
		double lineWidth = this.opCodeHandler.currentLineWidth();
		this.valueStack.push(new Value(lineWidth));
	}

	private void pushGraphicsState() {
		this.opCodeHandler.saveGraphicsState();
	}

	private void popGraphicsState() {
		this.opCodeHandler.restoreGraphicsState();
	}

	private void processDup() {
		Value orig = this.valueStack.pop();
		Value copy = new Value(orig);
		this.valueStack.push(orig);
		this.valueStack.push(copy);
		if(logger.isDebugEnabled()){
			logger.debug("Duplicated values in stack: orig=" + orig + ",copy=" + copy);
		}
	}

	private void processExch() {
		// swap the instructions on the stack
		Value instA = this.valueStack.pop();
		Value instB = this.valueStack.pop();
		this.valueStack.push(instA);
		this.valueStack.push(instB);
		if(logger.isDebugEnabled()){
			logger.debug("Exchanged values in stack: lower=" + instA + ",higher=" + instB);
		}
	}

	private void processCurfontstyle() {
		String styleString = this.opCodeHandler.getCurFontStyle();
		this.valueStack.push(Value.createStringLiteral(styleString));
	}

	private void processSetfontstyle() {
		String styleString = this.valueStack.pop().getStringLiteral();
		this.opCodeHandler.setFontStyle(styleString);
	}

	private void processCurfontsize() {
		double fontSize = this.opCodeHandler.getCurFontSize();
		this.valueStack.push(new Value(fontSize));
	}

	private void processSetfontsize() {
		double fontSize = this.valueStack.pop().getDouble();
		this.opCodeHandler.setFontSize(fontSize);
	}

	private void processOr() {
		Boolean a = this.valueStack.pop().getBoolean();
		Boolean b = this.valueStack.pop().getBoolean();
		this.valueStack.push(new Value(a || b));
	}

	private void processNot() {
		Boolean a = this.valueStack.pop().getBoolean();
		this.valueStack.push(new Value(!a));
	}

	private void processAnd() {
		Boolean a = this.valueStack.pop().getBoolean();
		Boolean b = this.valueStack.pop().getBoolean();
		this.valueStack.push(new Value(a && b));
	}

	private void processArithmeticOp(ArithmaticOp op){
		Value instB = this.valueStack.pop();
		Value instA = this.valueStack.pop();
		Value result = null;
		// if both size are an integer then return an integer result
		if(instA.getValue() instanceof Integer && instB.getValue() instanceof Integer){
			Integer a = instA.getInteger();
			Integer b = instB.getInteger();
			Integer res =  op.integerOp(a, b);
			result = new Value(res);
		}
		else{
			// otherwise convert to a double and return a double result
			Number a = instA.getDouble();
			Number b = instB.getDouble();
			Double res =  op.doubleOp(a.doubleValue(), b.doubleValue());
			result = new Value(res);
		}
		this.valueStack.push(result);
		if(logger.isDebugEnabled()){
			logger.debug("Arithmetic op: " + op.getOpName() + " a=" + instA.getValue() + ",b=" + instB.getValue()
					+ " : result=" + result);
		}
	}
	
	private void processDiv(){
		processArithmeticOp(new ArithmaticOp(){

			public Double doubleOp(Double a, Double b) {
				return a/b;
			}

			public Integer integerOp(Integer a, Integer b) {
				return a/b;
			}

			public String getOpName() {
				return "div";
			}
			
		});
	}
	
	private void processMul() {
		processArithmeticOp(new ArithmaticOp(){

			public Double doubleOp(Double a, Double b) {
				return a*b;
			}

			public Integer integerOp(Integer a, Integer b) {
				return a*b;
			}
			
			public String getOpName() {
				return "mul";
			}
			
		});
	}

	private void processSub() {
		processArithmeticOp(new ArithmaticOp(){

			public Double doubleOp(Double a, Double b) {
				return a-b;
			}

			public Integer integerOp(Integer a, Integer b) {
				return a-b;
			}
			
			public String getOpName() {
				return "sub";
			}
			
		});
	}

	private void processAdd() {
		processArithmeticOp(new ArithmaticOp(){

			public Double doubleOp(Double a, Double b) {
				return a+b;
			}

			public Integer integerOp(Integer a, Integer b) {
				return a+b;
			}
			
			public String getOpName() {
				return "add";
			}
			
		});
	}

	private void processCurlinecol() {
		List<Integer> curLineCol = this.opCodeHandler.getCurLineColour();
		if(curLineCol == null){
			// put a null value on the stack
			this.valueStack.push(new Value());
		}
		else{
			for(Integer component : curLineCol){
				this.valueStack.push(new Value(component));
			}
		}
	}

	private void processSetlinecol() {
		Value instType = this.valueStack.peek();
		if(instType.getValue() == null){
			this.valueStack.pop(); // get null value off the stack
			this.opCodeHandler.setNoLine();
		}
		else{
			int blue = this.valueStack.pop().getInteger();
			int green = this.valueStack.pop().getInteger();
			int red = this.valueStack.pop().getInteger();
			this.opCodeHandler.setLineColour(red, green, blue);
		}
	}

	private void processCurfillcol() {
		List<Integer> curFill = this.opCodeHandler.getCurFillColour();
		if(curFill == null){
			// put a null value on the stack
			this.valueStack.push(new Value());
		}
		else{
			for(Integer component : curFill){
				this.valueStack.push(new Value(component));
			}
		}
	}

	private void processSetfillcol() {
		Value instType = this.valueStack.peek();
		if(instType.isNull()){
			this.valueStack.pop(); // get null value off the stack
			this.opCodeHandler.setNoFill();
		}
		else{
			int blue = this.valueStack.pop().getInteger();
			int green = this.valueStack.pop().getInteger();
			int red = this.valueStack.pop().getInteger();
			this.opCodeHandler.setFillColour(red, green, blue);
		}
	}

	private void reportError(String msg) {
		this.errorHandler.reportError(msg);
	}

	private void processIfelse() {
		Value elseBlock = this.valueStack.pop();
		Value ifBlock = this.valueStack.pop();
		Boolean ifTestResult = this.valueStack.pop().getBoolean();
		if(ifTestResult){
			execProcedure(ifBlock.getPackedArray());
		}
		else{
			execProcedure(elseBlock.getPackedArray());
		}
	}
	
	
	private void processIf() {
		IFigureDefinition ifBlock = this.valueStack.pop().getPackedArray();
		Boolean ifTestResult = this.valueStack.pop().getBoolean();
		if(ifTestResult){
			execProcedure(ifBlock);
		}
	}
	
	
	private void execProcedure(IFigureDefinition instList){
		InstructionExecutor procExec = new InstructionExecutor(instList, this.valueStack, this.variableLookup,
					this.bindLookup, this.opCodeHandler, this.errorHandler);
		procExec.execute();
	}
	

	private void processGe() {
		Double val1 = this.valueStack.pop().getDouble();
		Double val2 = this.valueStack.pop().getDouble();
		int result = val2.compareTo(val1);
		boolean boolRes = result == GREATER_VAL || result == EQUAL_VAL;
		Value inst = new Value(boolRes);
		this.valueStack.push(inst);
	}

	private void processLe() {
		Double val1 = this.valueStack.pop().getDouble();
		Double val2 = this.valueStack.pop().getDouble();
		int result = val2.compareTo(val1);
		boolean boolRes = result == LESSER_VAL || result == EQUAL_VAL;
		Value inst = new Value(boolRes);
		this.valueStack.push(inst);
	}

	private void processGt() {
		Double val1 = this.valueStack.pop().getDouble();
		Double val2 = this.valueStack.pop().getDouble();
		int result = val2.compareTo(val1);
		boolean boolRes = result == GREATER_VAL;
		Value inst = new Value(boolRes);
		this.valueStack.push(inst);
	}

	private void processLt() {
		Double val1 = this.valueStack.pop().getDouble();
		Double val2 = this.valueStack.pop().getDouble();
		int result = val2.compareTo(val1);
		boolean boolRes = result == LESSER_VAL;
		Value inst = new Value(boolRes);
		this.valueStack.push(inst);
	}

	private void processNe() {
		Object val1 = this.valueStack.pop().getValue();
		Object val2 = this.valueStack.pop().getValue();
		boolean result = !val1.equals(val2);
		Value inst = new Value(result);
		this.valueStack.push(inst);
	}

	private void processEq() {
		Object val1 = this.valueStack.pop().getValue();
		Object val2 = this.valueStack.pop().getValue();
		boolean result = val1.equals(val2);
		Value inst = new Value(result);
		this.valueStack.push(inst);
	}

	private void processText() {
		String text = this.valueStack.pop().getStringLiteral();
		String alignmentStr = this.valueStack.pop().getStringLiteral();
		double y = this.valueStack.pop().getDouble();
		double x = this.valueStack.pop().getDouble();
		TextAlignment alignment = TextAlignment.createFromString(alignmentStr);
		if(alignment == null){
			this.reportError("Unrecognised alignment value:" + alignmentStr);
		}
		this.opCodeHandler.handleText(x, y, alignment, text);
	}

	private void processArc() {
		int length = this.valueStack.pop().getInteger();
		int offset = this.valueStack.pop().getInteger();
		double height = this.valueStack.pop().getDouble();
		double width = this.valueStack.pop().getDouble();
		double y = this.valueStack.pop().getDouble();
		double x = this.valueStack.pop().getDouble();
		this.opCodeHandler.handleArc(x, y, width, height, offset, length);
	}

	private void processRect() {
		double height = this.valueStack.pop().getDouble();
		double width = this.valueStack.pop().getDouble();
		double y = this.valueStack.pop().getDouble();
		double x = this.valueStack.pop().getDouble();
		this.opCodeHandler.handleRectangle(x, y, width, height);
	}

	private void processRRect() {
		double arcHeight = this.valueStack.pop().getDouble();
		double arcWidth = this.valueStack.pop().getDouble();
		double height = this.valueStack.pop().getDouble();
		double width = this.valueStack.pop().getDouble();
		double y = this.valueStack.pop().getDouble();
		double x = this.valueStack.pop().getDouble();
		this.opCodeHandler.handleRoundRectangle(x, y, width, height, arcWidth, arcHeight);
	}

	private void processOval() {
		double height = this.valueStack.pop().getDouble();
		double width = this.valueStack.pop().getDouble();
		double y = this.valueStack.pop().getDouble();
		double x = this.valueStack.pop().getDouble();
		this.opCodeHandler.handleOval(x, y, width, height);
	}

	private void processLine() {
		double endY = this.valueStack.pop().getDouble();
		double endX = this.valueStack.pop().getDouble();
		double startY = this.valueStack.pop().getDouble();
		double startX = this.valueStack.pop().getDouble();
		this.opCodeHandler.handleLine(startX, startY, endX, endY);
	}

	private void processPoint() {
		double y = this.valueStack.pop().getDouble();
		double x = this.valueStack.pop().getDouble();
		this.opCodeHandler.handlePoint(x, y);
	}

	private void processDef() {
		Value varValue = this.valueStack.pop(); 
		String varName = this.valueStack.pop().getLiteralVariableName();
		this.variableLookup.put(varName, varValue);
		if(logger.isDebugEnabled()){
			logger.debug("Define variable name=" + varName + ",value=" + varValue);
		}
	}

	private double[] createPointArray(List<Double> pointList){
		double pointArr[] = new double[pointList.size()];
		int idx = 0;
		for (Double val : pointList) {
			pointArr[idx] = val;
			idx++;
		}
		return pointArr;
	}
	
	private void processPline() {
		ValueList valueList = this.valueStack.pop().getArray();
		List<Double> pointList = valueList.getDoubleList();
		this.opCodeHandler.handlePolyline(createPointArray(pointList));
	}

	private void processPgon() {
		ValueList valueList = this.valueStack.pop().getArray();
		List<Double> pointList = valueList.getDoubleList();
		this.opCodeHandler.handlePolygon(createPointArray(pointList));
	}

	private void processExit() {
			reader.finish();
//			this.valueStack.empty();
			this.exitCalled = true;
	}

	public IOpCodeHandler getOpCodeHandler() {
		return opCodeHandler;
	}
	
	public void execute() {
		this.exitCalled = false;
		this.reader = new InstructionReader(instructions.iterator());
		while (reader.hasNext()) {
			Instruction inst = reader.next();
			if (inst.getType().equals(InstructionType.OPCODE)) {
				OpCodes opCode = (OpCodes) inst.getValue();
				IOpCodeLookup lookup = this.opCodeLookup.get(opCode);
				if(lookup != null){
					lookup.processOpCode();
				}
				else{
					this.reportError("Unrecognised opcode=" + opCode);
				}
			}
			else if(inst.getType().equals(InstructionType.VARIABLE_NAME)){
				// lookup variable
				Value lookup = this.variableLookup.get((String)inst.getValue());
				if(lookup == null)
					throw new IllegalStateException("No variable name found matching: " + inst.getValue());
				
				if(lookup.getType().equals(ValueType.PROCEDURE)){
					InstructionExecutor arrayExec = new InstructionExecutor(lookup.getPackedArray(), this.valueStack,
							variableLookup, bindLookup, opCodeHandler, errorHandler);
					if(logger.isDebugEnabled()){
						logger.debug("Executing procedure: name=" + inst.getValue() + ",stack=" + this.valueStack);
					}
					arrayExec.execute();
				}
				else{
					this.valueStack.push(lookup);
					if(logger.isDebugEnabled()){
						logger.debug("Added to exanded variable to stack: name=" + inst.getValue() +",value=" + lookup.getValue());
					}
				}
			}
			else if(inst.getType().equals(InstructionType.BOUND_VALUE)){
				// lookup variable
				Value lookup = this.bindLookup.get((String)inst.getValue());
				if(lookup == null) throw new IllegalStateException("No binding found for name: " + inst.getValue());
				this.valueStack.push(lookup);
				if(logger.isDebugEnabled()){
					logger.debug("Added bound value to stack: name=" + inst.getValue() + ",value=" + lookup.getValue());
				}
			}
			else if(inst.getType().equals(InstructionType.RAW_ARRAY)){
				// need to pop everything off the array and execute it.
				// the resulting stack is the array
				Stack<Value> arrayStack = new Stack<Value>();
				InstructionExecutor arrayExec = new InstructionExecutor((IFigureDefinition)inst.getValue(), arrayStack,
						variableLookup, bindLookup, opCodeHandler, errorHandler);
				arrayExec.execute();
				ValueList valList = new ValueList(arrayStack);
				this.valueStack.push(new Value(valList));
				if(logger.isDebugEnabled()){
					logger.debug("Added to stack: value=" + valList);
				}
			}
			else{
				Value val = inst.getTypedValue();
				this.valueStack.push(val);
				if(logger.isDebugEnabled()){
					logger.debug("Added to stack: value=" + val);
				}
			}
		}
	}
}
