package auto_recup;

import java.io.*;
import org.apache.commons.io.IOUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static Scanner sc;
	private static ArrayList<File> fichiersGPS = new ArrayList<File>();

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.println("Saisir le dossier à analyser:");
		String src = sc.nextLine();
		File dossier = new File(src);
		listPath(dossier);
		System.out.println("Fin du programme");
	}
	
	static void listPath(File path) {
	    File files[];
	 
	    files = path.listFiles();
	    Arrays.sort(files);
	    for (int i = 0, n = files.length; i < n; i++) {
	      if (files[i].isDirectory()) {
	        listPath(files[i]);
	      }
	      else{
	    	  if(getExtension(files[i]).equals(".tcx")){
	    	  fichiersGPS.add(files[i]);
	    	  }
	      }
	    }
	    System.out.println("Saisir le dossier de destination:");
	    String des = sc.nextLine();
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
}
