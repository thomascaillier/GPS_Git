package auto_recup;

import java.io.*;
import org.apache.commons.io.IOUtils;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.event.*;

public class Main {

	private static ArrayList<File> fichiersGPS = new ArrayList<File>();
	public JTextField tfsrc;
	public JTextField tfdst;
	public JFrame fen;
	public JPanel form;
	public JButton go;

	public static void main(String[] args){
		new Main();
	}
	
	public Main() {
		fen = new JFrame("Auto_Recup");
		form = new JPanel();
		JLabel lsrc = new JLabel("Entrer le dossier source:");
		tfsrc = new JTextField(38);
		JLabel ldst = new JLabel("Entrer le dossier destination:");
		tfdst = new JTextField(38);
		go = new JButton("AUTORECUP->");
		go.addActionListener(new goAction());
		form.add(lsrc);
		form.add(tfsrc);
		form.add(ldst);
		form.add(tfdst);
		form.add(go);
		fen.setSize(450,300); 
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setResizable(false);
		fen.setLocationRelativeTo(null);
		fen.setContentPane(form);
		fen.setVisible(true);		
	}
	
	static void listPath(File path, String des) {
	    File files[];
	    files = path.listFiles();
	    Arrays.sort(files);
	    for (int i = 0, n = files.length; i < n; i++) {
	      if (files[i].isDirectory()) {
	        listPath(files[i],des);
	      }
	      else{
	    	  if(formatAccept(getExtension(files[i]))){
	    	  fichiersGPS.add(files[i]);
	    	  }
	      }
	    }
	    if(!des.endsWith("/") && !des.endsWith("\\"))des+="/";
	    for(int i=0; i<fichiersGPS.size(); i++){
	    	String sortie = fichiersGPS.get(i).toString().substring(fichiersGPS.get(i).toString().lastIndexOf("\\"));
	    	sortie=des+sortie;
	    	if(!CopierFichier(fichiersGPS.get(i),sortie))
	    		System.out.println("ERREUR: impossible de copier "+fichiersGPS.get(i).toString()+" vers "+des+"\n");
	    }
	}
	
	private static String getExtension(File file){
		String filename = file.toString();
		// si le fichier contient un point il a une extension
		if (filename.lastIndexOf(".") > 0) {
		    // On récupère l'extension du fichier
		    return filename.substring(filename.lastIndexOf("."));
		} else {
		    // sinon c'est que le fichier n'a pas d'extension
		    System.out.println("Fichier incorrect");
		    return "";
		}
	}
	
	private static boolean CopierFichier(File Source, String DestSrc){
		File Destination = new File(DestSrc);
		InputStream input;
		OutputStream output;
		try {
			Destination.createNewFile();
			input = new FileInputStream(Source);
			output = new FileOutputStream(Destination);
			IOUtils.copy(input, output);
			System.out.println("Copie : "+Destination.toString());
			input.close();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Err1");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Err2");
			e.printStackTrace();
			return false;
		}
		return true;
    }
	
	private static boolean formatAccept(String ext){
		ArrayList<String> extensions = new ArrayList<String>();
		extensions.add(".tcx");
		extensions.add(".fit");
		extensions.add(".gpx");
		extensions.add(".hrm");
		extensions.add(".xml");
		extensions.add(".sdf");
		extensions.add(".itn");
		extensions.add(".nike+");
		extensions.add(".pwx");
		extensions.add(".csv");
		for(int i=0; i<extensions.size();i++){
			if(ext.toUpperCase().equals(extensions.get(i).toUpperCase()))
				return true;
		}
		return false;
	}
	
	public class goAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	String src = tfsrc.getText();
    		File dossier = new File(src);
    		listPath(dossier,tfdst.getText());
    		go.setEnabled(false);
    		System.out.println("Fin du programme");
        }
    }
}
