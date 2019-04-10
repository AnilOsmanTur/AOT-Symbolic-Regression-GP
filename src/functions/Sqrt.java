/*
 * Anil Osman Tur
 * Evolutionary Computation Project 2
 * Physagor theorem symbolic regression
 * 
 * The function
 */


package functions;

import aotGPSymReg.Data;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Sqrt extends GPNode {

	
	@Override
	public int expectedChildren() {
		// Returns the children count of the function 
		return 1;
	}
	
	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual ind, Problem problem) {
		// the result of the function generated
		Data d = ((Data)(input));
		
		children[0].eval(state, thread, input, stack, ind, problem);
		// the data have to be positive to take a sqrt
		if (d.data < 0) {
			d.data = 0.0;
		} else {
			d.data = Math.sqrt(d.data);
		}
	}

	@Override
	public String toString() {
		// Returns the symbol of the function
		return "sqrt";
	}

}
