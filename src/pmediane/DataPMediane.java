package pmediane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
	/** Le tableau des distances. */
	private int[][] distances;
	
	/**
	 * Crée une instance du problème de la p-médiane
	 * en chargeant les données à partir du fichier
	 * dont le nom est fourni.
	 * Le fichier doit être de la forme suivante :
	 * La première ligne comporte trois entiers :
	 * - le nombre d'entités
	 * - le nombre de liens
	 * - le nombre de centres à ouvrir.
	 * Les lignes suivantes comportent également trois
	 * entiers dont la signification est la suivante :
	 * - numéro de l'entité de départ
	 * - numéro de l'entité d'arrivée
	 * - distance entre les deux entités.
	 * 
	 * @param nomFichier le nom du fichier à charger.
	 */
	public DataPMediane(String nomFichier)
	{
		int i, j;
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(nomFichier));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		nbEntites = scanner.nextInt();
		scanner.nextInt(); // nombre de liens, non utilisé
		nbCentres = scanner.nextInt();
		
		distances = new int[nbEntites][nbEntites];
		
		// Initialisation de toutes les distances à l'infini
		// sauf les distances d'une entité à elle-même.
		for (i=0; i<nbEntites; i++)
		{
			for (j=0; j<nbEntites; j++)
				distances[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
		}
		
		// Chargement des liens
		while (scanner.hasNextInt())
		{
			i = scanner.nextInt() - 1;
			j = scanner.nextInt() - 1;
			distances[i][j] = distances[j][i] = scanner.nextInt();
		}
		
		// On complète la matrice en calculant les plus courts
		// chemins entre tous les couples d'entités.
		calculerPlusCourtsChemins();
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
		return (distances[i][j] != Integer.MAX_VALUE);
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
		return distances[i][j];
	}
	
	/**
	 * Complète la matrice des distances en calculant
	 * les plus courts chemins entre toutes les entités
	 * en utilisant l'algorithme de Floyd.
	 */
	private void calculerPlusCourtsChemins()
	{
		for (int k=0; k<nbEntites; k++)
		{
			for (int i=0; i<nbEntites; i++)
			{
				if (i != k && distances[i][k] != Integer.MAX_VALUE)
				{
					for (int j=0; j<nbEntites; j++)
					{
						if (distances[k][j] != Integer.MAX_VALUE && (distances[i][j] == Integer.MAX_VALUE || distances[i][k] + distances[k][j] < distances[i][j]))
							distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
		}
	}
}
