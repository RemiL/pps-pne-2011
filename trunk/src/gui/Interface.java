package gui;



import ilog.concert.IloException;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pmediane.CPLEXPMediane;
import pmediane.DataPMediane;
import pmediane.HeuristiqueGloutonnePMediane;
import pmediane.RSPMediane;
import pmediane.VNSPMediane;
import pmediane.VoisinagePMediane;


	public class Interface extends JFrame {


		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//Pour la barre du menu
		private JMenuBar menuBar = new JMenuBar();
		private JMenu menu1 = new JMenu("Fichier");
		private JMenu menu2 = new JMenu("Méthode de résolution");
		private JMenu menu3 = new JMenu("Aide");	
		private JMenuItem item1 = new JMenuItem("Charger une instance");
		private JMenuItem item2 = new JMenuItem("Lancer la résolution");
		private JMenuItem item3 = new JMenuItem("Quitter");
		private JRadioButton  item4 = new JRadioButton ("Recuit Simulé");
		private JRadioButton  item5 = new JRadioButton ("VNS");
		private JRadioButton  item6 = new JRadioButton ("CPLEX");
		private JMenuItem item7 = new JMenuItem("Configuration");
		private JMenuItem item8 = new JMenuItem("Manuel");
		private JMenuItem item9 = new JMenuItem("A propos");
		private Configuration config;
		private ButtonGroup bg = new ButtonGroup();
		private ArrayList<Integer> info;
		
		private JTable tableauAdjacences;
		private JTable tableauCentres;
		private JTable tableauAffectations;
		
		private DataPMediane donnees;
		private CPLEXPMediane sol;

		private VoisinagePMediane voisinage;
		private HeuristiqueGloutonnePMediane solution;
		
		private JPanel journal;
		
		//Pour les onglets
		private JTabbedPane onglet;
		
		public Interface(){
			
			voisinage = new VoisinagePMediane();
			solution = new HeuristiqueGloutonnePMediane();
			
			this.setTitle("Programmation en Nombre Entier");
			this.setSize(800, 600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			
			//Fichier
			this.menu1.add(item1);
			item1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					//Charger une instance
					JFileChooser instance = new JFileChooser();
					instance.showOpenDialog(null);
					try {
						donnees = new DataPMediane(instance.getSelectedFile().getAbsolutePath());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					
					//pour l'onglet 1
					DefaultTableModel tm = (DefaultTableModel) tableauAdjacences.getModel();
					tm.setColumnCount(0);
					
					Integer[] vals = new Integer[donnees.getNbEntites()];
					for (int i=0; i<donnees.getNbEntites(); i++)
						vals[i] = i+1;
					tm.addColumn("Entités", vals);
					
					for (Integer j=0; j<donnees.getNbEntites(); j++)
					{
						vals = new Integer[donnees.getNbEntites()];
						
						for (int i=0; i<donnees.getNbEntites(); i++)
							vals[i] = donnees.getDistance(i, j);
						
						tm.addColumn(j+1, vals);
					}
				}				
			});
			this.menu1.add(item2);
			
			//Lancer la résolution
			item2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					JTextField methode;
					ArrayList<JTextField> params;
					JTextField valObj;
					Timer timer;
					if (item4.isSelected()){
						RSPMediane sol = new RSPMediane(info.get(0), info.get(1),info.get(2), info.get(3), voisinage, solution);
						methode = new JTextField("Début du calcul : \n Méthode : Recuit Simulé");
						params = new ArrayList<JTextField>();
						
						params.add(new JTextField("Température initiale : " + info.get(0).toString()));					
						params.add(new JTextField("Température d'Arrêt : " + info.get(1).toString()));
						params.add(new JTextField("Nombre d'itération / paliers : " + info.get(2).toString()));
						params.add(new JTextField("Taux de décroissance : " + info.get(3).toString()));
						
						valObj = new JTextField(sol.getFonctionObjectif().toString());
					}
					else if (item5.isSelected()){
						VNSPMediane sol = new VNSPMediane(info.get(0), voisinage, solution);
						methode = new JTextField("Début du calcul : \n Méthode : VNS");
						params = new ArrayList<JTextField>();
						params.add(new JTextField("Température initiale : " + info.get(0).toString()));
						
						valObj = new JTextField();
						
					}
					else{
						try {
							sol = new CPLEXPMediane();
						} catch (IloException e) {
							e.printStackTrace();
						}
						methode = new JTextField("Début du calcul : \n Méthode : CPLEX");
						params = new ArrayList<JTextField>();
						params.add(new JTextField("Nombre maximum d'itérations sans amélioration : " + info.get(0).toString()));
						
						valObj = new JTextField();
					}
					
					
					journal.add(methode);
					for(int i = 0; i < params.size(); i ++){
						journal.add(params.get(i));
					}
					journal.add(valObj);
				
					journal.add(new JTextField());
				}				
			});
			this.menu1.addSeparator();
			this.menu1.add(item3);
			//Quitter
			item3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}				
			});
			
			item4.setSelected(true);
			
			//Recuit simulé
	        bg.add(item4);
	        //VNS
	        bg.add(item5);
	        //CPLEX
	        bg.add(item6);
	        
	        
	        //Initialisation de la configuration au Recuit simulé 
	        info = new ArrayList<Integer>();
	        info.add(0);
	        info.add(0);
	        info.add(0);
	        info.add(0);
	        

			config = new ConfigurationRS();
			item7.setEnabled(true);
	       
	        //Méthodes de résolution
	        
	        //Recuit Simulé
			this.menu2.add(item4);
			item4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					config = new ConfigurationRS();
					item7.setEnabled(true);
				}				
			});
			//VNS
			this.menu2.add(item5);
			item5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					config = new ConfigurationVNS();
					item7.setEnabled(true);
				}				
			});
			//CPLEX
			this.menu2.add(item6);
			item6.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					item7.setEnabled(false);
				}				
			});
			
			this.menu2.addSeparator();
			//Configuration
			this.menu2.add(item7);
			item7.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					info = config.configuration();
				}				
			});
			
			//Aide
			
			//Manuel
			this.menu3.add(item8);
			item8.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try	{
						java.awt.Desktop.getDesktop().open(new File("P_Médiane_Manuel_utilisateur_Duisit_Lacroix_Nivet.pdf"));
					} catch(Exception e) {
						e.printStackTrace();
					}
				}				
			});
			this.menu3.addSeparator();
			//A Propos
			this.menu3.add(item9);
			item9.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					//A propos
					APropos apropos = new APropos();
				}				
			});
			this.menuBar.add(menu1);
			this.menuBar.add(menu2);
			this.menuBar.add(menu3);
				
			this.setJMenuBar(menuBar);
			
			
			//Création de nos onglets
			
			//Pour l'onglet 0 ==> Données graphiques
			JPanel graphique = new JPanel();
			
			//pour l'onglet 1 ==> Données textuelles
			tableauAdjacences = new JTable(new DefaultTableModel());
			tableauAdjacences.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableauCentres = new JTable(new DefaultTableModel());
			tableauAffectations = new JTable(new DefaultTableModel());
			
			JPanel textuelle = new JPanel();
			textuelle.setLayout(new BoxLayout(textuelle, BoxLayout.Y_AXIS));
			textuelle.add(new JLabel("Matrice d'adjacence :"));
			textuelle.add(new JScrollPane(tableauAdjacences));
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			textuelle.add(new JLabel("Valeur de la fonction objectif : ?"));
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			textuelle.add(new JLabel("Centres ouverts :"));
			textuelle.add(new JScrollPane(tableauCentres));
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			textuelle.add(new JLabel("Affectations :"));
			textuelle.add(new JScrollPane(tableauAffectations));
			
			//Pour l'onglet 2 ==> Journal
			journal = new JPanel();

			
			//Création de notre conteneur d'onglets
			onglet = new JTabbedPane();
			onglet.add("Visualisation graphique", graphique);
			onglet.add("Visualisation textuelle", textuelle);
			onglet.add("Journal", journal);
			
			//on passe ensuite les onglets au contentPane
			this.getContentPane().add(onglet);

			this.setVisible(true);
		}

	public static void main(String[] args) {
		Interface i = new Interface();

	}

}
