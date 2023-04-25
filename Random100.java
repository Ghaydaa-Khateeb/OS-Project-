import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.*;
public class Random100 {
	//PID , arrivalTime , burstTime , numOfLines
	public static int PID[];
	public static int arrivalTime[];
	public static int burstTime[];
	public static int Priority[];
	public static int numOfLines;
   public static void main(String [] args)throws IOException{
	   run();
   }
    public static void run() throws IOException {
	    	File fi = new File("process_information.txt");
		   PrintWriter out = new PrintWriter(fi);
			Random generator = new Random();
			int num1 , num2 , num3 , num4 , num5 , num6 ;
			//Generate Random numbers to a file
			numOfLines = generator.nextInt(10) + 3 ;
			numOfLines = numOfLines - 1 ;
			out.println("ArrivalTime:BurstTime:Priority:Repeat:Interval:Deadline");
			for (int i = 0; i <= numOfLines; i++) {
			    //generate a random number
				num1 = generator.nextInt(10); 
			    num2 = generator.nextInt(10) + 1;
			    num3 = generator.nextInt(10);
			    num4 = generator.nextInt(10);
			    num5 = generator.nextInt(10);	
			    num6 = generator.nextInt(10);
				out.println(num1+":"+num2+":"+num3+":"+num4+":"+num5+":"+num6); //write the number to the file
			}
			out.close(); 
			//Defining the arrays to store file data
			 PID         = new int [numOfLines + 1];
			 arrivalTime = new int [numOfLines + 1];
			 burstTime   = new int [numOfLines + 1];
			 Priority    = new int [numOfLines + 1];
			int[] repeat      = new int [numOfLines + 1];
			int[] interval    = new int [numOfLines + 1];
			int[] deadline    = new int [numOfLines + 1];
			
			//fill the unique process ID array;
			for(int i=0 ; i< numOfLines  ; i++)
				PID[i] = i + 1 ;
			
			//read data from a file
			 int idx = 0 , counter = 0;
			 boolean flag = true;
			 Scanner read = new Scanner(fi);
			 while(read.hasNextLine()) {
				 counter ++;
				 if(flag) {
					 flag = false;
					 String unUsed = read.nextLine();
					 continue;
				 }
				 String tok[];
				 String data = read.nextLine();
				 tok = data.trim().split(":");
				 if(idx <= numOfLines) {
					 arrivalTime[idx]   = Integer.parseInt(tok[0]);
					 burstTime[idx]     = Integer.parseInt(tok[1]);
					 Priority[idx]      = Integer.parseInt(tok[2]);
					 repeat[idx]        = Integer.parseInt(tok[3]);
					 interval[idx]      = Integer.parseInt(tok[4]);
					 deadline[idx]      = Integer.parseInt(tok[5]);
					 idx = idx + 1 ;
				 	}
			 	}
			 FirstComeFirstServed(PID , arrivalTime , burstTime , numOfLines);
			 }
			// ShortestJobFirst SJF = new ShortestJobFirst(PID , arrivalTime , burstTime , numOfLines);
			// SJF.makeTable();
		    /* ShortestRemainingTimeFirst SRTF = new ShortestRemainingTimeFirst(PID , arrivalTime , burstTime , numOfLines);
			 SRTF.makeTable();
			 
			 System.out.println();
			 System.out.println("Explicit Priority");
			 ExplicitPriority EP = new ExplicitPriority(PID , arrivalTime , burstTime ,Priority, numOfLines);
			 EP.makeTable();
			 MultiProgrammed mp = new MultiProgrammed(PID , arrivalTime , burstTime , numOfLines);
			 mp.makeTable();*/
			 public static void FirstComeFirstServed(int[] PID, int[] AT, int[] BT, int numOfLines) {
				   System.out.println(" <---- Shortest Job First ---->");
				   boolean isFinished[]          = new boolean [numOfLines] ;
				   int arrives[]                 = new int [numOfLines];
				   int differences[]  		     = new int [numOfLines];
				   int FinishTime[]              = new int[numOfLines];
				   int waitTime[]                = new int [numOfLines];
				   double WTA[]                  = new double [numOfLines];
				   int TurnAroundTime[]          = new int[numOfLines];
				   int BurstTime []     = new int[numOfLines];
				   for(int i=0 ; i<numOfLines ; i++) {
					   BurstTime[i] = BT[i];
				   }
				   ArrayList<String> GantDiagram = new ArrayList <String>();
				   ArrayList <Pair<Integer , Integer> > vals= new ArrayList<Pair<Integer , Integer > >();
				   int numOfProcesses = 0 , totalBurst = 0 , startTime = 0 , st = 0 , counter = 0 , timer = 0;
				   for(int i=0 ; i<numOfLines ; i++)
					   totalBurst += BT[i];
				   int lastArrived = 0;
				   for(int i=0 ; i<numOfLines ; i++)
						if(AT[i] > lastArrived)
							lastArrived = AT[i];
				   for(int i=0 ; i < numOfLines ; i++)
					   arrives[i] = AT[i];
				   for(int i=0 ; i<numOfLines ; i++)
					   for(int j=i + 1 ; j<numOfLines ; j++)
						   if(arrives[i] >= arrives[j]) {
							   int temp = arrives[i];
							   arrives[i] = arrives[j];
							   arrives[j] = temp;
						   }
				   boolean isArrived [] =  new boolean[lastArrived];
				   for(int i=0 ; i< lastArrived ; i++) {
					   for(int j=0 ; j<numOfLines ; j++)
						   if(i == AT[j]){
							   isArrived[i] = true;
						   }
				   }
				   while(true) {
					   if(timer > lastArrived  )
						   break;
					   
					   if(isArrived[timer] == true ) {
		    				  for(int k=0 ; k < numOfLines ; k ++)
		    					  if(AT[k] == timer) 
		    						  vals.add(new Pair<Integer , Integer>(BurstTime[k] , PID[k]));
		    				  isArrived[timer] = false;
					   }
					   timer++;
				   }
				   for(int i=0 ; i<vals.size() ; i++)
					   for(int j=0 ; j<vals.get(i).getKey() ; j++)
						   GantDiagram.add("P"+vals.get(j).getValue() + " " );
				   
					//System.out.println("\npid  arrival brust  complete turn waiting");
					for(int i=0 ;i<numOfLines;i++){
						System.out.println(PID[i]+"\t"+AT[i]+"\t"+BT[i]+"\t"+FinishTime[i]+"\t"+TurnAroundTime[i]+"\t"+waitTime[i]+"\t"+WTA[i]);
					}
					for(int i=0 ; i<totalBurst ; i++)
						System.out.print(GantDiagram.get(i) + " ");
				   				 
			 }
    
   }

   
   
   
   
   
   
   
   
   
   
   
