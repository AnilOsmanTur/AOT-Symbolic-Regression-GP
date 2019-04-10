/*
 * Anil Osman Tur
 * Evolutionary Computation Project 2
 * Physagor theorem symbolic regression
 * 
 * The value
 */

package aotGPSymReg;

import ec.gp.GPData;

public class Data extends GPData {
    public double data;
    
    public void copyTo(final GPData gpd) {
    	((Data)gpd).data = data;
    }
}
