package pmediane;

/**
 * Classe représentant une solution à un problème de la
 * p-médiane.
 * Une solution est principalement caractérisée par la
 * liste des centres choisis parmi les entités et par
 * la liste des affectations des entités à chaque centre.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class SolutionPMediane
{
	/** Le jeu de données correspondant à cette solution. */
	private DataPMediane donnees;
	/** Le tableau des centres choisis. */
	private int[] centres;
	/** Le tableau des affectations des entités aux centres. */
	private int[] affectations;
	/** Le tableau des deuxièmes meilleurs centres pour chaque entité. */
	private int[] affectationsSecondaires;
	
	/**
	 * Crée une instance de solution à un problème
	 * de la p-médiane représenté par les données
	 * fournies et les caractéristiques fournies.
	 * 
	 * @param donnees le jeu de données sur lequel
	 * 				  reposera la solution.
	 */
	public SolutionPMediane(DataPMediane donnees, int[] centres,
			 				int[] affectations, int[] affectationsSecondaires)
	{
		this.donnees = donnees;
		this.centres = centres.clone();
		this.affectations = affectations.clone();
		this.affectationsSecondaires = affectationsSecondaires.clone();
	}
	
	/**
	 * Retourne une copie de la liste des centres ouverts.
	 * 
	 * @return une copie de la liste des centres ouverts.
	 */
	public int[] getCentres()
	{
		return centres.clone();
	}
	
	/**
	 * Retourne une copie de la liste des affectations.
	 * 
	 * @return une copie de la liste des affectations.
	 */
	public int[] getAffectations()
	{
		return affectations.clone();
	}
	
	/**
	 * Retourne une copie de la liste des affectations secondaires
	 * (affectations au deuxième meilleur centre possible).
	 * 
	 * @return une copie de la liste des affectations.
	 */
	public int[] getAffectationsSecondaires()
	{
		return affectationsSecondaires.clone();
	}
	
	/**
	 * Teste si l'entité i est un centre.
	 * 
	 * @param i le numéro de l'entité
	 * @return vrai si l'entité i est centre, faux sinon.
	 */
	public boolean estCentre(int i)
	{
		return (affectations[i] == i);
	}
	
	/**
	 * Retourne le numéro du centre auquel l'entité dont le
	 * numéro est fourni est reliée.
	 * 
	 * @param i le numéro de l'entité.
	 * @return le numéro du centre auquel l'entité est relié.
	 */
	public int getCentre(int i)
	{
		return affectations[i];
	}
	
	/**
	 * Retourne la distance de l'entité dont le numéro est 
	 * fourni à son centre.
	 * 
	 * @param i le numéro de l'entité.
	 * @return la distance de l'entité à son centre.
	 */
	public int getDistanceCentre(int i)
	{
		return donnees.getDistance(i, affectations[i]);
	}
	
	/** 
	 * Retourne le jeu de données correspondant
	 * à la solution.
	 * 
	 * @return le jeu de données correspondant
	 * 		   à la solution.
	 */
	public DataPMediane getDonnees()
	{
		return donnees;
	}
}
