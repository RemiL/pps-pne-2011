package pmediane;

import pne.Voisinage;
import pne.VoisinageTailleVariable;

/**
 * Classe implémentant un voisinage pour le problème de la
 * p-médiane. Le voisinage choisi est basé sur une inversion
 * d'un centre avec une entité non centre : un centre ouvert
 * est fermé et une entité est ouverte à la place. Les affectations
 * des entités aux centres sont recalculées pour rattacher
 * toute entité à son centre le plus proche. Le voisinage
 * est à taille variable puisque le nombre de permutations à
 * effectuer peut être réglé.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class VoisinagePMediane implements Voisinage<SolutionPMediane>, VoisinageTailleVariable<SolutionPMediane>
{
	/**
	 * Génère une solution au problème de la p-médiane appartenant
	 * au voisinage (basé sur une permutation) de la solution fournie.
	 * 
	 * @param s la solution de base
	 * @return une solution appartenant au voisinage de la solution
	 * 		   de base.
	 */
	public SolutionPMediane genererSolution(SolutionPMediane s)
	{
		return null;
	}

	/**
	 * Génère une solution appartenant au voisinage de taille
	 * indiquée (basé sur k permutations) de la solution fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage à considérer.
	 * @return une solution située dans le voisinage de taille
	 * 		   k de la solution de base.
	 */
	public SolutionPMediane genererSolution(SolutionPMediane s, int k)
	{
		return null;
	}

	/**
	 * Trouve la meilleure solution locale disponible dans le
	 * voisinage de la solution initiale fournie en effectuant
	 * une recherche exhaustive.
	 * 
	 * @param s la solution initiale.
	 * @return la meilleure solution située dans le
	 * 		   voisinage de la solution de base.
	 */
	public SolutionPMediane rechercherMeilleureSolution(SolutionPMediane s)
	{
		return null;
	}
	
	/**
	 * Trouve la meilleure solution locale disponible dans le
	 * voisinage de taille k de la solution initiale fournie en
	 * effectuant une recherche exhaustive.
	 * 
	 * @param s la solution initiale.
	 * @param k la taille du voisinage à considérer.
	 * @return la meilleure solution située dans le voisinage
	 * 		   de taille k de la solution de base.
	 */
	public SolutionPMediane rechercherMeilleureSolution(SolutionPMediane s,	int k)
	{
		return null;
	}
}
