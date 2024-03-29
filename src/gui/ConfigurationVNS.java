package gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigurationVNS extends Configuration{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Button ok = new Button("OK");
	
	 private JTextField iterInitDef = new JTextField("5000");
     private JLabel iterInit = new JLabel("Nombre maximum d'itérations sans amélioration");
    
     private JPanel pan = new JPanel();
     
	public ConfigurationVNS()
	{
		info = new ArrayList<Double>();
        info.add(5000.0);
	}
	
	public ArrayList<Double> configuration()
	{
		this.setTitle("Configuration de VNS");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
	    
		pan = new JPanel();
		
	    Font police = new Font("Arial", Font.BOLD, 14);
	    iterInitDef.setFont(police);
	    iterInitDef.setPreferredSize(new Dimension(150, 30));
	
	    ok.setFont(police);
	    ok.setPreferredSize(new Dimension(40, 20));

	    pan.add(iterInit);
	    pan.add(iterInitDef);
	    
	    pan.add(ok);
	   
	    ActionListener valider = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		info = new ArrayList<Double> ();
	    		info.add(Double.parseDouble(iterInitDef.getText()));
	    		
				dispose();
	        }
	    };
	    ok.addActionListener(valider);
	    this.setContentPane(pan);
	
		this.setVisible(true);
		
		return info;
	}
}
