import java.util.ArrayList;

import javafx.util.Pair;

public class ExplicitPriority {
	private int PID[];
	private int AT[];
	private int BT[];	
	private int Priority[];	
	private int numOfLines;
	public ExplicitPriority(int[] pID, int[] aT, int[] bT, int[] priority, int numOfLines) {
		super();
		PID = pID;
		AT = aT;
		BT = bT;
		Priority = priority;
		this.numOfLines = numOfLines;
	}
	private int FinishTime[];
	private int waitTime[];
	private double WTA[];
	private int TurnAroundTime[];
	private  ArrayList<String> GantDiagram;
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
	public int[] getPriority() {
		return Priority;
	}
	public void setPriority(int[] priority) {
		Priority = priority;
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
	public ArrayList<String> getGantDiagram() {
		return GantDiagram;
	}
	public void setGantDiagram(ArrayList<String> gantDiagram) {
		GantDiagram = gantDiagram;
	}
	
	public void makeTable() {
		 boolean isFinished[]       = new boolean [numOfLines] ;
		 int arrives[]              = new int [numOfLines];
		 int differences[]          = new int [numOfLines];
		  FinishTime                = new int[numOfLines];
		  waitTime                  = new int [numOfLines];
		   WTA                      = new double [numOfLines];
		  TurnAroundTime            = new int[numOfLines];
		  int P[]                   = new int[numOfLines];
		  int BurstTime []          = new int[numOfLines];
		   for(int i=0 ; i<numOfLines ; i++) {
			   BurstTime[i] = BT[i];
		   }
		   int numOfProcesses = 0 , totalBurst = 0 , startTime = 0 , st = 0;
		   for(int i=0 ; i<numOfLines ; i++)
			   totalBurst += BT[i];
		 for(int i=0 ; i<numOfLines ; i++)
		   P[i] = Priority[i];
		    GantDiagram =  new ArrayList <String>() ;//we'll connect it with the interface  
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
		   ArrayList <Pair<Integer , Integer> > vals= new ArrayList<Pair<Integer , Integer > >();
		   ArrayList <Pair<Integer , Integer> > vals0= new ArrayList<Pair<Integer , Integer > >();
		     ArrayList <Pair<Integer , Integer> > FA= new ArrayList<Pair<Integer , Integer > >();
		   int  min = 1000 ,  minIndx = 0 , abs = -1 , id = 0 ,  timer = 0 , times = 0 , counter = 0,bt = 0;
	   boolean flag = true;
	   for(int i=0 ; i<numOfLines - 1 ; i++)
		   differences[i] = arrives[i + 1] - arrives[i];
	   differences[numOfLines - 1] = 0;
	   lastArrived ++ ;
	   boolean isArrived [] =  new boolean[lastArrived];
	   for(int i=0 ; i< lastArrived ; i++) {
		   for(int j=0 ; j<numOfLines ; j++)
			   if(i == AT[j]){
				   isArrived[i] = true;
			   }
	   }
	   while(true) {
		      for(int i=0 ; i<vals0.size() ; i++)
		    	  System.out.print(vals0.get(i) + " ");
		      System.out.println();
   	  	  if(counter == numOfLines )
   	  			break;
   	  	  else
   	  			counter = 0;
   		  min = 1000;
   		  if(timer < lastArrived)
   			  if(isArrived[timer] == true) {
   				  for(int k=0 ; k < numOfLines ; k ++)
   					  if(AT[k] == timer) {
   						  vals.add(new Pair<Integer , Integer>(P[k] , PID[k]));
   						  vals0.add(new Pair<Integer , Integer>(BurstTime[k] , PID[k]));
   					  }
   				  isArrived[timer] = false;
   		  }
   		  for(int i=0 ; i<vals0.size(); i++) {
   			  if(vals0.get(i).getKey() == -1)
   				  counter++;
   		  }
   		  for(int i=0 ; i<vals.size(); i++)
   			  if(vals.get(i).getKey()  <  min  &&vals0.get(i).getKey() != -1) {
   				  min = vals.get(i).getKey();
   				  bt  = vals0.get(i).getKey();
   				  minIndx = i ;
   				  id = vals.get(i).getValue();
   			  }
		   		for(int u=0 ; u<numOfLines ; u++)
		   			if(P[u] == min && id == PID[u]) {
		   				abs = u;
		   			}
   		  times = 0;
   		  if(min != 1000) {
   			  for(int j=0 ; j<bt ; j++) {
   				  GantDiagram.add("P"+id);
   				  times++;
   				  timer++;
   				  if(timer < lastArrived) {
   					  if(isArrived[timer] == true) {
   						  for(int k=0 ; k < numOfLines ; k ++)
   							  if(AT[k] == timer) {
   								  vals.add(new Pair<Integer , Integer>(P[k] , PID[k]));
   								  vals0.add(new Pair<Integer , Integer>(BurstTime[k] , PID[k]));
   							  }    						  
   						  isArrived[timer] = false;
   						  break;
   			  			}
   			  		}
   			  	}    			 
   		  }else
   			  timer++;
   		  if(min != 1000) {
   			  	bt -= times;
	   				if(bt > 0) {
	   					vals0.set(minIndx, new Pair<Integer , Integer>(bt ,id));
	   					BurstTime[abs] = bt;
	   				}
	   				else {
	   					vals0.set(minIndx, new Pair<Integer , Integer>(-1 , id));
	   					FA.add(new Pair<Integer,Integer>(id , timer));
	   					BurstTime[abs] = -1;
	   					bt = 1000;
	   				}
   		  	}	    		  
   		  for(int i=0 ; i<vals.size() ; i++) {
   				  vals.set(i, new Pair<Integer , Integer>(vals.get(i).getKey() - 1 , vals.get(i).getValue()));
   		  }
	   }
	     boolean finish[] = new boolean[numOfLines];
      /* for(int i = GantDiagram.size() - 1 ; i >= 0 ; i--) {
       	int x =Integer.valueOf(GantDiagram.get(i).substring(1));
       	if(finish[x - 1] == false) {
       		finish[x - 1] = true;
       	}
       }*/
       for(int i=0 ; i<FA.size() ; i++) {
       	FinishTime[FA.get(i).getKey() - 1] = FA.get(i).getValue();
       	TurnAroundTime[FA.get(i).getKey() - 1] = FinishTime[FA.get(i).getKey() - 1] - AT[FA.get(i).getKey() - 1];
       	waitTime[FA.get(i).getKey() - 1] = TurnAroundTime[FA.get(i).getKey() - 1] - BT[FA.get(i).getKey() - 1];
       	WTA[FA.get(i).getKey() - 1] = TurnAroundTime[FA.get(i).getKey() - 1] /(double) BT[FA.get(i).getKey() - 1];
       	//System.out.println(FA.get(i));
       }
		System.out.println("\npid  arrival brust Priority  complete turn waiting    WTA");
		for(int i=0 ;i<numOfLines;i++){
			System.out.println(PID[i]+"\t"+AT[i]+"\t"+BT[i]+"\t"+Priority[i] + "\t" +FinishTime[i]+"\t"+TurnAroundTime[i]+"\t"+waitTime[i]+"\t"+WTA[i]);
		}
		for(int i=0 ; i<GantDiagram.size() ; i++)
			System.out.print(GantDiagram.get(i) + " ");


	}
	

}
