import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;

public class MultiProgrammed {
	private int PID[];
	private int AT[];
	private int BT[];
	private int numOfLines;
	private int FinishTime[];
	private int waitTime[];
	private double WTA[];
	private int TurnAroundTime[];
	ArrayList <Pair<Double , Integer> > finished1;
	public MultiProgrammed(int[] pID, int[] aT, int[] bT, int numOfLines) {
		super();
		PID = pID;
		AT = aT;
		BT = bT;
		this.numOfLines = numOfLines;
	}
	public int[] getPID() {
		return PID;
	}
	public void setPID(int[] pID) {
		PID = pID;
	}
	public int[] getAT() {
		return AT;
	}
	public void setAT(int[] aT) {
		AT = aT;
	}
	public int[] getBT() {
		return BT;
	}
	public void setBT(int[] bT) {
		BT = bT;
	}
	public int getNumOfLines() {
		return numOfLines;
	}
	public void setNumOfLines(int numOfLines) {
		this.numOfLines = numOfLines;
	}
	public int[] getFinishTime() {
		return FinishTime;
	}
	public void setFinishTime(int[] finishTime) {
		FinishTime = finishTime;
	}
	public int[] getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int[] waitTime) {
		this.waitTime = waitTime;
	}
	public double[] getWTA() {
		return WTA;
	}
	public void setWTA(double[] wTA) {
		WTA = wTA;
	}
	public int[] getTurnAroundTime() {
		return TurnAroundTime;
	}
	public void setTurnAroundTime(int[] turnAroundTime) {
		TurnAroundTime = turnAroundTime;
	}
	public void makeTable() {
    	if(numOfLines > 10)
    		numOfLines = 10;
		   boolean isFinished[]    = new boolean [numOfLines] ;
		   int arrives[]           = new int [numOfLines];
		   int differences[]       = new int [numOfLines];
		   FinishTime              = new int[numOfLines];
		   waitTime                = new int [numOfLines];
		   WTA                     = new double [numOfLines];
		   TurnAroundTime          = new int[numOfLines];
		   double BurstTime []        = new double[numOfLines];
		   double numOfProcesses[] = new double[11];
		   for(int i=0 ; i<numOfLines ; i++) {
			   BurstTime[i] = BT[i];
		   }
		   int totalBurst = 0 , startTime = 0 , st = 0;
		   for(int i=0 ; i<numOfLines ; i++)
			   totalBurst += BT[i];
		   ArrayList<String>GantDiagram= new ArrayList <String>();//we'll connect it with the interface  
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
		   numOfProcesses[0] = 0;
		   numOfProcesses[1] = 0.5;
		   numOfProcesses[2] = 0.75;
		   numOfProcesses[3] = 0.875;
		   numOfProcesses[4] = 0.9375;
		   numOfProcesses[5] = 0.96875;
		   numOfProcesses[6] = 0.984375;
		   numOfProcesses[7] = 0.9921875;
		   numOfProcesses[8] = 0.99609375;
		   numOfProcesses[9] = 0.998046875;
		   numOfProcesses[10]= 0.999023438;
		   ArrayList <Pair<Double , Integer> > vals= new ArrayList<Pair<Double , Integer > >();
		   ArrayList <Pair<Integer , Integer> > pair= new ArrayList<Pair<Integer , Integer > >();
		   finished1= new ArrayList<Pair<Double , Integer > >();
		   Set <String> uniqueArrived = new HashSet<String>();
		   int  min = 1000 ,  minIndx = 0 , abs = -1 , id = 0 ,  timer = 0 , times = 0 , counter = 0 , idx = 0 , siz = 0;
		   boolean flag = true;
		   for(int i=0 ; i<numOfLines - 1 ; i++)
			   differences[i] = arrives[i + 1] - arrives[i];
		   differences[numOfLines - 1] = 0;
		   boolean isArrived [] =  new boolean[lastArrived];
		   for(int i=0 ; i< lastArrived ; i++) {
			   for(int j=0 ; j<numOfLines ; j++)
				   if(i == AT[j]){
					   isArrived[i] = true;
				   }
		   }
		 
		   for(int i=0 ; i<numOfLines  ; i++)
			   uniqueArrived.add(arrives[i]+"");
		   String unique[] = new String[uniqueArrived.size()];
		   for(int i=0 ; i<numOfLines ; i++)
		   	System.out.println(AT[i]);
		   System.out.println("----------------");
		   uniqueArrived.toArray(unique);
		   for(int i=0 ; i<unique.length  - 1; i++) {
			   pair.add(new Pair<Integer , Integer>(Integer.parseInt(unique[i]),Integer.parseInt(unique[i + 1])));
		   }
		   for(int i=0 ; i<pair.size() ; i++)
			   System.out.println( pair.get(i));
		   System.out.println("-----8-8-8-");
		   for(int i=0 ; i<pair.size() ; i++) {
			   if(i == pair.size() - 1) idx = 1;
			   for(int j=pair.get(i).getKey() ; j < pair.get(i).getValue()  ; j++) {
				   for(int k=0 ; k<numOfLines ; k++)
					   if(arrives[k] == j)
						   vals.add(new Pair<Double , Integer>(BurstTime[k] , PID[k]));
			   }	
			   siz = 0;
			   		for(int i1 =0 ; i1<vals.size() ; i1++ ) {
			   				if(vals.get(i1).getKey() > 0.0)
			   					siz++;
			   			}

				   for(int l=0 ; l<vals.size() ; l++)
					   if(vals.get(l).getKey() - ((numOfProcesses[siz]/(double)(siz)) * ( pair.get(i).getValue() - pair.get(i).getKey())) > 0.0 && isFinished[vals.get(l).getValue() - 1] == false)
						   vals.set(l, new Pair<Double , Integer>(vals.get(l).getKey() - ((numOfProcesses[siz]/(double)(siz)) * ( pair.get(i).getValue() - pair.get(i).getKey())) , PID[l]));
					   else if(isFinished[vals.get(l).getValue() - 1] = false) {
						   finished1.add(new Pair<Double , Integer>( (double)pair.get(i).getKey() , vals.get(l).getValue()));
						   vals.set(l, new Pair<Double , Integer>(0.0 , vals.get(l).getValue()));
						   isFinished[vals.get(l).getValue() - 1] = true;
						   siz--;

						   }

		   }
		   boolean flagg = true;
		   double last = lastArrived;
		   System.out.println("SIZE = " + siz);
		   while(siz != 0) {
				for(int k=0 ; k<numOfLines ; k++)
					if(arrives[k] == pair.get(pair.size() - 1).getValue() && flagg) {
						vals.add(new Pair<Double , Integer>(BurstTime[k] , PID[k]));
						flagg = false;
					}
		   		siz = 0;
		   		for(int i=0 ; i<vals.size() ; i++) {
		   			if(vals.get(i).getKey() > 0.0 )
		   				siz++;
		   			System.out.println(vals.get(i));
		   		}
			   double mn = 1000;
			   System.out.println(vals.size());
			   for(int i=0 ; i<vals.size() ; i++)
				   if(vals.get(i).getKey() <= mn && vals.get(i).getKey() > 0.0) {
					   mn = vals.get(i).getKey();
				   }
			   if(mn != 1000) {
				   double elaps = mn / ((double)numOfProcesses[siz]/(double)siz); 
				   last += elaps;
				   for(int l=0 ; l<vals.size() ; l++)
					   if(vals.get(l).getKey() - mn > 0.0 && isFinished[vals.get(l).getValue() - 1] == false) {
						   vals.set(l, new Pair<Double , Integer>(vals.get(l).getKey() - mn , vals.get(l).getValue()));
					   }
					   else if(isFinished[vals.get(l).getValue() - 1] == false) {
						   finished1.add(new Pair<Double , Integer>(last, vals.get(l).getValue()));
						   vals.set(l, new Pair<Double , Integer>(0.0 , vals.get(l).getValue()));
						   isFinished[vals.get(l).getValue() - 1] = true;
					   }
			   		}  
		   }
			 	  
		   	//for(int i=0 ; i<uniqueArrived.size() ; i++)
		   System.out.println("<----- FINISHED ----->");
		   for(int i=0 ; i<finished1.size() ; i++)
			   System.out.println(finished1.get(i));

    }

	

}
