package aotGPSymReg;

import ec.Evolve;

public class Main {

	public static void main(String[] args) {
		String pathToFiles = "/home/anilosmantur/Documents/Ankara_Üni/2_yuksek_lisans/1yıl/1_guz/evolutionary_computation/projects/2_project/";
		int numberOfJobs = 1;
		String statisticType = "ec.gp.koza.KozaShortStatistics";
		String[] runConfig = new String[] {
				Evolve.A_FILE, "src/aotGPSymReg/gpsymreg.params", 
//				"-p", ("stat="+statisticType), 
				"-p", ("stat.file=$out.stat"), 
				"-p", ("jobs="+numberOfJobs),
				"-p", "gp.tree.print-style=dot"
				};
		System.out.println("the evaluation will start.");
		Evolve.main(runConfig);
		
		System.out.println("the evaluation finished.");
		

	}

}

/*
 * # c type 
#gp.tree.print-style = c
# graphviz
*/
