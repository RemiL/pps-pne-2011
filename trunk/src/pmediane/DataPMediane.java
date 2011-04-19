package pmediane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
	/** Le tableau des distances. */
	private int[][] distances;
	
	/**
	 * Cr�e une instance du probl�me de la p-m�diane
	 * en chargeant les donn�es � partir du fichier
	 * dont le nom est fourni.
	 * Le fichier doit �tre de la forme suivante :
	 * La premi�re ligne comporte trois entiers :
	 * - le nombre d'entit�s
	 * - le nombre de liens
	 * - le nombre de centres � ouvrir.
	 * Les lignes suivantes comportent �galement trois
	 * entiers dont la signification est la suivante :
	 * - num�ro de l'entit� de d�part
	 * - num�ro de l'entit� d'arriv�e
	 * - distance entre les deux entit�s.
	 * 
	 * @param nomFichier le nom du fichier � charger.
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
		scanner.nextInt(); // nombre de liens, non utilis�
		nbCentres = scanner.nextInt();
		
		distances = new int[nbEntites][nbEntites];
		
		// Initialisation de toutes les distances � l'infini
		// sauf les distances d'une entit� � elle-m�me.
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
		
		// On compl�te la matrice en calculant les plus courts
		// chemins entre tous les couples d'entit�s.
		calculerPlusCourtsChemins();
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
	 * entit�s dont le num�ro est fourni.
	 * 
	 * @param i le num�ro de l'entit� de d�part.
	 * @param j le num�ro de l'entit� d'arriv�e.
	 * @return vrai si et seulement si il existe
	 * 		   un lien reliant les deux entit�s.
	 */
	public boolean sontReliees(int i, int j)
	{
		return (distances[i][j] != Integer.MAX_VALUE);
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
		return distances[i][j];
	}
	
	/**
	 * Compl�te la matrice des distances en calculant
	 * les plus courts chemins entre toutes les entit�s
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
