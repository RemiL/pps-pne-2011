package pmediane;

import java.util.HashMap;
import java.util.Map.Entry;

import ilog.concert.IloException;
import ilog.concert.IloIntExpr;
import ilog.concert.IloIntVar;
import ilog.cplex.IloCplex;
import pne.Heuristique;

/**
 * Classe impl�mentant un solveur du PLNE de la p-m�diane
 * bas� sur CPLEX.
 * Ce probl�me vise � choisir P centres parmi N entit�s tels que 
 * la somme des distances des entit�s � leur centre le plus proche
 * soit minimale.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class CPLEXPMediane implements Heuristique<PLNEPMediane, SolutionPMediane>
{
	private IloCplex modele;
	private IloIntVar[] variables; 
	
	/**
	 * Cr�e un solveur pour le PLNE de la p-m�diane
	 * bas� sur CPLEX.
	 */
	public CPLEXPMediane()
	{
		try {
			modele = new IloCplex();
		} catch (IloException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne la solution au PLNE de la p-m�diane fourni
	 * calcul� par le solveur CPLEX. Si la r�solution �choue
	 * cette m�thode retournera null.
	 * 
	 * @param donnees l'instance du probl�me � r�soudre.
	 * @return la solution au PLNE calcul�e par CPLEX
	 * 		   ou null en cas d'�chec.
	 */
	public SolutionPMediane calculerSolution(PLNEPMediane donnees)
	{
		SolutionPMediane s = null;
		
		try {
			modele.clearModel();
			
			// Cr�ation des variables
			variables = modele.boolVarArray(donnees.getNbVariables(), donnees.getNomsVariables());
			
			// D�finition de la fonction objectif
			modele.addMinimize(modele.scalProd(variables, donnees.getFonctionObjectif()));
			
			// Ajout des contraintes
			int num = 0;
			IloIntExpr expr;
			for (HashMap<Integer, Integer> c : donnees.getContraintes())
			{
				expr = modele.intExpr();
				for (Entry<Integer, Integer> e : c.entrySet())
					expr = modele.sum(expr, modele.prod(e.getValue(), variables[e.getKey()]));
				
				switch (donnees.getTypesContraintes()[num])
				{
					case EGAL:
						modele.addEq(expr, donnees.getSecondsMembresContraintes()[num]);
						break;
	
					case INF_EGAL:
						modele.addLe(expr, donnees.getSecondsMembresContraintes()[num]);
						break;
						
					case SUP_EGAL:
						modele.addGe(expr, donnees.getSecondsMembresContraintes()[num]);
						break;
				}
				
				num++;
			}
			
			if (modele.solve())
			{
				int c = 0;
				int centres[] = new int[donnees.getDonnees().getNbCentres()];
				int affectations[] = new int[donnees.getDonnees().getNbEntites()];
				int affectationsSecondaires[] = new int[donnees.getDonnees().getNbEntites()];
				double[] val = modele.getValues(variables);
				
				for (int i=0; i<donnees.getDonnees().getNbEntites(); i++)
				{
					for (int j=0; j<donnees.getDonnees().getNbEntites(); j++)
					{
						if (val[i*donnees.getDonnees().getNbEntites() + j] == 1.0)
						{
							affectations[i] = j;
							
							if (i == j)
								centres[c++] = j;
						}
					}
				}
				
				s = new SolutionPMediane(donnees.getDonnees(), centres, affectations, affectationsSecondaires);
			}
		} catch (IloException e) {
			e.printStackTrace();
		}
		
		return s;
	}
}
