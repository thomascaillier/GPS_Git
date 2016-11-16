import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Fenetre() {
		this.setTitle("Fenetre");
    		this.setSize(800, 600);
    		this.setLocationRelativeTo(null); 
	  	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
