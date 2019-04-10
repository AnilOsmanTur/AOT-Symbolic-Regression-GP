/*
 * Anil Osman Tur
 * Evolutionary Computation Project 2
 * Physagor theorem symbolic regression
 * 
 * terminal value 
 */


package aotGPSymReg;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class B extends GPNode {

	
	@Override
	public int expectedChildren() {
		return 0;
	}

	@Override
	public void eval(EvolutionState arg0, int arg1, GPData input, ADFStack arg3, GPIndividual arg4, Problem problem) {
		// Returns the value of the terminal
		Data d = (Data)(input);
		d.data = ((SymbolicRegression)problem).currentB;
	}

	@Override
	public String toString() {
		// Returns the string symbol of the node
		return "b";
	}

}
