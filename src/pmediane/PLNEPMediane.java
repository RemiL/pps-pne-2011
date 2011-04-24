package pmediane;

import java.util.HashMap;

import pne.PLNE;

/**
 * Classe spécialisant l'interface PLNE et représentant le
 * programme linéaire en nombres entiers correspondant à une
 * instance donnée du problème de la p-médiane.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class PLNEPMediane extends PLNE
{	
	/** L'instance du problème correspondant à ce PNLE. */
	private DataPMediane donnees;
	
	/**
	 * Construit le programme linéaire en nombres entiers
	 * correspondant à l'instance fournie du problème de
	 * la p-médiane.
	 * 
	 * @param donnees l'instance du problème de la p-médiane.
	 */
	@SuppressWarnings("unchecked")
	public PLNEPMediane(DataPMediane donnees)
	{
		// On alloue la mémoire nécessaire.
		this.donnees = donnees;
		nbVariables = donnees.getNbEntites()*donnees.getNbEntites();
		nomsVariables = new String[nbVariables];
		typesVariables = new TypeVar[nbVariables];
		fonctionObjectif = new int[nbVariables];
		typeObjectif = TypeObjectif.MIN;
		contraintes = new HashMap[nbVariables + donnees.getNbEntites() + 1];
		secondsMembresContraintes = new int[nbVariables + donnees.getNbEntites() + 1];
		typesContraintes = new TypeContrainte[nbVariables + donnees.getNbEntites() + 1];
		
		// Variables et fonction objectif
		for (int i=0; i<donnees.getNbEntites(); i++)
		{
			for (int j=0; j<donnees.getNbEntites(); j++)
			{
				nomsVariables[i*donnees.getNbEntites() + j] = "x"+i+j;
				typesVariables[i*donnees.getNbEntites() + j] = TypeVar.BOOLEENNE;
				fonctionObjectif[i*donnees.getNbEntites() + j] = donnees.getDistance(i, j);
			}
		}
		
		// Contraintes d'affectation
		for (int i=0; i<donnees.getNbEntites(); i++)
		{
			contraintes[i] = new HashMap<Integer, Integer>(donnees.getNbEntites()+1, 1);
			
			for (int j=0; j<donnees.getNbEntites(); j++)
			{
				contraintes[i].put(i*donnees.getNbEntites() + j, 1);
			}
			
			secondsMembresContraintes[i] = 1;
			typesContraintes[i] = TypeContrainte.EGAL;
		}
		
		// Contrainte sur le nombre de centres à ouvrir
		contraintes[donnees.getNbEntites()] = new HashMap<Integer, Integer>(donnees.getNbEntites()+1, 1);
		for (int j=0; j<donnees.getNbEntites(); j++)
			contraintes[donnees.getNbEntites()].put(j*donnees.getNbEntites() + j, 1);
		secondsMembresContraintes[donnees.getNbEntites()] = donnees.getNbCentres();
		typesContraintes[donnees.getNbEntites()] = TypeContrainte.EGAL;
		
		// Contraintes sur l'affectation : une entité
		// ne peut être rattachée qu'à un centre.
		for (int i=0; i<donnees.getNbEntites(); i++)
		{
			for (int j=0; j<donnees.getNbEntites(); j++)
			{
				contraintes[(i+1)*donnees.getNbEntites() + j+1] = new HashMap<Integer, Integer>(3, 1);
				contraintes[(i+1)*donnees.getNbEntites() + j+1].put(i*donnees.getNbEntites() + j, 1);
				contraintes[(i+1)*donnees.getNbEntites() + j+1].put(j*donnees.getNbEntites() + j, -1);
				secondsMembresContraintes[(i+1)*donnees.getNbEntites() + j+1] = 0;
				typesContraintes[(i+1)*donnees.getNbEntites() + j+1] = TypeContrainte.INF_EGAL;
			}
		}
	}
	
	/**
	 * Retourne le jeu de données ayant permis de construire
	 * ce programme linéaire en nombres entiers.
	 * 
	 * @return le jeu de données ayant permis de construire le PLNE.
	 */
	public DataPMediane getDonnees()
	{
		return donnees;
	}
}
