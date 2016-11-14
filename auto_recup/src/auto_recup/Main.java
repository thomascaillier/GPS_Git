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
	      System.out.println(files[i].toString());
	      if (files[i].isDirectory()) {
	        listPath(files[i]);
	      }
	      else{
	    	  if(files[i].getExtension()=="TCX")
	    	  fichiersGPS[pos]=files[i];
	    	  pos++;
	      }
	    }
	}
}
