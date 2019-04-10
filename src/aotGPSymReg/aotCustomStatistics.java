package aotGPSymReg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import ec.EvolutionState;
import ec.Statistics;
import ec.gp.GPIndividual;
import ec.gp.GPTree;
import ec.util.Parameter;

public class aotCustomStatistics extends Statistics {

	// The parameter string and log number of the file for our readable population
    public static final String P_CSTYLEFILE = "cstyle-file";
    public int cstyleLog;
	
	// The parameter string and log number of the file for our readable population
    public static final String P_GRAPHFILE = "graph-file";
    public int graphLog;

    // The parameter string and log number of the file for our best-individual individual
    public static final String P_INFOFILE = "info-file";
    public int infoLog;

	//public PrintWriter writer;
    
	@Override
	public void setup(final EvolutionState state, final Parameter base) {
		super.setup(state, base);
		
		// set up popFile
		File graphFile = state.parameters.getFile(base.push(P_GRAPHFILE), null);
		if (graphFile!=null) {
			try {
				graphLog = state.output.addLog(graphFile, true);
			} catch (IOException i) {
				state.output.fatal("An IOException occured while trying to create the log " + graphFile + ":\n" + i);
			}
		}
		
		// set up cpopFile
		File cstyleFile = state.parameters.getFile(base.push(P_CSTYLEFILE), null);
		if (cstyleFile!=null) {
			try {
				cstyleLog = state.output.addLog(cstyleFile, true);
			} catch (IOException i) {
				state.output.fatal("An IOException occured while trying to create the log " + cstyleFile + ":\n" + i);
			}
		}
		
		// set up infoFile
		File infoFile = state.parameters.getFile(base.push(P_INFOFILE), null);
		if (infoFile!=null) {
			try {
				infoLog = state.output.addLog(infoFile, true);
			} catch (IOException i) {
				state.output.fatal("An IOException occured while trying to create the log " + infoFile + ":\n" + i);
			}
		}
	}
	
	@Override
	public void postEvaluationStatistics(final EvolutionState state) {
		super.postEvaluationStatistics(state);
		
		// write out a warning that the next generation is coming 
        state.output.println("-----------------------\nGENERATION " +
        				      state.generation + "\n-----------------------", graphLog);
        
     // write out a warning that the next generation is coming 
        state.output.println("-----------------------\nGENERATION " +
        				      state.generation + "\n-----------------------", cstyleLog);
        // find the best individual
        int best = 0;
        double sum = 0.0;
        double best_fitness = state.population.subpops.get(0).individuals.get(0).fitness.fitness();
        sum += best_fitness;
        for(int y = 1; y < state.population.subpops.get(0).individuals.size(); y++) {
        	double val = state.population.subpops.get(0).individuals.get(y).fitness.fitness();
        	sum += val;
        	if (val > best_fitness) {
        		//System.out.println("Fitness compere " + Double.toString(val) + " " + Double.toString(best_fitness));
        		best = y;
        		best_fitness = val;
            }
        }
        double avg_fitness = sum / state.population.subpops.get(0).individuals.size();
        
        ((GPIndividual)state.population.subpops.get(0).individuals.get(best)).trees[0].printStyle = GPTree.PRINT_STYLE_C;
        ((GPIndividual)state.population.subpops.get(0).individuals.get(best)).trees[0].printTreeForHumans(state, cstyleLog);
        ((GPIndividual)state.population.subpops.get(0).individuals.get(best)).trees[0].printStyle = GPTree.PRINT_STYLE_DOT;
        ((GPIndividual)state.population.subpops.get(0).individuals.get(best)).trees[0].printTreeForHumans(state, graphLog);
        writeFile(state, best_fitness, avg_fitness, infoLog);
	}

	public void writeFile(final EvolutionState state, double best, double avg, int log) {
		state.output.println( Double.toString(best) + " " + Double.toString(avg), log);
	}
	
}
