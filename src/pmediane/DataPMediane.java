package pmediane;

import java.util.HashMap;

/**
 * Classe représentant les données correspondant à
 * une instance du problème de la p-médiane.
 * Il s'agit principalement du nombre de centres à
 * ouvrir et du graphe représentant les liens existant
 * entre les différentes entités.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class DataPMediane
{
	/** Le nombre d'entités dans le graphe. */
	private final int nbEntites;
	/** Le nombre de centres à ouvrir. */
	private final int nbCentres;
	/**	Le tableau des distances.
	 *  Pour chaque entité de départ, on dispose
	 *  d'une table de hachage permettant de donner
	 *  retrouver la distance à l'entité d'arrivée. */
	private HashMap<Integer, Integer>[] distances;
	
	/**
	 * Crée une instance du problème de la p-médiane
	 * en chargeant les données à partir du fichier
	 * dont le nom est fourni.
	 * 
	 * @param nomFichier le nom du fichier à charger.
	 */
	public DataPMediane(String nomFichier)
	{
		
	}
	
	/**
	 * Retourne le nombre d'entités composant le graphe.
	 * 
	 * @return le nombre d'entités composant le graphe.
	 */
	public int getNbEntites()
	{
		return nbEntites;
	}
	
	/**
	 * Retourne le nombre de centres à ouvrir.
	 * 
	 * @return le nombre de centres à ouvrir.
	 */
	public int getNbCentres()
	{
		return nbCentres;
	}
	
	/**
	 * Retourne le nombre de liens dans le graphe.
	 * 
	 * @return le nombre de liens dans le graphe.
	 */
	public int getNbLiens()
	{
		return 0;
	}
	
	/**
	 * Teste s'il existe un lien entre les deux
	 * entités dont le numéro est fourni.
	 * 
	 * @param i le numéro de l'entité de départ.
	 * @param j le numéro de l'entité d'arrivée.
	 * @return vrai si et seulement si il existe
	 * 		   un lien reliant les deux entités.
	 */
	public boolean sontReliees(int i, int j)
	{
		return false;
	}
	
	/**
	 * Retourne la distance entre les deux
	 * entités dont le numéro est fourni.
	 * La distance retournée est égale à 
	 * Integer.MAX_VALUE si les deux entités
	 * ne sont pas reliées directement.
	 * 
	 * @param i le numéro de l'entité de départ.
	 * @param j le numéro de l'entité d'arrivée.
	 * @return la distance du lien entre les deux
	 * 		   entités ou Integer.MAX_VALUE s'il 
	 * 		   n'existe pas un tel lien.
	 */
	public int getDistance(int i, int j)
	{
		return Integer.MAX_VALUE;
	}
}
