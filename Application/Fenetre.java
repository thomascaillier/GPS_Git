import javax . swing .*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.applet.*;
import java.awt.*;
import java.awt.Color; 
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JButton;

public class Fenetre extends JFrame implements ActionListener
	
{
	private JButton home;
  public Fenetre()
  {            
    this.setTitle("Fenetre");
    this.setSize(800, 600);
    this.setLocationRelativeTo(null); 
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
 
    JPanel pan = new JPanel();
    pan.setBackground(new Color(0,51,102));        
    this.setContentPane(pan);               
    this.setVisible(true);

	  JPanel pane = new JPanel();
	  setLayout(null); 
	  repaint (); 
	 
	  
		home = new JButton(""); 
	  home.setIcon(new ImageIcon("icon.png"));
	  home.addActionListener(this);
	  home.setBounds(30,15,60,60); 
	  home.setBackground(new Color(0,51,102));
	  this.add(home);
	 
	  
	  
}
	
	
	public void actionPerformed(ActionEvent evt) 
	{
		Object source = evt.getSource();
		if(source==home)
		{
			 Fenetre fen = new Fenetre();
		}
	}
}