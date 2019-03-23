import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {

	public static void main(String[] args){
		
		String str = null;
		
		
		try {
			 System.out.print("Enter the file name with extention(.txt) the directory : ");

	            Scanner input = new Scanner(System.in);

	            File file = new File(input.nextLine());

	            input = new Scanner(file);
	            while (input.hasNextLine()) {
	                str = input.nextLine();
	            }
			    input.close();
			  
			  String[] y = str.split(" ");
			  //gemizoun oles tis metavlites pou mporei xan xreiastoume afou kanoume split to arxeio 
			  int i = 0;
			  @SuppressWarnings("unused")
			  int numofstates = Integer.parseInt(y[0]);
			  i++;
			  int startingstate = Integer.parseInt(y[i]);
			  i++;
			  int numoffinalstates = Integer.parseInt(y[i]);
			  i++;
			  //edw mexri na teleiwsei t
			  int finalstates[] = new int[numoffinalstates] ;
			  for(int j = 0; j < numoffinalstates; j++) {
				  	finalstates[j] = Integer.parseInt(y[i]);
				  	i++;
			  }
			  int numoftransitions = Integer.parseInt(y[i]);
			  i++;
			  String transitions[] = new String[numoftransitions];
			  int max = y.length - i;
			  for(int j = 0; j < max; j++) {				  
				    transitions[j]=  y[i];	 
				    i++;
			  }
			  repeatread(transitions,finalstates,startingstate); 
			  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}      
	}	
	
	public static void repeatread(String transitions[],int finalstates[],int startingstate) {
	
		String s = null;
		String finished = "y";	
		int flag = 0;
		
		Scanner keyboard = new Scanner(System.in);
	
	    while(finished != "n") {
	    	int currentstate = startingstate;
	    	System.out.println("Give input :");	
	        s = keyboard.nextLine();
		    //spame thn leksh se characters gia na mporesoume na treksoume to automato
	        String l[] = s.split("(?!^)");
	        
		    for(int i = 0;i < l.length;i++){
		    //kanoume override kai to nextstate apo to move to kanoume currentstate gia to epomeno xarakthra p 8a paroume apo to input proxwraei to automato se kainourgia 8esh me vash thn leksh 
		    //pou vriskete xwrismenh ston pinaka l 	
		    int temp = move(l[i],transitions,currentstate);   
		    currentstate = temp;
		    }
		    for(int i = 0; i < finalstates.length;i++) {
		    	if(currentstate == finalstates[i]) {
		    		flag = 1;
		    	}
		    }
		    
		    if(flag == 1)
		    	System.out.println("automaton finished in a final state");
		    else
		    	System.out.println("automaton didn't finish in a final state");
		    //edw teleiwnei h ka8e leksh 
		    while(true) {
			    System.out.println("do you want to give another input y/n?");
			    finished = keyboard.nextLine();
			    
			    if(finished.equals("n")) 
			    	System.exit(1);
			    else if(finished.equals("y")) {
			    	flag = 0;
			        break;
			    }
			    else
			    	System.out.println("Give either y/n for no/yes");
		    }
	    }
	    keyboard.close();
	    
   }
	
	public static int move(String s,String transitions[], int currentstate) {
		int nextstate = 0;
		
		//paralamvanei ena string apo gramata px aaabbbccc prepei na ta spaseis kai na ta elenxei
		//mia loopa gia na elenxeis an ontws to automato teleiwse
		String[] y = new String[transitions.length*3];
		for(int i = 0 ; i < transitions.length;i++) {
			y = transitions[i].split("->");			
			int firststate = Integer.parseInt(y[0]);			
			if(y[1].equals(s) && firststate == currentstate) {
				nextstate = Integer.parseInt(y[2]);
			}
		}
		return nextstate;
	}

}