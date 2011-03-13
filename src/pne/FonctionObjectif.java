package pne;

/**
 * Interface g�n�rique permettant de manipuler des fonctions
 * objectifs capable de calculer la valeur correspondant �
 * une solution et de statuer sur le fait de savoir si le
 * passage d'une certaine valeur resultat � une autre constitue
 * ou non une am�lioration de la fonction objectif.
 * 
 * @param <Solution> le type des solution manipul�es par 
 *                   la fonction objectif.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public interface FonctionObjectif<Solution>
{
	/**
	 * Calcule la valeur de la fonction objectif
	 * pour la solution fournie.
	 * 
	 * @param s la solution pour laquelle la valeur de
	 * 			la fonction objectif doit �tre calcul�e.
	 * @return la valeur de la fonction objectif pour 
	 * 		   cette solution.
	 */
	public int calculer(Solution s);
	
	/**
	 * Indique si la passage de l'ancienne valeur objectif
	 * � la nouvelle valeur constitue une am�lioration.
	 * 
	 * @param nouvelleVal la nouvelle valeur de la fonction objectif.
	 * @param ancienneVal l'ancienne valeur de la fonction objectif.
	 * @return vrai si et seulement si la nouvelle valeur ne d�grade
	 * 		   pas la fonction objectif.
	 */
	public boolean estAmelioration(int nouvelleVal, int ancienneVal);
}
