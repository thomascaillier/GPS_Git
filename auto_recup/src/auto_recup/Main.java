package auto_recup;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static Scanner sc;
	private static File fichiersGPS[];
	private static int pos;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.println("Saisir le dossier à analyser:\n");
		String src = sc.nextLine();
		File dossier = new File(src);
		pos=0;
		listPath(dossier);
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
	    	  if(getExtension(files[i])=="TCX")
	    	  fichiersGPS[pos]=files[i];
	    	  pos++;
	      }
	    }
	    System.out.println("Saisir le dossier de destination:");
	    String des = sc.nextLine();
	    for(int i=0; i<=pos; i++){
	    	
	    }
	}
	
	static String getExtension(File file){
		String filename = file.toString();
		// si le fichier contient un point il a une extension
		if (filename.lastIndexOf(".") > 0) {
		    // On récupère l'extension du fichier
		    String ext = filename.substring(filename.lastIndexOf("."));
		    // Si le fichier n'est pas en .txt on le met en .txt
		    return ext;
		} else {
		    // sinon c'est que le fichier n'a pas d'extension
		    System.out.println("Fichier incorrect");
		    return "";
		}
	}
}
