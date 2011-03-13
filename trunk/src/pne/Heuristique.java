package pne;

/**
 * Interface g�n�rique permettant de manipuler des heuristiques
 * permettant de calculer une solution � partir de donn�es.
 * 
 * @param <Data> le type des donn�es manipul�es par l'heuristique.
 * @param <Solution> le type des solution manipul�es par l'heuristique.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public interface Heuristique<Data, Solution>
{
	/**
	 * Calcule une solution la plus optimale possible �
	 * partir des donn�es fournies en utilisant l'heuristique.
	 * 
	 * @param donnees les donn�es auxquelles appliquer l'heuristique.
	 */
	public Solution calculerSolution(Data donnees);
}
