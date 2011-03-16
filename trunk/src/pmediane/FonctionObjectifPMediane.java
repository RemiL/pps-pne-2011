package pmediane;

import pne.FonctionObjectif;

/**
 * Classe implémentant l'interface FonctionObjectif spécialisée
 * pour le traitement du problème de la p-mediane. La fonction
 * objectif dans ce cas est la somme des distances des entités
 * à leur centre le plus proche que l'on souhaite minimisée.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class FonctionObjectifPMediane implements FonctionObjectif<SolutionPMediane>
{
	/**
	 * Calcule la valeur de la fonction objectif pour le problème
	 * de la p-mediane pour la solution fournie.
	 * 
	 * @param s la solution pour laquelle on veut calculer
	 * 			la valeur de la fonction objectif.
	 * @return la valeur de la fonction objectif pour cette solution.
	 */
	public int calculer(SolutionPMediane s)
	{
		return 0;
	}

	/**
	 * Teste si la nouvelle valeur fournie améliore (au sens large)
	 * la valeur objectif au sens du problème de la p-médiane.
	 * C'est-à-dire si la valeur est diminuée ou égale puisqu'on 
	 * veut minimiser la fonction objectif.
	 * 
	 * @param nouvelleVal la nouvelle valeur de la fonction objectif.
	 * @param ancienneVal l'ancienne valeur de la fonction objectif.
	 * @return vrai si et seulement si la nouvelle valeur n'est pas
	 * 		   plus élevée que l'ancienne valeur.
	 */
	public boolean estAmelioration(int nouvelleVal, int ancienneVal)
	{
		return false;
	}
}
