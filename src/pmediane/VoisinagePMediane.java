package pmediane;

import pne.Voisinage;

/**
 * Classe impl�mentant un voisinage pour le probl�me de la
 * p-m�diane. Le voisinage choisi est bas� sur une inversion
 * d'un centre avec une entit� non centre : un centre ouvert
 * est ferm� et une entit� est ouverte � la place. Les affectations
 * des entit�s aux centres sont recalcul�es pour rattacher
 * toute entit� � son centre le plus proche.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class VoisinagePMediane implements Voisinage<SolutionPMediane>
{
	/**
	 * G�n�re une solution au probl�me de la p-m�diane appartenant
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
