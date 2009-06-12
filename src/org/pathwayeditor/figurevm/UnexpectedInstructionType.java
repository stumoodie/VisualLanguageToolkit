package org.pathwayeditor.figurevm;

import org.pathwayeditor.figurevm.Instruction.InstructionType;

public final class UnexpectedInstructionType extends RuntimeException {
	private static final long serialVersionUID = 5431620131639720529L;
	private final InstructionType expectedInstruction;
	private final Instruction actualInstruction;
	
	public UnexpectedInstructionType(InstructionType expected, Instruction actualInstruction) {
		super("Unexpected instruction type. Expected: " + expected + ", instn=" + actualInstruction);
		this.expectedInstruction = expected;
		this.actualInstruction = actualInstruction;
	}

	public InstructionType getExpectedInstruction() {
		return expectedInstruction;
	}

	public Instruction getActualInstruction() {
		return actualInstruction;
	}

}
