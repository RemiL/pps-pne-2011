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
	private static Random rand = new Random();
	
	private DataPMediane donnees;
	private int i, j, c, a;
	private int ancienCentre, nouveauCentre;
	private int[] centres;
	private int[] affectations;
	private int[] affectationsSecondaires;
	private SolutionPMediane sol;
	private int[][] dispos;
	private int[] nbDispos;
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
		donnees = s.getDonnees();
		centres = s.getCentres();
		affectations = s.getAffectations();
		affectationsSecondaires = s.getAffectationsSecondaires();
		
		c = rand.nextInt(centres.length);
		ancienCentre = centres[c];
		do
			nouveauCentre = rand.nextInt(affectations.length);
		while (affectations[nouveauCentre] == nouveauCentre);
		
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
				{
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
		donnees = s.getDonnees();
		centres = s.getCentres();
		affectations = s.getAffectations();
		affectationsSecondaires = s.getAffectationsSecondaires();
		
		for (int k2=0; k2<k; k2++)
		{
			c = rand.nextInt(centres.length - k2);
			ancienCentre = centres[c];
			do
				nouveauCentre = rand.nextInt(affectations.length);
			while (affectations[nouveauCentre] == nouveauCentre);
			
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
					{
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
			centres[centres.length - k2 - 1] = nouveauCentre;
		}
		
		return new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
	}

	/**
	 * Trouve la meilleure solution locale disponible dans le
	 * voisinage de la solution initiale fournie en effectuant
	 * une recherche exhaustive.
	 * 
	 * @param s la solution initiale.
	 * @return la meilleure solution située dans le
	 * 		   voisinage de la solution de base.
	 */
	public SolutionPMediane rechercherMeilleureSolution(SolutionPMediane s, FonctionObjectif<SolutionPMediane> f)
	{
		/*SolutionPMediane sol;
		int valObj, meilleureValObj = f.calculer(s);
		
		for (int i=0; i<s.getDonnees().getNbEntites(); i++)
		{
			sol = genererSolution(s);
			valObj = f.calculer(sol);
			
			if (f.estAmelioration(valObj, meilleureValObj))
			{
				s = sol;
				meilleureValObj = valObj;
			}
		}
		
		return s;*/

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
		meilleureValObj = f.calculer(s);
		
		for (int n=0; n<donnees.getNbCentres(); n++)
		{
			centres = s.getCentres();
			affectations = s.getAffectations();
			affectationsSecondaires = s.getAffectationsSecondaires();
			
			c = rand.nextInt(centres.length);
			while (nbDispos[c] <= 0)
				c = (c+1) % centres.length;
			ancienCentre = centres[c];
			
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
					{
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
	
	/**
	 * Trouve la meilleure solution locale disponible dans le
	 * voisinage de taille k de la solution initiale fournie en
	 * effectuant une recherche exhaustive.
	 * 
	 * @param s la solution initiale.
	 * @param k la taille du voisinage à considérer.
	 * @return la meilleure solution située dans le voisinage
	 * 		   de taille k de la solution de base.
	 */
	public SolutionPMediane rechercherMeilleureSolution(SolutionPMediane s,	int k, FonctionObjectif<SolutionPMediane> f)
	{
		return rechercherMeilleureSolution(s, f);
	}
}
