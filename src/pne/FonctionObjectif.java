package pne;

/**
 * Interface générique permettant de manipuler des fonctions
 * objectifs capable de calculer la valeur correspondant à
 * une solution et de statuer sur le fait de savoir si le
 * passage d'une certaine valeur resultat à une autre constitue
 * ou non une amélioration de la fonction objectif.
 * 
 * @param <Solution> le type des solution manipulées par 
 *                   la fonction objectif.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public interface FonctionObjectif<Solution>
{
	/**
	 * Calcule la valeur de la fonction objectif
	 * pour la solution fournie.
	 * 
	 * @param s la solution pour laquelle la valeur de
	 * 			la fonction objectif doit être calculée.
	 * @return la valeur de la fonction objectif pour 
	 * 		   cette solution.
	 */
	public int calculer(Solution s);
	
	/**
	 * Indique si la passage de l'ancienne valeur objectif
	 * à la nouvelle valeur constitue une amélioration.
	 * 
	 * @param nouvelleVal la nouvelle valeur de la fonction objectif.
	 * @param ancienneVal l'ancienne valeur de la fonction objectif.
	 * @return vrai si et seulement si la nouvelle valeur ne dégrade
	 * 		   pas la fonction objectif.
	 */
	public boolean estAmelioration(int nouvelleVal, int ancienneVal);
}
