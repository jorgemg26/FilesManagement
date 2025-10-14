package filesManagement;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Files_Directories {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		
		int x=0;
		boolean validNumber;
		
		FileManager fileManager = new FileManager();
		
		do {																		//Loop to leave when user presses 
			do {																	//Loop do-while which is repeated until user presses a valid value
				//To inform about the menu to the user
				System.out.println("Press the next keys:");
				System.out.println("1) Create Directory");
				System.out.println("2) Create SubDirectory");
				System.out.println("3) Create File");
				System.out.println("4) Remove File");
				System.out.println("5) Remove Directory or SubDirectory");
				System.out.println("6) Show Directories");
				System.out.println("7) Show Files");
				System.out.println("8) Exit");
				
				try {					
					x=scan.nextInt();												//We ask to the user the key
					
					if (x<1 || x>8) {
						validNumber=false;											//If it is not a number between 1 and 8, it will ask the number another time
						System.out.println("Type a number between 1 and 8");
						scan.nextLine();
					}
					else {
						validNumber=true;
						scan.nextLine();
					}
				}
				catch (InputMismatchException e) {
					validNumber=false;
					System.out.println("Type a valid number");						//If it is not a number, it will ask another time the number
					scan.nextLine();
				}
			}
			while (validNumber==false);
			
			switch (x) {															//A switch for the menu´s options
				//If the user types 1, it tries to create the Directory
				case 1:	
					fileManager.createDir(scan);
					break;
				//If the user types 2, it tires to create a SubDirectory (in the Directory that the user chooses)
				case 2:
					fileManager.createSubDir(scan);
					break;
				//If the user types 3, it tries to create the File (in the Directory that the user chooses)	
				case 3:
					fileManager.createFile(scan);
					break;
				//If the user types 4, it tries to remove the File
				case 4:
					fileManager.deleteFile(scan);
					break;
				//If the user types 4, it tries to remove the Directory
				case 5:
					fileManager.deleteDirectory(scan);
					break;
				//If the user types 6, it shows the Directories
				case 6:
					fileManager.showDirectories(scan);
					break;
				//If the user types 7, it shows the Files of the Directory that the user chooses
				case 7:
					fileManager.showFiles(scan);
					break;
				//If the user types 8, the user leaves the program
				case 8:
					System.out.println("See you soon!");
			}
		}
		while (x!=8);

	}

}
