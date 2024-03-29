package pmediane;

import pne.FonctionObjectif;

/**
 * Classe impl�mentant l'interface FonctionObjectif sp�cialis�e
 * pour le traitement du probl�me de la p-mediane. La fonction
 * objectif dans ce cas est la somme des distances des entit�s
 * � leur centre le plus proche que l'on souhaite minimis�e.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class FonctionObjectifPMediane implements FonctionObjectif<SolutionPMediane>
{
	/**
	 * Calcule la valeur de la fonction objectif pour le probl�me
	 * de la p-mediane pour la solution fournie.
	 * 
	 * @param s la solution pour laquelle on veut calculer
	 * 			la valeur de la fonction objectif.
	 * @return la valeur de la fonction objectif pour cette solution.
	 */
	public int calculer(SolutionPMediane s)
	{
		int val = 0;
		
		for (int i=0; i<s.getDonnees().getNbEntites(); i++)
			val += s.getDistanceCentre(i);
		
		return val;
	}
	
	/**
	 * Teste si la nouvelle valeur fournie am�liore (au sens large)
	 * la valeur objectif au sens du probl�me de la p-m�diane.
	 * C'est-�-dire si la valeur est diminu�e ou �gale puisqu'on 
	 * veut minimiser la fonction objectif.
	 * 
	 * @param nouvelleVal la nouvelle valeur de la fonction objectif.
	 * @param ancienneVal l'ancienne valeur de la fonction objectif.
	 * @return vrai si et seulement si la nouvelle valeur n'est pas
	 * 		   plus �lev�e que l'ancienne valeur.
	 */
	public boolean estAmelioration(int nouvelleVal, int ancienneVal)
	{
		return (nouvelleVal <= ancienneVal);
	}
	
	/**
	 * Indique si l'objectif est de maximiser la fonction
	 * ou au contraire de la minimiser.
	 * Retourne faux puisque dans le cas du probl�me de la
	 * p-m�diane on a une minimisation.
	 * 
	 * @return faux puisque l'objectif est de minimiser.
	 */
	public boolean estMaximisation()
	{
		return false;
	}
}
