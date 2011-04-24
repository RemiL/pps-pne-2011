package pmediane;

import java.util.Random;

import pne.FonctionObjectif;
import pne.Voisinage;
import pne.VoisinageTailleVariable;

/**
 * Classe implémentant un voisinage pour le problème de la
 * p-médiane. Le voisinage choisi est basé sur une inversion
 * d'un centre avec une entité non centre : un centre ouvert
 * est fermé et une entité est ouverte à la place. Les affectations
 * des entités aux centres sont recalculées pour rattacher
 * toute entité à son centre le plus proche. Le voisinage
 * est à taille variable puisque le nombre de permutations à
 * effectuer peut être réglé.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class VoisinagePMediane implements Voisinage<SolutionPMediane>, VoisinageTailleVariable<SolutionPMediane>
{
	/** Générateur de nombres aléatoires. */
	private static Random rand = new Random();
	/** Les données correspondant à la solution
	 *  courante. */
	private DataPMediane donnees;
	/** Indices de boucles */
	private int i, j, c, a;
	/** Ancien et nouveau centre pour les échanges. */
	private int ancienCentre, nouveauCentre;
	/** Tableaux représentant la solution en cours de génération. */
	private int[] centres, affectations, affectationsSecondaires;
	/** Une solution temporaire. */
	private SolutionPMediane sol;
	/** Tableau permettant d'éviter de réessayer
	 *  les même solutions. */
	private int[] nbDispos;
	/** Tableau permettant d'éviter de réessayer
	 *  les même solutions. */
	private int[][] dispos;
	/** Valeurs de la fonction objectif. */
	int valObj, meilleureValObj;
	
	/**
	 * Génère une solution au problème de la p-médiane appartenant
	 * au voisinage (basé sur une permutation) de la solution fournie.
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
		
		// On dire un centre à fermer au hasard
		c = rand.nextInt(centres.length);
		ancienCentre = centres[c];
		// On ouvre un nouveau centre (ne doit pas être un
		// centre déjà ouvert).
		do
			nouveauCentre = rand.nextInt(affectations.length);
		while (affectations[nouveauCentre] == nouveauCentre);
		
		// On réaffecte les entités.
		for (i=0; i<affectations.length; i++)
		{
			// Le centre de l'entité a été fermé
			if (affectations[i] == ancienCentre)
			{
				// Si le nouveau centre est meilleur que le
				// deuxième meilleur centre.
				if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
					affectations[i] = nouveauCentre;
				else // Sinon on doit retrouver le nouveau deuxième meilleur centre
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
			else // Le centre de l'entité i n'a pas été fermé
			{
				// Si le nouveau centre est meilleur que l'ancien
				if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectations[i]))
				{
					affectationsSecondaires[i] = affectations[i];
					affectations[i] = nouveauCentre;
				}
				// Sinon le nouveau centre peut être meilleur que
				// l'ancien deuxième meilleur centre.
				else if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
					affectationsSecondaires[i] = nouveauCentre;
				// Si le deuxième meilleur centre était celui qui a
				// été fermé, il faut le remplacer.
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
	 * Génère une solution appartenant au voisinage de taille
	 * indiquée (basé sur k permutations) de la solution fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage à considérer.
	 * @return une solution située dans le voisinage de taille
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
			// On tire un centre pour le fermer en se débrouillant
			// pour ne pas fermer un centre qui vient d'être ouvert.
			c = rand.nextInt(centres.length - k2);
			ancienCentre = centres[c];
			// On le remplace par une entité non actuellement centre.
			do
				nouveauCentre = rand.nextInt(affectations.length);
			while (affectations[nouveauCentre] == nouveauCentre);
			
			// On réaffecte les entités.
			for (i=0; i<affectations.length; i++)
			{
				// Le centre de l'entité a été fermé
				if (affectations[i] == ancienCentre)
				{
					// Si le nouveau centre est meilleur que l'ancien
					// deuxième meilleur centre.
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectations[i] = nouveauCentre;
					else // Sinon on doit retrouver le nouveau deuxième meilleur centre
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
				else // Le centre de l'entité i n'a pas été fermé
				{
					// Si le nouveau centre est meilleur que l'ancien
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectations[i]))
					{
						affectationsSecondaires[i] = affectations[i];
						affectations[i] = nouveauCentre;
					}
					// Sinon le nouveau centre peut être meilleur que
					// l'ancien deuxième meilleur centre.
					else if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectationsSecondaires[i] = nouveauCentre;
					// Si le deuxième meilleur centre était celui qui a
					// été fermé, il faut le remplacer.
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
	 * Retourne la meilleure solution trouvée après une
	 * recherche locale. La recherche est effectuée en
	 * faisant une descente avec au maximum le nombre
	 * d'essais fournis. Elle utilise le voisinage de 
	 * taille 1.
	 * La solution retournée est la meilleure par rapport
	 * à la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param essais la nombre d'essai à effectuer.
	 * @param f la fonction objectif.
	 * @return la meilleure solution locale trouvée
	 * 		   après la descente.
	 */
	public SolutionPMediane rechercherSolutionLocale(SolutionPMediane s, int essais, FonctionObjectif<SolutionPMediane> f)
	{
		// Tableaux permettant d'éviter de réessayer les même solutions
		// plusieurs fois en gardant une trace des essais précédents.
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
		// est celle de départ.
		meilleureValObj = f.calculer(s);
		
		// On fait le nombre d'essais demandés.
		for (int n=0; n<essais; n++)
		{
			// On repart de la solution actuellement meilleure
			centres = s.getCentres();
			affectations = s.getAffectations();
			affectationsSecondaires = s.getAffectationsSecondaires();
			
			// On ferme un centre pour lequel on n'a pas déja
			// testé toutes les possibilités de centres entrants.
			c = rand.nextInt(centres.length);
			while (nbDispos[c] <= 0)
				c = (c+1) % centres.length;
			ancienCentre = centres[c];
			
			// On trouve un centre entrant non encore déja essayé.
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
			
			// On réaffecte les entités
			for (i=0; i<affectations.length; i++)
			{
				// Le centre de l'entité a été fermé
				if (affectations[i] == ancienCentre)
				{
					// Si le nouveau centre est meilleur que le
					// deuxième meilleur centre.
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectations[i] = nouveauCentre;
					else // Sinon on doit retrouver le nouveau deuxième meilleur centre
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
				else // Le centre de l'entité i n'a pas été fermé
				{
					// Si le nouveau centre est meilleur que l'ancien
					if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectations[i]))
					{
						affectationsSecondaires[i] = affectations[i];
						affectations[i] = nouveauCentre;
					}
					// Sinon le nouveau centre peut être meilleur que
					// l'ancien deuxième meilleur centre.
					else if (donnees.getDistance(i, nouveauCentre) < donnees.getDistance(i, affectationsSecondaires[i]))
						affectationsSecondaires[i] = nouveauCentre;
					// Si le deuxième meilleur centre était celui qui a
					// été fermé, il faut le remplacer.
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
			
			// Si la solution générée est meilleure que l'ancienne,
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
