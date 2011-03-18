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
	/** Le tableau des distances aux centres. */
	private int[] distancesCentres;
	/** Le tableau des affectations des entités aux centres. */
	private int[] centresEntite;
	
	/**
	 * Crée une instance de solution à un problème
	 * de la p-médiane représenté par les données
	 * fournies.
	 * 
	 * @param donnees le jeu de données sur lequel
	 * 				  reposera la solution.
	 */
	public SolutionPMediane(DataPMediane donnees)
	{
		
	}
	
	/**
	 * Teste si l'entité i est un centre.
	 * 
	 * @param i le numéro de l'entité
	 * @return vrai si l'entité i est centre, faux sinon.
	 */
	public boolean estCentre(int i)
	{
		
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
