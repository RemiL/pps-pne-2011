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
	
	 private JTextField iterInitDef = new JTextField("250");
     private JLabel iterInit = new JLabel("Nombre maximum d'itérations sans amélioration");
    
     private JPanel pan = new JPanel();
     
	public ConfigurationVNS(){
	}
	
	public ArrayList<Double> configuration(){
		this.setTitle("Configuration de VNS");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	
		
	    
	// pan.setBackground(Color.white);
	 //   pan.setLayout(new BorderLayout());
	           
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	   
	    
	    Font police = new Font("Arial", Font.BOLD, 14);
	    iterInitDef.setFont(police);
	    iterInitDef.setPreferredSize(new Dimension(150, 30));
	
	    ok.setFont(police);
	    ok.setPreferredSize(new Dimension(40, 20));
	    //tempCase.setSelected(true);
	    pan.add(iterInit);
	    
	    pan.add(iterInitDef);
	    
	
	    pan.add(ok);
	  // pan.add(top, BorderLayout.NORTH);
	   
	    ActionListener valider = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
	        }
	    };
	    ok.addActionListener(valider);
	    this.setContentPane(pan);
	
		this.setVisible(true);
		ArrayList<Double> list = new ArrayList<Double> ();
		list.add(Double.parseDouble(iterInitDef.getText()));
		return list;
	}
}
