package pmediane;

import java.util.HashMap;

import pne.PLNE;

/**
 * Classe sp�cialisant l'interface PLNE et repr�sentant le
 * programme lin�aire en nombres entiers correspondant � une
 * instance donn�e du probl�me de la p-m�diane.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class PLNEPMediane extends PLNE
{	
	/** L'instance du probl�me correspondant � ce PNLE. */
	private DataPMediane donnees;
	
	/**
	 * Construit le programme lin�aire en nombres entiers
	 * correspondant � l'instance fournie du probl�me de
	 * la p-m�diane.
	 * 
	 * @param donnees l'instance du probl�me de la p-m�diane.
	 */
	@SuppressWarnings("unchecked")
	public PLNEPMediane(DataPMediane donnees)
	{
		// On alloue la m�moire n�cessaire.
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
		
		// Contrainte sur le nombre de centres � ouvrir
		contraintes[donnees.getNbEntites()] = new HashMap<Integer, Integer>(donnees.getNbEntites()+1, 1);
		for (int j=0; j<donnees.getNbEntites(); j++)
			contraintes[donnees.getNbEntites()].put(j*donnees.getNbEntites() + j, 1);
		secondsMembresContraintes[donnees.getNbEntites()] = donnees.getNbCentres();
		typesContraintes[donnees.getNbEntites()] = TypeContrainte.EGAL;
		
		// Contraintes sur l'affectation : une entit�
		// ne peut �tre rattach�e qu'� un centre.
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
	 * Retourne le jeu de donn�es ayant permis de construire
	 * ce programme lin�aire en nombres entiers.
	 * 
	 * @return le jeu de donn�es ayant permis de construire le PLNE.
	 */
	public DataPMediane getDonnees()
	{
		return donnees;
	}
}
