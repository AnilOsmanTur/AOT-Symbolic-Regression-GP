/*
 * Anil Osman Tur
 * Evolutionary Computation Project 2
 * Physagor theorem symbolic regression
 * 
 * The problem
 */


package aotGPSymReg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;

public class SymbolicRegression extends GPProblem implements SimpleProblemForm {

	public static final String P_DATA = "data";
	
	public double currentA;
	public double currentB;
	
	public ArrayList<Double> inputA = new ArrayList<Double>();
	public ArrayList<Double> inputB = new ArrayList<Double>();
	public ArrayList<Double> inputC = new ArrayList<Double>();


	@Override
	public void setup(EvolutionState state, Parameter base) {
		// TODO Auto-generated method stub
		super.setup(state, base);
		
		// verify our input is the right class
		if (!(input instanceof Data)) {
			state.output.fatal("GPData class must be subclass from " + Data.class,
					base.push(P_DATA), null);
		}
		
		readFromFile();
		
	}



	@Override
	public void evaluate(EvolutionState state, Individual ind, int subpop, int threadnum) {
		// Evaluation process
		
		if (!ind.evaluated) {
			Data input = (Data)(this.input);
			
			int hits = 0;
			double sum = 0.0;
			double expectedResult;
			double result;
			
			//System.out.println("training size: " + Integer.toString(inputA.size()) );
			for (int i = 0; i < inputA.size(); i++) {
				currentA = inputA.get(i);
				currentB = inputB.get(i);
				expectedResult = inputC.get(i);
				
				((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
				//System.out.print("result: " + Double.toString(input.data) + " ");
				
				result = Math.abs(expectedResult - input.data);
				if (result <= 0.01) hits++;
				sum += result;
				//System.out.print("loss: " + Double.toString(result) + "\n");
			}
			
			// the fitness better be KozaFitness!
            KozaFitness f = ((KozaFitness)ind.fitness);
            f.setStandardizedFitness(state, sum);
            f.hits = hits;
            ind.evaluated = true;
		
           // writeFile(f.fitness(), f.standardizedFitness());
            
		}
		
	}
	
	private void readFromFile() {
		Scanner scan;
		File file = new File("src/aotGPSymReg/data.txt");
		try {
			scan = new Scanner(file);
			int indexOfFile = 0;
			while (scan.hasNextDouble()) {
				double number = scan.nextDouble();
				System.out.print("num: " + Double.toString(number) + " ");
				if (indexOfFile % 3 == 0) {
					inputA.add(number);
				} else if (indexOfFile % 3 == 1) {
					inputB.add(number);
				} else {
					inputC.add(number);
					System.out.print("\n");
				}
				indexOfFile++;
			}
			System.out.println("Data file readed " + Integer.toString(indexOfFile) );
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}

}
