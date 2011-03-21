package pne;

/**
 * Classe g�n�rique permettant de manipuler des programmes
 * lin�aires en nombres entiers caract�ris� par ses variables,
 * sa fonction objectif et ses contraintes.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public abstract class PLNE
{
	/**
	 * Les types possibles pour les variables.
	 */
	public static enum TypeVar
	{
	    ENTIERE, BOOLEENNE;
	}
	/**
	 * Les types possibles pour l'objectif.
	 */
	public static enum TypeObjectif
	{
	    MIN, MAX;
	}
	/**
	 * Les types possibles pour les contraintes.
	 */
	public static enum TypeContrainte
	{
	    INF_EGAL, EGAL, SUP_EGAL;
	}
	
	/** Le nombre de variables utilis�es dans le PLNE. */
	private int nbVariables;
	/** Le nom des variables utilis�es. */
	private String[] nomsVariables;
	/** Le nom des variables utilis�es. */
	private TypeVar[] typesVariables;
	/** Les coefficients de la fonction objectif. */
	private int[] fonctionObjectif;
	/** L'objectif � atteindre, � savoir minimiser ou
	 *  maximiser la valeur de la fonction objectif. */
	private TypeObjectif typeObjectif;
	/** La matrice des contraintes. La derni�re valeur
	 *  correspond � la partie droite de la contrainte. */
	private int[][] contraintes;
	/** Le type des contraintes (<=, =, >=). */
	private TypeContrainte[] typesContraintes;
	
	/**
	 * Retourne le nombre de variables du programme lin�aire.
	 * 
	 * @return le nombre de variables du PLNE.
	 */
	public int getNbVariables()
	{
		return nbVariables;
	}
	
	/**
	 * Retourne la liste des noms des variables du programme
	 * lin�aire.
	 * 
	 * @return la liste des noms des variables du PLNE.
	 */
	public String[] getNomsVariables()
	{
		return nomsVariables;
	}
	
	/**
	 * Retourne le type des variables du programme lin�aire.
	 * 
	 * @return le type des variables du PLNE.
	 */
	public TypeVar[] getTypesVariables()
	{
		return typesVariables;
	}
	
	/**
	 * Retourne le vecteur de coefficients correspondant �
	 * la fonction objectif.
	 * 
	 * @return le vecteur des coefficients de la fonction objectif.
	 */
	public int[] getFonctionObjectif()
	{
		return fonctionObjectif;
	}
	
	/**
	 * Retourne le type d'objectif � savoir minimiser
	 * ou maximiser la fonction objectif.
	 * 
	 * @return le type d'objectif.
	 */
	public TypeObjectif getTypeObjectif()
	{
		return typeObjectif;
	}
	
	/**
	 * Retourne la matrice des coefficients des contraintes.
	 * Le dernier coefficient correspond � la partie droite
	 * de l'expression.
	 * 
	 * @return la matrice des coefficients des contraintes.
	 */
	public int[][] getContraintes()
	{
		return contraintes;
	}
	
	/**
	 * Retourne le type des contraintes.
	 * 
	 * @return le type des contraintes.
	 */
	public TypeContrainte[] getTypesContraintes()
	{
		return typesContraintes;
	}
}
