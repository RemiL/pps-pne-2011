package pne;

import java.util.HashMap;

/**
 * Classe générique permettant de manipuler des programmes
 * linéaires en nombres entiers caractérisé par ses variables,
 * sa fonction objectif et ses contraintes.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
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
	
	/** Le nombre de variables utilisées dans le PLNE. */
	protected int nbVariables;
	/** Le nom des variables utilisées. */
	protected String[] nomsVariables;
	/** Le nom des variables utilisées. */
	protected TypeVar[] typesVariables;
	/** Les coefficients de la fonction objectif. */
	protected int[] fonctionObjectif;
	/** L'objectif à atteindre, à savoir minimiser ou
	 *  maximiser la valeur de la fonction objectif. */
	protected TypeObjectif typeObjectif;
	/** La matrice des contraintes. */
	protected HashMap<Integer, Integer>[] contraintes;
	/** Les seconds membres des contraintes */
	protected int[] secondsMembresContraintes;
	/** Le type des contraintes (<=, =, >=). */
	protected TypeContrainte[] typesContraintes;
	
	/**
	 * Retourne le nombre de variables du programme linéaire.
	 * 
	 * @return le nombre de variables du PLNE.
	 */
	public int getNbVariables()
	{
		return nbVariables;
	}
	
	/**
	 * Retourne la liste des noms des variables du programme
	 * linéaire.
	 * 
	 * @return la liste des noms des variables du PLNE.
	 */
	public String[] getNomsVariables()
	{
		return nomsVariables;
	}
	
	/**
	 * Retourne le type des variables du programme linéaire.
	 * 
	 * @return le type des variables du PLNE.
	 */
	public TypeVar[] getTypesVariables()
	{
		return typesVariables;
	}
	
	/**
	 * Retourne le vecteur de coefficients correspondant à
	 * la fonction objectif.
	 * 
	 * @return le vecteur des coefficients de la fonction objectif.
	 */
	public int[] getFonctionObjectif()
	{
		return fonctionObjectif;
	}
	
	/**
	 * Retourne le type d'objectif à savoir minimiser
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
	 * 
	 * @return la matrice des coefficients des contraintes.
	 */
	public HashMap<Integer, Integer>[] getContraintes()
	{
		return contraintes;
	}
	
	/**
	 * Retourne les seconds membres des contraintes.
	 * 
	 * @return la seconds membres des contraintes.
	 */
	public int[] getSecondsMembresContraintes()
	{
		return secondsMembresContraintes;
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
