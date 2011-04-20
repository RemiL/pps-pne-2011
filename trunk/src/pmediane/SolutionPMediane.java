package pmediane;

/**
 * Classe repr�sentant une solution � un probl�me de la
 * p-m�diane.
 * Une solution est principalement caract�ris�e par la
 * liste des centres choisis parmi les entit�s et par
 * la liste des affectations des entit�s � chaque centre.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class SolutionPMediane
{
	/** Le jeu de donn�es correspondant � cette solution. */
	private DataPMediane donnees;
	/** Le tableau des centres choisis. */
	private int[] centres;
	/** Le tableau des affectations des entit�s aux centres. */
	private int[] affectations;
	/** Le tableau des deuxi�mes meilleurs centres pour chaque entit�. */
	private int[] affectationsSecondaires;
	
	/**
	 * Cr�e une instance de solution � un probl�me
	 * de la p-m�diane repr�sent� par les donn�es
	 * fournies et les caract�ristiques fournies.
	 * 
	 * @param donnees le jeu de donn�es sur lequel
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
	 * (affectations au deuxi�me meilleur centre possible).
	 * 
	 * @return une copie de la liste des affectations.
	 */
	public int[] getAffectationsSecondaires()
	{
		return affectationsSecondaires.clone();
	}
	
	/**
	 * Teste si l'entit� i est un centre.
	 * 
	 * @param i le num�ro de l'entit�
	 * @return vrai si l'entit� i est centre, faux sinon.
	 */
	public boolean estCentre(int i)
	{
		return (affectations[i] == i);
	}
	
	/**
	 * Retourne le num�ro du centre auquel l'entit� dont le
	 * num�ro est fourni est reli�e.
	 * 
	 * @param i le num�ro de l'entit�.
	 * @return le num�ro du centre auquel l'entit� est reli�.
	 */
	public int getCentre(int i)
	{
		return affectations[i];
	}
	
	/**
	 * Retourne la distance de l'entit� dont le num�ro est 
	 * fourni � son centre.
	 * 
	 * @param i le num�ro de l'entit�.
	 * @return la distance de l'entit� � son centre.
	 */
	public int getDistanceCentre(int i)
	{
		return donnees.getDistance(i, affectations[i]);
	}
	
	/** 
	 * Retourne le jeu de donn�es correspondant
	 * � la solution.
	 * 
	 * @return le jeu de donn�es correspondant
	 * 		   � la solution.
	 */
	public DataPMediane getDonnees()
	{
		return donnees;
	}
}
