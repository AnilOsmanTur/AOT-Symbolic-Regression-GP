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

public class Div extends GPNode {

	
	@Override
	public int expectedChildren() {
		// Returns the children count of the function 
		return 2;
	}
	
	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual ind, Problem problem) {
		// the result of the function generated
		Data d = ((Data)(input));
		
		children[1].eval(state, thread, input, stack, ind, problem);
		// dividing zero exception 
		if (d.data == 0) {
			d.data = 1;
		} else {
			double child;
			child = d.data;
			
			children[0].eval(state, thread, input, stack, ind, problem);
			d.data = d.data / child;
		}
	}

	@Override
	public String toString() {
		// Returns the symbol of the function
		return "/";
	}

}
