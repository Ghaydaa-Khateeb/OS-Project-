
import java.util.ArrayList;

public class ShortestJobFirst {
	//int PID[] , int AT[] , int BT[] , int numOfLines
	private int PID[];
	private int AT[];
	private int BT[];
	private int numOfLines;
	private int FinishTime[];
	private int waitTime[];
	private double WTA[];
	private int TurnAroundTime[];
	private  ArrayList<String> GantDiagram;
    public ShortestJobFirst() {
    	
    }
	public ShortestJobFirst(int[] pID, int[] aT, int[] bT, int numOfLines) {
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
	public ArrayList<String> getGantDiagram() {
		return GantDiagram;
	}
	public void setGantDiagram(ArrayList<String> gantDiagram) {
		GantDiagram = gantDiagram;
	}
	public void makeTable() {
		   System.out.println(" <---- Shortest Job First ---->");
		   boolean isFinished[] = new boolean [numOfLines] ;
		   FinishTime           = new int[numOfLines];
		   waitTime             = new int [numOfLines];
		   WTA                  = new double [numOfLines];
		   TurnAroundTime       = new int[numOfLines];
		   int numOfProcesses = 0 , totalBurst = 0 , startTime = 0 , st = 0;
		   for(int i=0 ; i<numOfLines ; i++)
			   totalBurst += BT[i];
		    GantDiagram = new ArrayList <String>();//we'll connect it with the interface  
		   
		   while(true) {
			   int idxOfFirstOne = 1000 , min = 1000;
			   if(numOfProcesses == numOfLines)
				   break;
			   for(int i = 0 ; i<numOfLines ; i++) {
				   if(AT[i] <= startTime && BT[i] < min && isFinished[i] == false) {
					   min = BT[i];
					   idxOfFirstOne = i;
				   }
			   }
			   if(idxOfFirstOne == 1000) {
				   startTime ++ ;
			   }
			   else {
				   startTime += BT[idxOfFirstOne];
				   FinishTime[idxOfFirstOne] = startTime;
				   TurnAroundTime[idxOfFirstOne] = FinishTime[idxOfFirstOne] - AT[idxOfFirstOne];
				   waitTime[idxOfFirstOne] = TurnAroundTime[idxOfFirstOne] - BT[idxOfFirstOne]; 
				   WTA[idxOfFirstOne] = TurnAroundTime[idxOfFirstOne] / (double)(BT[idxOfFirstOne]);
				   isFinished[idxOfFirstOne] = true;
				   numOfProcesses++;
				   for(int j=st ; j < BT[idxOfFirstOne] + st ; j++) {
					   GantDiagram.add("P" + (idxOfFirstOne + 1)) ;
				   }
				   st += BT[idxOfFirstOne];
			   }
		   }
			System.out.println("\npid  arrival brust  complete turn waiting");
			for(int i=0 ;i<numOfLines;i++){
				System.out.println(PID[i]+"\t"+AT[i]+"\t"+BT[i]+"\t"+FinishTime[i]+"\t"+TurnAroundTime[i]+"\t"+waitTime[i]+"\t"+WTA[i]);
			}
			for(int i=0 ; i<totalBurst ; i++)
				System.out.print(GantDiagram.get(i) + " ");
	}

}
