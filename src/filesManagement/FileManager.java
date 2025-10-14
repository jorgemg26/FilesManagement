package filesManagement;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileManager {
	
	//We create the method to create Directories
	public void createDir(Scanner scan) {									
		System.out.println("Write the name of the Directory");
		String dirName = scan.nextLine().trim();													//We ask to the user the name of the Directory
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+dirName);
		
		if (dir.exists()) {																			//We check if the Directory already exists
			System.out.println("The Directory " + dirName + " already exists");
		}
		else {
			dir.mkdir();
			System.out.println(dirName + " created in " + dir.getAbsolutePath());
		}
		System.out.println();
	}
	//We create the method to create SubDirectories (in the Directory that the user chooses)
	public void createSubDir (Scanner scan) {
		System.out.println("Type route where you want to create the SubDirectory (only the name of the Directory if they are on it, or the Directory/SubDirectories if it has)");
		String route = scan.nextLine().trim();														//We ask to the user the route where he wants to create the SubDirectory
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+route);
		
		if (dir.exists()) {																			//We check that the route exists
			System.out.println("Type the name of the SubDirectory you want to create");
			String subDirName = scan.nextLine().trim();												//We ask the user the name of the SubDirectory
			
			File subDir = new File ("src"+File.separator+"filesManagement"+File.separator+route+File.separator+subDirName);
			
			if (subDir.exists()) {																	//We check that the SubDirectory exists
				System.out.println("The SubDirectory " + subDirName + " already exists");
			}
			else {
				subDir.mkdir();
				System.out.println(subDirName + " created in " + subDir.getAbsolutePath());
			}
		}
		else {
			System.out.println("The route " + route + " does not exist");
		}
		System.out.println();
	}
	//We create the method to create Files (in the Directory that the user chooses)
	public void createFile(Scanner scan) {
		System.out.println("Type route where you want to create the File (only the name of the Directory if they are on it, or the Directory/SubDirectories if it has), or press `enter` if you want to create it in the Base Directory");
		String route=scan.nextLine().trim();														//We ask to the user the name of the Directory where the user wants to create the File
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+route);
		
		if (dir.exists()) {																			//We check if the route where the user wants to create the Directory exists								
			System.out.println("Type the name of the File (with extension if you want it)");
			String fileName = scan.nextLine();														//We ask to the user the name of the File
			
			File file = new File ("src"+File.separator+"filesManagement"+File.separator+route+File.separator+fileName);
			
			try {																					//We check if the File already exists
				if (file.createNewFile()) {						
					System.out.println("File " + fileName + " created in " + file.getAbsolutePath());
				}
				else {
					System.out.println("File " + fileName + " already exists");
				}
			}
			catch (IOException e) {												
				System.out.println("Error creating the File " + e.getMessage());					//Print an error message	
			}
		}
		else {
			System.out.println("The route " + route + " does not exist");
		}
		System.out.println();
	}
	//We create the method to remove Files
	public void deleteFile(Scanner scan) {
		System.out.println("Write the route where the File is located (only the name of the Directory if they are on it, or the Directory/SubDirectories if it has)");
		String route = scan.nextLine().trim();														//We ask the location of the File 
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+route);
		
		if (dir.exists()) {																			//We check if the route exists
			System.out.println("Write the name of the File that you want to delete");
			String fileDelete = scan.nextLine();													//We ask the File that the user wants to delete
			
			File file = new File ("src"+File.separator+"filesManagement"+File.separator+route+File.separator+fileDelete);
			
			if (file.exists() && file.isFile()) {													//We check that the File exists
				if (file.getName().endsWith(".java")) {
					System.out.println("You can not delete program Files");							//We make sure that the user does not delete program Files
				}
				else {
					file.delete();																	//We delete the File
					System.out.println("The File " + fileDelete + " has been deleted");
				}
			}
			else {
				System.out.println("The File " + fileDelete + " does not exist");
			}
		}
		else {
			System.out.println("The route " + route + " does not exist");
		}
		System.out.println();
	}
	//We create the method to remove Directories
	public void deleteDirectory(Scanner scan) {
		boolean validNumber = false;
		int x=0;
		
		System.out.println("Write the Directory that you want to delete, or the route of the SubDirectory (Directory/SubDirectories)");
		String route = scan.nextLine().trim();														//We ask the route of the Directory or SubDirectory that the user wants to delete
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+route);
		
		if (dir.getName().equals("filesManagement")) {												//To make sure that the user does not delete this Directory
			System.out.println("You can not delete this Directory");
		}
		else {
			if (dir.exists() && dir.isDirectory()) {
				File[] files = dir.listFiles();														//A list of Files of the Directory
			
				if (files.length==0) {																//We check if the Directory is empty
					dir.delete();																	//We delete the Directory if it is empty
					System.out.println("Directory " + route + " deleted");
				}
				else {
					System.out.print("The Directory " + route + " contains: ");						//If the Directory is not empty, it shows the Files that it contains
					for (File f: files) {
						System.out.print(f.getName() + " ");
					}
					System.out.println();
				
					do {
						try {
							System.out.println("Press `1` if you want to delete the Directory and the Files. Press `2` if you do not");					
						
							x=scan.nextInt();														//We ask to the user the key
						
							if (x<1 || x>2) {
								validNumber=false;													//If it is not 1 or 2, it will ask the number another time
								System.out.println("Type 1 or 2");
								scan.nextLine();
							}
							else {
								validNumber=true;
								scan.nextLine();
							}
						}
						catch (InputMismatchException e) {
							validNumber=false;
							System.out.println("Type a valid number");								//If it is not a number, it will ask another time the number
							scan.nextLine();
						}
					}
					while(validNumber==false);
				
					if (x==1) {																		//If the user typed 1, it will delete the Directory and the content (using
						deleteAll(dir);
						System.out.println("Directory " + dir.getAbsolutePath() + " deleted (with its content included)");
					}
					else {
						System.out.println("The Directory " + route + " was not deleted");			//If the user typed 2, it will not delete the Directory with the Files
					}
				}
			}
			else {
				System.out.println("The Directory " + route + " does not exist");
			}
		}
		System.out.println();
	}
	//We create the method to delete all, the Directory and the File, it is recursive, because it calls itself
	public static void deleteAll (File dir) {
		File[] files = dir.listFiles();																//We create a list of the Files that are in the Directory
		
		if (files.length!=0) {																			//We check if the Directory has Files
			for (File f: files) {																	//To check all the Files
				if (f.isDirectory()) {																//If the Directory has another Directory, it will do the method another time for that Directory
					deleteAll(f);																	//The method we are using, called in itself	
				}		
				f.delete();																			//To delete the Files of the Directory
			}
		}
		dir.delete();																				//When all the Files are deleted, it will delete the Directory
	}
	//We create the method to show all the Directories that are created into filesManagement
	public void showDirectories (Scanner scan) {
		System.out.println("Type the route if you want to see the Directories of a SubDirectory (Directory/SubDirectories). If not, press `enter`");
		String route = scan.nextLine();																//We ask the user the route of the Directories he wants to see
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+route);
		
		
		if (dir.exists()) {
			File [] directories = dir.listFiles(File::isDirectory);									//We create a list of the Directories in the route
		
			if (directories.length!=0) {
				System.out.println("Directories: ");
				for (File d: directories) {															//To check all the Directories of the base Directory
					if (d.isDirectory()) {															//Only the Directories
						System.out.print("📁" + d.getName() + " ");
					}
				}
				System.out.println();
			}
			else {
				System.out.println("There are not Directories in " + dir.getAbsolutePath());
			}
		}
		else {
			System.out.println("The route " + route + " does not exist");
		}
		System.out.println();
	}
	//We create the method to show all the Files that are into the Directory that the user types
	public void showFiles (Scanner scan) {
		System.out.println("Type the route of the Files (only the name of the Directory if they are on it, or the Directory/SubDirectories if it has). Press enter if they are in the Base Directory");
		String route = scan.nextLine().trim();														//We ask the user the route where he wants to see the list of the Files
		
		File dir = new File ("src"+File.separator+"filesManagement"+File.separator+route);
		
		if (dir.exists()) {																			//We check that the route exists
			File [] files = dir.listFiles(File::isFile);											//We create the list of Files that are in the Directory
			System.out.println("Files in " + dir.getAbsolutePath() + ":");
			
			if (files.length!=0) {
				for(File f: files) {																//To check all the Files of the Directory
					if (f.isFile()) {																//Only the Files
						System.out.print("📄" + f.getName() + " ");
					}
				}
				System.out.println();
			}
			else {
				System.out.println("The route " + route + " has no Files");
			}
		}
		else {
			System.out.println("The route " + route + " does not exist");
		}
		System.out.println();
	}
}
