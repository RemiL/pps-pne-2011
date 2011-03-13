package pne;

/**
 * Interface générique permettant de manipuler des heuristiques
 * permettant de calculer une solution à partir de données.
 * 
 * @param <Data> le type des données manipulées par l'heuristique.
 * @param <Solution> le type des solution manipulées par l'heuristique.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public interface Heuristique<Data, Solution>
{
	/**
	 * Calcule une solution la plus optimale possible à
	 * partir des données fournies en utilisant l'heuristique.
	 * 
	 * @param donnees les données auxquelles appliquer l'heuristique.
	 */
	public Solution calculerSolution(Data donnees);
}
