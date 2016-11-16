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
		System.out.println("Saisir le dossier à analyser:");
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
	    if(!des.endsWith("/") && !des.endsWith("\\"))des+="/";
	    for(int i=0; i<=pos; i++){
	    	File sortie= new File(new String(des+fichiersGPS[i].toString()));
	    	if(!CopierFichier(fichiersGPS[i],sortie))
	    		System.out.println("ERREUR: impossible de copier "+fichiersGPS[i].toString()+" vers "+des+"\n");
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
	
	private static boolean CopierFichier(File Source, File Destination){
        boolean resultat=false;
        FileInputStream filesource=null;
        FileOutputStream fileDestination=null;
        try{
            filesource=new FileInputStream(Source);
            fileDestination=new FileOutputStream(Destination);
            byte buffer[]=new byte[512*1024];
            int nblecture;
            while((nblecture=filesource.read(buffer))!=-1){
                fileDestination.write(buffer,0,nblecture);
            }
            resultat=true;
        }catch(FileNotFoundException nf){
            nf.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }finally{
            try{
                filesource.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                fileDestination.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
        return resultat;
    }
}
