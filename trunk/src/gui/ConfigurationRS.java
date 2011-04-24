package gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigurationRS extends Configuration
{
	private static final long serialVersionUID = 1L;

	private Button ok = new Button("OK");

	private JTextField tempInitDef = new JTextField("1000.0");
	private JLabel tempInit = new JLabel("Température initiale");
	private JTextField tempArretDef = new JTextField("0.001");
	private JLabel tempArret = new JLabel("Température d'arrêt");
	private JTextField nbItDef = new JTextField("-1");
	private JLabel nbIt = new JLabel("Nombre  d'itérations / paliers");
	private JTextField tauxDecroissanceDef = new JTextField("0.93");
	private JLabel tauxDecroissance = new JLabel("Taux de décroissance");
	private JCheckBox tempCase = new JCheckBox("Auto");
	private JCheckBox itCase = new JCheckBox("Auto");
	private JPanel pan = new JPanel();
	
	private Double auto = 1.0;
	private Double autoIt = 1.0;
	
	public ConfigurationRS()
	{
        //Initialisation de la configuration au Recuit simulé 
        info = new ArrayList<Double>();
        info.add(1000.);
        info.add(0.001);
        info.add(-1.0);
        info.add(0.93);
        info.add(1.0);
        info.add(1.0);
	}
	
	public ArrayList<Double> configuration()
	{
		this.setTitle("Configuration du Recuit Simulé");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		auto = autoIt = 1.0;
		
		pan = new JPanel();
		
	    Font police = new Font("Arial", Font.BOLD, 14);
	    tempInitDef.setFont(police);
	    tempInitDef.setPreferredSize(new Dimension(150, 30));
	    tempArretDef.setFont(police);
	    tempArretDef.setPreferredSize(new Dimension(150, 30));
	    nbItDef.setFont(police);
	    nbItDef.setPreferredSize(new Dimension(150, 30));
	    tauxDecroissanceDef.setFont(police);
	    tauxDecroissanceDef.setPreferredSize(new Dimension(150, 30));
	    ok.setFont(police);
	    ok.setPreferredSize(new Dimension(40, 20));
	    pan.add(tempInit);
	    
	    pan.add(tempInitDef);
	    tempCase.setSelected(true);
	    pan.getComponent(1).setEnabled(false);
	    ActionListener grisee = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (tempCase.isSelected())
	        	{
	        		auto = 1.0;
	        		pan.getComponent(1).setEnabled(false);
	        	}
	            else
	            {
	            	pan.getComponent(1).setEnabled(true);
	            	auto = 0.0;
	            }
	        }
	    }; 
	    
	    tempCase.addActionListener(grisee);
	    
	    pan.add(tempCase);
	    pan.add(tempArret);
	    pan.add(tempArretDef);
	    pan.add(nbIt);
	    pan.add(nbItDef);
	    pan.getComponent(6).setEnabled(false);
	    ActionListener griseeIt = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (itCase.isSelected())
	        	{
	        		autoIt = 1.0;
	        		pan.getComponent(6).setEnabled(false);
	        	}
	            else
	            {
	            	pan.getComponent(6).setEnabled(true);
	            	autoIt = 0.0;
	            }
	        }
	    };
	    itCase.setSelected(true);
	    itCase.addActionListener(griseeIt);
	    pan.add(itCase);
	    pan.add(tauxDecroissance);
	    pan.add(tauxDecroissanceDef);
	    pan.add(ok);
	   
	    ActionListener valider = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		info = new ArrayList<Double> ();
	    		info.add(Double.parseDouble(tempInitDef.getText()));
	    		info.add(Double.parseDouble(tempArretDef.getText()));
	    		info.add(Double.parseDouble(nbItDef.getText()));
	    		info.add(Double.parseDouble(tauxDecroissanceDef.getText()));
	    		info.add(auto);
	    		info.add(autoIt);
	        	dispose();
	        }
	    };
	    ok.addActionListener(valider);
	    this.setContentPane(pan);
	
		this.setVisible(true);

		return info;
	}
}
