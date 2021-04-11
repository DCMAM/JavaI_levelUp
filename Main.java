import java.util.Scanner;

public class Main {
	Scanner input = new Scanner(System.in);
	
	int max = 50;
	int totalUser = 0;
	int totalGame = 0;
	String currentUser;
	float currentScore=0;
	
	String[] name = new String[max];
	String[] email = new String[max];
	String[] password = new String[max];
	
	String[] scoreboardName = new String[max];
	float[] scoreboardScore = new float[max];
	
	public Main() {
		int menu = 0;
		do {
			printMenu();
			try {
				menu = input.nextInt();				
			} catch (Exception e) {
				System.out.println("You should input integer!");
			}
			if(menu==1) Login();
			else if(menu==2) register();
			else if(menu==3) break;
		}while(true);
	}
	
	private void Login() {
		String loginEmail, loginPassword;
		int index=0;
		do {
			System.out.print("Please input an email : ");
			loginEmail = input.next();
			int flag=0;
			for (int i = 0; i < totalUser; i++) {
				if(email[i].equals(loginEmail)) {
					index = i;
					flag=1;
					break;
				}
			}
			if(flag==1) break;
		}while(true);
		
		System.out.print("Please input a password : ");
		loginPassword = input.next();
		if(loginPassword.equals(password[index])) { 
			currentUser = name[index];
			gamePage();
			return;
		}
		else System.out.println("User not found!");
	}
	
	private void gamePage() {
		int option=0;
		do {
			printMenu2();
			try {
				option = input.nextInt();				
			} catch (Exception e) {
				System.out.println("You should input integer!");
			}
			if(option==1) { 
				StartGame();
//				String[] scoreboardName = new String[max];
//				int[] scoreboardScore = new int[max];
//				scoreboardName[totalGame] = currentUser;
//				scoreboardScore[totalGame] = (int)currentScore;
				totalGame++;
			}
			else if(option==2) break;
		}while(true);
		return;
	}
	
	private void StartGame() {
		System.out.println("Commander $T : Earth is dying...");
		System.out.println("Commander $T : We only have 1000 people and 3 warp energy left ...");
		System.out.println();
		System.out.println("Task : ");
		System.out.println("1. You need to choose the most habitable planet in each galaxy");
		System.out.println("2. Choose how many people to send to the planet");
	

		
		float score = 0;
		int loop=0, totalPeople=1000;
		while(loop < 3) {
			newGalaxy();
			int num1 = (int)(Math.random()*2)+1;
			int num2 = (int)(Math.random()*2)+1;
			int num3 = (int)(Math.random()*4)+1;
			
			int num4 = (int)(Math.random()*2)+1;
			int num5 = (int)(Math.random()*2)+1;
			int num6 = (int)(Math.random()*4)+1;
			
			int num7 = (int)(Math.random()*2)+1;
			int num8 = (int)(Math.random()*2)+1;
			int num9 = (int)(Math.random()*4)+1;
			
			System.out.println("Arrived at Galaxy Andromeda...");
			System.out.println(totalPeople + " people left");
			System.out.println("===========================");
			System.out.println("1. Planet " + generatePlanet());
			System.out.println("Weather : " + generateWeather(num1));
			System.out.println("Flora   : " + generateFlora(num2));
			System.out.println("Fauna   : " + generateFauna(num3));
			System.out.println("---------------------------");
			System.out.println("2. Planet " + generatePlanet());
			System.out.println("Weather : " + generateWeather(num4));
			System.out.println("Flora   : " + generateFlora(num5));
			System.out.println("Fauna   : " + generateFauna(num6));
			System.out.println("---------------------------");
			System.out.println("3. Planet " + generatePlanet());
			System.out.println("Weather : " + generateWeather(num7));
			System.out.println("Flora   : " + generateFlora(num8));
			System.out.println("Fauna   : " + generateFauna(num9));
			System.out.println("---------------------------");
			
			int menu=0;
			do {
				System.out.println("What planet do you want to visit ? [Exit Game : 0]");
				try {
					menu = input.nextInt();					
				} catch (Exception e) {
					System.out.println("You should input 1-3!");
				}
			}while(menu < 0 || menu > 3);
			
			if(menu == 1) {
				score = score + calculateScore(num1, num2, num3);
			}
			else if(menu == 2) {
				score = score + calculateScore(num4, num4, num6);
			}
			else if(menu == 3) {
				score = score + calculateScore(num7, num8, num9);
			}
			
			int totalSent=0;
			do {
				System.out.println("How many people do you want to send? (Max : 50% of total people) [exit Game : 0]");
				try {
					totalSent = input.nextInt();
				} catch (Exception e) {
					
				}
				if(loop == 2) {
					if(totalSent == totalPeople) break;
				}
				else {
					if(totalSent <= totalPeople/2) break;
				}				
			}while(true);
			
			totalPeople = totalPeople - totalSent;
			
			score = (score/3)*totalSent;
			loop++;
		}
		
		currentScore = score;
		
		System.out.println("Congratulasions !!! You have finnished the game ...");
		System.out.println("Your score : " + score);
		System.out.println("===============================================");
		sortPage();
	}
	

	private void sortPage() {
		scoreboardName[totalGame] = currentUser;
		scoreboardScore[totalGame] = currentScore;
		
		int option =0;
		do {
			System.out.println("Sort Menu");
			System.out.println("1. Sort users score Ascending");
			System.out.println("2. Sort Users Score Descending");
			System.out.println("Select a menu to sort [Exit : 0]: ");
			System.out.print("Input : ");
			try {
				option = input.nextInt();
			} catch (Exception e) {
			}
			if(option == 1 ) {
				quickSort(scoreboardScore, 0, totalGame);
				for (int i = 0; i < totalGame+1; i++) {
					System.out.println(i+1 + ". " + scoreboardScore[i] + " | " + scoreboardName[i]);
				}
			}
			else if(option == 2) {
				quickSort(scoreboardScore, 0, totalGame);
				for (int i = 0; i < totalGame+1; i++) {
					System.out.println(i+1 + ". " + scoreboardScore[i] + " | " + scoreboardName[i]);
				}
			}
			else if(option == 0) break;
		}while(true);
	}

	private float calculateScore(int num1, int num2, int num3) {
		float total = 0;
		
		if(num1 == 1) total = total + 1;
		else if(num1 == 2) total = total + (float)0.4;
		else if(num1 == 3) total = total + (float)0.2;
		
		if(num2 == 1) total = total + 1;
		else if(num2 == 2) total = total + (float)0.3;
		else if(num2 == 3) total = total + 0;
		
		if(num3 == 1) total = total + 1;
		else if(num3 == 2) total = total + (float)0.6;
		else if(num3 == 3) total = total + (float)0.4;
		else if(num3 == 4) total = total + (float)0.2;
		else if(num3 == 5) total = total + 0;
		
		return total;
	}

	private String generatePlanet() {
		int random1 = (int)(Math.random()*('Z'-'A'))+'A';
		int random2 = (int)(Math.random()*('Z'-'A'))+'A';
		int random3 = (int)(Math.random()*('Z'-'A'))+'A';
		return "" + (char)random1 + (char)random2 + (char)random3;
	}
	
	private String generateWeather(int num) {
		int random = (int)(Math.random()*1)+1;
		
		if(num == 1) return "Calmy";
		else if(num == 2 && random == 1) return "Heat";
		else if(num == 2 && random == 2) return "Cold";
		else if(num == 3 && random == 1) return "Toxic";
		else if(num == 3 && random == 2) return "Radioactive";
		return null;
	}

	private String generateFlora(int num) {
		if(num == 1) return "Non-hazardous";
		else if(num == 2) return "Hazardous";
		else if(num == 3) return "No Flora";
		return null;
	}
	
	private String generateFauna(int num) {
		if(num == 1) return "Passive";
		else if(num == 2) return "Prey";
		else if(num == 3) return "Predator";
		else if(num == 4) return "Human Predator";
		else if(num == 5) return "No Fauna";
		return null;
	}

	private void newGalaxy() {
		System.out.print("Going to new Galaxy 3");
		try{		
		   Thread.sleep(1000);
		}
		catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
		System.out.print(" 2");
		try{		
		   Thread.sleep(1000);
		}
		catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
		System.out.print(" 1");
		try{		
		   Thread.sleep(1000);
		}
		catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
		System.out.println();
		System.out.println();
	}

	private void printMenu2() {
		System.out.println("Welcome to ST Galaxy...");
		System.out.println("=======================");
		System.out.println("Main menu");
		System.out.println("1. Start Game");
		System.out.println("2. Exit");
		System.out.println("what do you want to do ?");
		System.out.print("Input : ");
	}

	private void register() {
		do {
			System.out.print("Please input a name : ");
			name[totalUser] = input.next();
		}while(name[totalUser].length() <= 0);
		do {
			System.out.print("Please input an email : ");
			email[totalUser] = input.next();
			break;
		}while(true);
		do {
			System.out.print("Please input a password [Min LEngth : 4] : ");
			password[totalUser] = input.next();
			if(password[totalUser].length() >= 4) break;
			System.out.println("Min Password Length is 4 !!!");
		}while(true);
		
		totalUser++;
	}



	static void swap(float[] arr, int i, int j){
	    float temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}
	 
	static int partition(float[] arr, int low, int high){
	    float pivot = arr[high];
	     
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++){
	         
	        if (arr[j] < pivot){
	            i++;
	            swap(arr, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    return (i + 1);
	}
	 
	static void quickSort(float[] arr, int low, int high){
	    if (low < high){
	        int pi = partition(arr, low, high);
	 
	        quickSort(arr, low, pi - 1);
	        quickSort(arr, pi + 1, high);
	    }
	}

	private void printMenu() {
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.print(">> ");
	}

	public static void main(String[] args) {
		new Main();
	}
}
