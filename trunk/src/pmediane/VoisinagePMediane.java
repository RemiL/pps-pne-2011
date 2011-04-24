package pmediane;

import java.util.Random;

import pne.FonctionObjectif;
import pne.Voisinage;
import pne.VoisinageTailleVariable;

/**
 * Classe impl�mentant un voisinage pour le probl�me de la
 * p-m�diane. Le voisinage choisi est bas� sur une inversion
 * d'un centre avec une entit� non centre : un centre ouvert
 * est ferm� et une entit� est ouverte � la place. Les affectations
 * des entit�s aux centres sont recalcul�es pour rattacher
 * toute entit� � son centre le plus proche. Le voisinage
 * est � taille variable puisque le nombre de permutations �
 * effectuer peut �tre r�gl�.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class VoisinagePMediane implements Voisinage<SolutionPMediane>, VoisinageTailleVariable<SolutionPMediane>
{
	/** G�n�rateur de nombres al�atoires. */
	private static Random rand = new Random();
	/** Les donn�es correspondant � la solution
	 *  courante. */
	private DataPMediane donnees;
	/** Indices de boucles */
	private int i, j, c, a;
	/** Ancien et nouveau centre pour les �changes. */
	private int ancienCentre, nouveauCentre;
	/** Tableaux repr�sentant la solution en cours de g�n�ration. */
	private int[] centres, affectations, affectationsSecondaires;
	/** Une solution temporaire. */
	private SolutionPMediane sol;
	/** Tableau permettant d'�viter de r�essayer
	 *  les m�me solutions. */
	private int[] nbDispos;
	/** Tableau permettant d'�viter de r�essayer
	 *  les m�me solutions. */
	private int[][] dispos;
	/** Valeurs de la fonction objectif. */
	int valObj, meilleureValObj;
	
	/**
	 * G�n�re une solution au probl�me de la p-m�diane appartenant
	 * au voisinage (bas� sur une permutation) de la solution fournie.
	 * 
	 * @param s la solution de base
	 * @return une solution appartenant au voisinage de la solution
	 * 		   de base.
	 */
	public SolutionPMediane genererSolution(SolutionPMediane s)
	{
		// On part de la solution fournie.
		donnees = s.getDonnees();
		centres = s.getCentres();
		affectations = s.getAffectations();
		affectationsSecondaires = s.getAffectationsSecondaires();
		
		// On dire un centre � fermer au hasard
		c = rand.nextInt(centres.length);
		ancienCentre = centres[c];
		// On ouvre un nouveau centre (ne doit pas �tre un
		// centre d�j� ouvert).
		do
			nouveauCentre = rand.nextInt(affectations.length);
		while (affectations[nouveauCentre] == nouveauCentre);
		
		// On r�affecte les entit�s.
		for (i=0; i<affectations.length; i++)
		{
			// Le centre de l'entit� a �t� ferm�
			if (affectations[i] == ancienCentre)
			{
				// Si le nouveau centre est meilleur que le
				// deuxi�me meilleur centre.
				if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
					affectations[i] = nouveauCentre;
				else // Sinon on doit retrouver le nouveau deuxi�me meilleur centre
				{// car l'ancien devient le meilleur centre.
					affectations[i] = affectationsSecondaires[i];
					
					affectationsSecondaires[i] = nouveauCentre;
					for (j=0; j<centres.length; j++)
					{
						if (centres[j] != ancienCentre && centres[j] != affectations[i]
						    && donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[j]))
							affectationsSecondaires[i] = centres[j];
					}
				}
			}
			else // Le centre de l'entit� i n'a pas �t� ferm�
			{
				// Si le nouveau centre est meilleur que l'ancien
				if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectations[i]))
				{
					affectationsSecondaires[i] = affectations[i];
					affectations[i] = nouveauCentre;
				}
				// Sinon le nouveau centre peut �tre meilleur que
				// l'ancien deuxi�me meilleur centre.
				else if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
					affectationsSecondaires[i] = nouveauCentre;
				// Si le deuxi�me meilleur centre �tait celui qui a
				// �t� ferm�, il faut le remplacer.
				else if (affectationsSecondaires[i] == ancienCentre)
				{
					affectationsSecondaires[i] = nouveauCentre;
					for (j=0; j<centres.length; j++)
					{
						if (centres[j] != ancienCentre && centres[j] != affectations[i]
						    && donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[j]))
							affectationsSecondaires[i] = centres[j];
					}
				}
			}
		}
		
		centres[c] = nouveauCentre;
		
		return new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
	}

	/**
	 * G�n�re une solution appartenant au voisinage de taille
	 * indiqu�e (bas� sur k permutations) de la solution fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage � consid�rer.
	 * @return une solution situ�e dans le voisinage de taille
	 * 		   k de la solution de base.
	 */
	public SolutionPMediane genererSolution(SolutionPMediane s, int k)
	{
		// On part de la solution fournie.
		donnees = s.getDonnees();
		centres = s.getCentres();
		affectations = s.getAffectations();
		affectationsSecondaires = s.getAffectationsSecondaires();
		
		// On doit fermer k centres parmi les p ouverts actuellement.
		for (int k2=0; k2<k; k2++)
		{
			// On tire un centre pour le fermer en se d�brouillant
			// pour ne pas fermer un centre qui vient d'�tre ouvert.
			c = rand.nextInt(centres.length - k2);
			ancienCentre = centres[c];
			// On le remplace par une entit� non actuellement centre.
			do
				nouveauCentre = rand.nextInt(affectations.length);
			while (affectations[nouveauCentre] == nouveauCentre);
			
			// On r�affecte les entit�s.
			for (i=0; i<affectations.length; i++)
			{
				// Le centre de l'entit� a �t� ferm�
				if (affectations[i] == ancienCentre)
				{
					// Si le nouveau centre est meilleur que l'ancien
					// deuxi�me meilleur centre.
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectations[i] = nouveauCentre;
					else // Sinon on doit retrouver le nouveau deuxi�me meilleur centre
					{ // car l'ancien devient le nouveau meilleur centre.
						affectations[i] = affectationsSecondaires[i];
						
						affectationsSecondaires[i] = nouveauCentre;
						for (j=0; j<centres.length; j++)
						{
							if (centres[j] != ancienCentre && centres[j] != affectations[i]
							    && donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[j]))
								affectationsSecondaires[i] = centres[j];
						}
					}
				}
				else // Le centre de l'entit� i n'a pas �t� ferm�
				{
					// Si le nouveau centre est meilleur que l'ancien
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectations[i]))
					{
						affectationsSecondaires[i] = affectations[i];
						affectations[i] = nouveauCentre;
					}
					// Sinon le nouveau centre peut �tre meilleur que
					// l'ancien deuxi�me meilleur centre.
					else if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectationsSecondaires[i] = nouveauCentre;
					// Si le deuxi�me meilleur centre �tait celui qui a
					// �t� ferm�, il faut le remplacer.
					else if (affectationsSecondaires[i] == ancienCentre)
					{
						affectationsSecondaires[i] = nouveauCentre;
						for (j=0; j<centres.length; j++)
						{
							if (centres[j] != ancienCentre && centres[j] != affectations[i]
							    && donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[j]))
								affectationsSecondaires[i] = centres[j];
						}
					}
				}
			}
			
			centres[c] = centres[centres.length - k2 - 1];
			// Evite de refermer le centre tout juste ouvert.
			centres[centres.length - k2 - 1] = nouveauCentre;
		}
		
		return new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
	}

	/**
	 * Retourne la meilleure solution trouv�e apr�s une
	 * recherche locale. La recherche est effectu�e en
	 * faisant une descente avec au maximum le nombre
	 * d'essais fournis. Elle utilise le voisinage de 
	 * taille 1.
	 * La solution retourn�e est la meilleure par rapport
	 * � la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param essais la nombre d'essai � effectuer.
	 * @param f la fonction objectif.
	 * @return la meilleure solution locale trouv�e
	 * 		   apr�s la descente.
	 */
	public SolutionPMediane rechercherSolutionLocale(SolutionPMediane s, int essais, FonctionObjectif<SolutionPMediane> f)
	{
		// Tableaux permettant d'�viter de r�essayer les m�me solutions
		// plusieurs fois en gardant une trace des essais pr�c�dents.
		dispos = new int[donnees.getNbCentres()][donnees.getNbEntites()];
		nbDispos = new int[donnees.getNbCentres()];
		for (i=0; i<nbDispos.length; i++)
		{
			nbDispos[i] = dispos[i].length;
			
			for (j=0; j<dispos[i].length; j++)
			{
				dispos[i][j] = j;
			}
		}
		
		donnees = s.getDonnees();
		// Pour l'instant la meilleure solution
		// est celle de d�part.
		meilleureValObj = f.calculer(s);
		
		// On fait le nombre d'essais demand�s.
		for (int n=0; n<essais; n++)
		{
			// On repart de la solution actuellement meilleure
			centres = s.getCentres();
			affectations = s.getAffectations();
			affectationsSecondaires = s.getAffectationsSecondaires();
			
			// On ferme un centre pour lequel on n'a pas d�ja
			// test� toutes les possibilit�s de centres entrants.
			c = rand.nextInt(centres.length);
			while (nbDispos[c] <= 0)
				c = (c+1) % centres.length;
			ancienCentre = centres[c];
			
			// On trouve un centre entrant non encore d�ja essay�.
			do
			{
				a = rand.nextInt(nbDispos[c]);
				nouveauCentre = dispos[c][a];
				if (affectations[nouveauCentre] == nouveauCentre)
				{
					dispos[c][a] = dispos[c][nbDispos[c]-1];
					nbDispos[c]--;
				}
			} while (affectations[nouveauCentre] == nouveauCentre);
			dispos[c][a] = dispos[c][nbDispos[c]-1];
			nbDispos[c]--;
			
			// On r�affecte les entit�s
			for (i=0; i<affectations.length; i++)
			{
				// Le centre de l'entit� a �t� ferm�
				if (affectations[i] == ancienCentre)
				{
					// Si le nouveau centre est meilleur que le
					// deuxi�me meilleur centre.
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectations[i] = nouveauCentre;
					else // Sinon on doit retrouver le nouveau deuxi�me meilleur centre
					{ // puisque l'ancien devient le nouveau meilleure centre.
						affectations[i] = affectationsSecondaires[i];
						
						affectationsSecondaires[i] = nouveauCentre;
						for (j=0; j<centres.length; j++)
						{
							if (centres[j] != ancienCentre && centres[j] != affectations[i]
							    && donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[j]))
								affectationsSecondaires[i] = centres[j];
						}
					}
				}
				else // Le centre de l'entit� i n'a pas �t� ferm�
				{
					// Si le nouveau centre est meilleur que l'ancien
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectations[i]))
					{
						affectationsSecondaires[i] = affectations[i];
						affectations[i] = nouveauCentre;
					}
					// Sinon le nouveau centre peut �tre meilleur que
					// l'ancien deuxi�me meilleur centre.
					else if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectationsSecondaires[i] = nouveauCentre;
					// Si le deuxi�me meilleur centre �tait celui qui a
					// �t� ferm�, il faut le remplacer.
					else if (affectationsSecondaires[i] == ancienCentre)
					{
						affectationsSecondaires[i] = nouveauCentre;
						for (j=0; j<centres.length; j++)
						{
							if (centres[j] != ancienCentre && centres[j] != affectations[i]
							    && donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[j]))
								affectationsSecondaires[i] = centres[j];
						}
					}
				}
			}
			
			centres[c] = nouveauCentre;
			
			sol = new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
			valObj = f.calculer(sol);
			
			// Si la solution g�n�r�e est meilleure que l'ancienne,
			// on repart d'elle.
			if (f.estAmelioration(valObj, meilleureValObj))
			{
				s = sol;
				meilleureValObj = valObj;
				
				for (i=0; i<nbDispos.length; i++)
				{
					nbDispos[i] = dispos[i].length;
					
					for (j=0; j<dispos[i].length; j++)
					{
						dispos[i][j] = j;
					}
				}
			}
		}
		
		return s;
	}
}
