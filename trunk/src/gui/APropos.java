package gui;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class APropos extends JFrame{

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel projet = new JLabel("Projet PNE - Polytech Inge4 - 2011");
     private JLabel noms = new JLabel("Marie Nivet, Rémi LACROIX et Bruno DUISIT");

 	private Button quitter = new Button("Quitter");
     private JPanel pan = new JPanel();
     
	public APropos(){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Configuration de VNS");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	    
	
	    pan.add(projet);
	    pan.add(noms);
	    pan.add(quitter);
		  
	    ActionListener quit = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        }
	    };
	    quitter.addActionListener(quit);
	   
	    this.setContentPane(pan);
	    
		this.setVisible(true);
	}
}
