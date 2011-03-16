package pmediane;

import pne.Voisinage;

/**
 * Classe implémentant un voisinage pour le problème de la
 * p-médiane. Le voisinage choisi est basé sur une inversion
 * d'un centre avec une entité non centre : un centre ouvert
 * est fermé et une entité est ouverte à la place. Les affectations
 * des entités aux centres sont recalculées pour rattacher
 * toute entité à son centre le plus proche.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class VoisinagePMediane implements Voisinage<SolutionPMediane>
{
	/**
	 * Génère une solution au problème de la p-médiane appartenant
	 * au voisinage de la solution fournie.
	 * 
	 * @param s la solution de base
	 * @return une solution appartenant au voisinage de la solution
	 * 		   de base.
	 */
	public SolutionPMediane genererSolution(SolutionPMediane s)
	{
		return null;
	}
}
