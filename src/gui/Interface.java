package gui;



import ilog.concert.IloException;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import pmediane.CPLEXPMediane;
import pmediane.DataPMediane;
import pmediane.FonctionObjectifPMediane;
import pmediane.HeuristiqueGloutonnePMediane;
import pmediane.PLNEPMediane;
import pmediane.RSPMediane;
import pmediane.SolutionPMediane;
import pmediane.VNSPMediane;
import pmediane.VoisinagePMediane;


	public class Interface extends JFrame
	{
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
		
		private JTable tableauAdjacences;
		private JTable tableauCentres;
		private JTable tableauAffectations;
		
		private DataPMediane donnees;
		private SolutionPMediane sol;
		private FonctionObjectifPMediane f;
		private VoisinagePMediane voisinage;
		private HeuristiqueGloutonnePMediane solution;
		
		private JPanel journal;
		private DefaultTableModel tableJournal;
		private Vector<String> log;
		
		//Pour les onglets
		private JTabbedPane onglet;

		private JLabel labelAdjacences;
		private JLabel labelValFoncObj;

		private JButton boutonExport;

		private UndirectedSparseGraph<Integer, Number> graph;
		private VisualizationViewer<Integer, Number> vv;

		private JPanel graphique;
		
		public Interface()
		{
			f = new FonctionObjectifPMediane();
			voisinage = new VoisinagePMediane();
			solution = new HeuristiqueGloutonnePMediane();
			
			this.setTitle("Programmation en Nombre Entier");
			this.setSize(800, 600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			
			//Fichier
			this.menu1.add(item1);
			item1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//Charger une instance
					graphique.removeAll();
					
					JFileChooser instance = new JFileChooser();
					instance.showOpenDialog(null);
					try {
						donnees = new DataPMediane(instance.getSelectedFile().getAbsolutePath());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					//pour l'onglet 1
					labelAdjacences.setText("Matrice d'adjacence (nb entités = "+
							donnees.getNbEntites()+", nb centres à ouvrir = "+donnees.getNbCentres()+") :");
					
					log("Chargement d'une instance : "+instance.getSelectedFile().getAbsolutePath());
					log(" - Nombre d'entités : "+donnees.getNbEntites());
					log(" - Nombre de centres à ouvrir : "+donnees.getNbCentres());
					log("");
					
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
					
					labelValFoncObj.setText("Valeur de la fonction objectif : ?");
					
					tm = (DefaultTableModel) tableauCentres.getModel();
					tm.setColumnCount(0);
					
					tm = (DefaultTableModel) tableauAffectations.getModel();
					tm.setColumnCount(0);
					
					item2.setEnabled(true);
					boutonExport.setEnabled(false);
				}				
			});
			item2.setEnabled(false);
			this.menu1.add(item2);
			
			//Lancer la résolution
			item2.addActionListener(new ActionListener() {
				
				private FRLayout<Integer, Number> layout;

				public void actionPerformed(ActionEvent arg0) {
					long t1, t2;
					
					if (item4.isSelected())
					{
						RSPMediane rs = new RSPMediane((config.getInfo().get(4) == 0.0) ? config.getInfo().get(0) : RSPMediane.TEMP_INIT_AUTO,
														config.getInfo().get(1),
													   (config.getInfo().get(5) == 0.0) ? config.getInfo().get(2).intValue() : RSPMediane.NB_PALIERS_AUTO,
													   config.getInfo().get(3), voisinage, solution);
						
						log("Début du calcul : Méthode : Recuit Simulé");
						log("Température initiale : " + ((config.getInfo().get(4) == 0.0) ? config.getInfo().get(0) : "Auto"));					
						log("Température d'Arrêt : " + config.getInfo().get(1));
						log("Nombre d'itération / paliers : " + ((config.getInfo().get(5) == 0.0) ? config.getInfo().get(2) : "Auto"));
						log("Taux de décroissance : " + config.getInfo().get(3));
						
						t1 = System.nanoTime();
						sol = rs.calculerSolution(donnees);
						t2 = System.nanoTime();
					}
					else if (item5.isSelected())
					{
						VNSPMediane vns = new VNSPMediane(config.getInfo().get(0).intValue(), voisinage, solution);
						
						log("Début du calcul : Méthode : VNS");
						log("Nombre maximal d'itérations sans amélioration : " + config.getInfo().get(0));
						
						t1 = System.nanoTime();
						sol = vns.calculerSolution(donnees);
						t2 = System.nanoTime();
					}
					else
					{
						CPLEXPMediane cplex = null;
						
						try {
							cplex = new CPLEXPMediane();
						} catch (IloException e) {
							e.printStackTrace();
						}
						log("Début de la résolution : Méthode : CPLEX");
						
						t1 = System.nanoTime();
						sol = cplex.calculerSolution(new PLNEPMediane(donnees));
						t2 = System.nanoTime();
					}
					
					log("Fin de la résolution : valeur de la fonction objectif : "+f.calculer(sol)+
						" | temps = "+((t2 - t1)/1000000000.)+" s");
					log("");
					
					labelValFoncObj.setText("Valeur de la fonction objectif : "+f.calculer(sol));
					
					DefaultTableModel tm = (DefaultTableModel) tableauCentres.getModel();
					tm.setColumnCount(0);
					Integer[] vals = new Integer[1];
					for (int i=0; i<donnees.getNbCentres(); i++)
					{
						vals[0] = sol.getCentres()[i]+1;
							
						tm.addColumn("Centre "+(i+1), vals);
					}
					
					tm = (DefaultTableModel) tableauAffectations.getModel();
					tm.setColumnCount(0);
					vals = new Integer[1];
					for (Integer i=0; i<donnees.getNbEntites(); i++)
					{
						vals[0] = sol.getCentre(i)+1;
							
						tm.addColumn(i+1, vals);
					}
					
					boutonExport.setEnabled(true);
					
					// Affichage graphique
					graphique.removeAll();
					graphique.setLayout(new BoxLayout(graphique, BoxLayout.Y_AXIS));
					graph = new UndirectedSparseGraph<Integer, Number>();
					for (int i=1; i<=donnees.getNbEntites(); i++)
						graph.addVertex(i);
					for (int i=0; i<donnees.getNbEntites(); i++)
					{
						if (sol.getCentre(i) != i)
							graph.addEdge(new Double(Math.random()), sol.getCentre(i)+1, i+1);
					}
					layout = new FRLayout<Integer, Number>(graph);
					layout.setAttractionMultiplier(0.1);
					layout.setRepulsionMultiplier(0.1);
			        vv = new VisualizationViewer<Integer, Number>(layout);
			        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
			        vv.setGraphMouse(new DefaultModalGraphMouse<Integer, Number>());
			        graphique.add(new GraphZoomScrollPane(vv));
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
					config.configuration();
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
					new APropos();
				}				
			});
			this.menuBar.add(menu1);
			this.menuBar.add(menu2);
			this.menuBar.add(menu3);
				
			this.setJMenuBar(menuBar);
			
			
			//Création de nos onglets
			
			//Pour l'onglet 0 ==> Données graphiques
			graphique = new JPanel();
    		
			//pour l'onglet 1 ==> Données textuelles
			tableauAdjacences = new JTable(new DefaultTableModel());
			tableauAdjacences.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableauCentres = new JTable(new DefaultTableModel());
			tableauCentres.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tableauAffectations = new JTable(new DefaultTableModel());
			tableauAffectations.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			JPanel textuelle = new JPanel();
			textuelle.setLayout(new BoxLayout(textuelle, BoxLayout.Y_AXIS));
			labelAdjacences = new JLabel("Matrice d'adjacence :");
			textuelle.add(labelAdjacences);
			textuelle.add(new JScrollPane(tableauAdjacences));
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			labelValFoncObj = new JLabel("Valeur de la fonction objectif : ?");
			textuelle.add(labelValFoncObj);
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			textuelle.add(new JLabel("Centres ouverts :"));
			textuelle.add(new JScrollPane(tableauCentres));
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			textuelle.add(new JLabel("Affectations :"));
			textuelle.add(new JScrollPane(tableauAffectations));
			textuelle.add(Box.createRigidArea(new Dimension(1, 10)));
			boutonExport = new JButton("Exporter la solution");
			boutonExport.setEnabled(false);
			boutonExport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser export = new JFileChooser();
					export.showOpenDialog(null);
					try {
						sol.exporter(export.getSelectedFile().getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			textuelle.add(boutonExport);
			
			//Pour l'onglet 2 ==> Journal
			journal = new JPanel();
			journal.setLayout(new BoxLayout(journal, BoxLayout.Y_AXIS));
			tableJournal = new DefaultTableModel();
			tableJournal.addColumn("Information");
			journal.add(new JScrollPane(new JTable(tableJournal)));

			
			//Création de notre conteneur d'onglets
			onglet = new JTabbedPane();
			onglet.add("Visualisation graphique", graphique);
			onglet.add("Visualisation textuelle", textuelle);
			onglet.add("Journal", journal);
			
			//on passe ensuite les onglets au contentPane
			this.getContentPane().add(onglet);

			this.setVisible(true);
		}
	
	private void log(String s)
	{
		log = new Vector<String>();
		log.add(s);
		tableJournal.addRow(log);
	}
	
	public static void main(String[] args)
	{
		new Interface();
	}
}
