package pmediane;

import java.util.HashMap;

/**
 * Classe repr�sentant les donn�es correspondant �
 * une instance du probl�me de la p-m�diane.
 * Il s'agit principalement du nombre de centres �
 * ouvrir et du graphe repr�sentant les liens existant
 * entre les diff�rentes entit�s.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class DataPMediane
{
	/** Le nombre d'entit�s dans le graphe. */
	private final int nbEntites;
	/** Le nombre de centres � ouvrir. */
	private final int nbCentres;
	/**	Le tableau des distances*/
	private HashMap<Integer, Integer>[] distances;
	
	/**
	 * Cr�e une instance du probl�me de la p-m�diane
	 * en chargeant les donn�es � partir du fichier
	 * dont le nom est fourni.
	 * 
	 * @param nomFichier le nom du fichier � charger.
	 */
	public DataPMediane(String nomFichier)
	{
		
	}
	
	/**
	 * Retourne le nombre d'entit�s composant le graphe.
	 * 
	 * @return le nombre d'entit�s composant le graphe.
	 */
	public int getNbEntites()
	{
		return nbEntites;
	}
	
	/**
	 * Retourne le nombre de centres � ouvrir.
	 * 
	 * @return le nombre de centres � ouvrir.
	 */
	public int getNbCentres()
	{
		return nbCentres;
	}
	
	/**
	 * Teste s'il existe un lien entre les deux
	 * entit�s dont le num�ro est fourni.
	 * 
	 * @param i le num�ro de l'entit� de d�part.
	 * @param j le num�ro de l'entit� d'arriv�e.
	 * @return vrai si et seulement si il existe
	 * 		   un lien reliant les deux entit�s.
	 */
	public boolean sontReliees(int i, int j)
	{
		return false;
	}
	
	/**
	 * Retourne la distance entre les deux
	 * entit�s dont le num�ro est fourni.
	 * La distance retourn�e est �gale � 
	 * Integer.MAX_VALUE si les deux entit�s
	 * ne sont pas reli�es directement.
	 * 
	 * @param i le num�ro de l'entit� de d�part.
	 * @param j le num�ro de l'entit� d'arriv�e.
	 * @return la distance du lien entre les deux
	 * 		   entit�s ou Integer.MAX_VALUE s'il 
	 * 		   n'existe pas un tel lien.
	 */
	public int getDistance(int i, int j)
	{
		return Integer.MAX_VALUE;
	}
}
