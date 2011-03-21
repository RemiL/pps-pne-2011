package pmediane;

import pne.Heuristique;

/**
 * Classe implémentant l'interface Heuristique proposant
 * une heuristique gloutonne pour le problème de la p-médiane
 * reposant sur le principe suivant : commencer par ouvrir le
 * centre qui minimise la distance aux entités puis ouvrir le
 * deuxième centre qui fait que la fonction objectif est 
 * minimale et ainsi de suite jusqu’à avoir ouvert p centres.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class HeuristiqueGloutonnePMediane implements Heuristique<DataPMediane, SolutionPMediane>
{
	/**
	 * Calcule la solution la plus optimale possible en utilisant
	 * l'heuristique gloutonne à partir des données fournies.
	 * 
	 * @param donnees les données sur lesquelles l'heuristique
	 * 				 doit être appliquée.
	 * @return la solution la plus optimale possible construite
	 * 		   avec l'heuristique.
	 */
	public SolutionPMediane calculerSolution(DataPMediane donnees)
	{
		return null;
	}
}
