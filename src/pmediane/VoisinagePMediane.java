package pmediane;

import pne.Voisinage;
import pne.VoisinageTailleVariable;

/**
 * Classe impl�mentant un voisinage pour le probl�me de la
 * p-m�diane. Le voisinage choisi est bas� sur une inversion
 * d'un centre avec une entit� non centre : un centre ouvert
 * est ferm� et une entit� est ouverte � la place. Les affectations
 * des entit�s aux centres sont recalcul�es pour rattacher
 * toute entit� � son centre le plus proche. Le voisinage
 * est � taille variable, le nombre de permutations � effectuer
 * est r�glable.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class VoisinagePMediane implements Voisinage<SolutionPMediane>, VoisinageTailleVariable<SolutionPMediane>
{
	/**
	 * G�n�re une solution au probl�me de la p-m�diane appartenant
	 * au voisinage (bas� sur une permutation) de la solution fournie.
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
	 * G�n�re une solution appartenant au voisinage de taille
	 * indiqu�e (bas� sur k permutations) de la solution fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage � consid�rer.
	 * @return une solution situ�e dans le voisinage de taille
	 * 		   k de la solution de base.
	 */
	public SolutionPMediane genererSolution(SolutionPMediane s, int k)
	{
		return null;
	}
}
