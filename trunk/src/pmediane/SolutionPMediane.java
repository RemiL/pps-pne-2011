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
	/** Le tableau des distances aux centres. */
	private int[] distancesCentres;
	/** Le tableau des affectations des entit�s aux centres. */
	private int[] centresEntite;
	
	/**
	 * Cr�e une instance de solution � un probl�me
	 * de la p-m�diane repr�sent� par les donn�es
	 * fournies.
	 * 
	 * @param donnees le jeu de donn�es sur lequel
	 * 				  reposera la solution.
	 */
	public SolutionPMediane(DataPMediane donnees)
	{
		
	}
	
	/**
	 * Teste si l'entit� i est un centre.
	 * 
	 * @param i le num�ro de l'entit�
	 * @return vrai si l'entit� i est centre, faux sinon.
	 */
	public boolean estCentre(int i)
	{
		
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
