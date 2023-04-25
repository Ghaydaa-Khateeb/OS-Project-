
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class MainController extends UI  implements Initializable {
	private Random100 r;
	private int nOfLines;
	//@FXML private Label myDiagram;
	@FXML private StackedBarChart sbc;
	@FXML private Label myMessage;
	@FXML private TableView<Process> table;
	@FXML private TableColumn<Process,Integer> PID;
	@FXML private TableColumn<Process,Integer> AT;
	@FXML private TableColumn<Process,Integer> BT;
	@FXML private TableColumn<Process,Integer> Priority;
	@FXML private TableColumn<Process,Integer> FT;
	@FXML private TableColumn<Process,Integer> TA;
	@FXML private TableColumn<Process,Integer> wait;
	@FXML private TableColumn<Process,Double> WTA;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
		
	public void generateRandom(ActionEvent e)throws IOException{
	    r = new Random100();
		r.run();
		myMessage.setText("   The Random values is Generated successfully");
	}
	public void generateDiagramSJF(ActionEvent e) throws IOException {
		ArrayList<Integer> PID1      = new ArrayList <Integer>();
		ArrayList<Integer> AT1       = new ArrayList <Integer>();
		ArrayList<Integer> BT1       = new ArrayList <Integer>();
		ArrayList<Integer> FT1       = new ArrayList <Integer>();
		ArrayList<Integer> Priority1 = new ArrayList <Integer>();
		ArrayList<Integer> TA1       = new ArrayList <Integer>();
		ArrayList<Integer> wait1     = new ArrayList <Integer>();
		ArrayList<Double> WTA1       = new ArrayList  <Double>();  
		ShortestJobFirst sjf = new ShortestJobFirst(r.PID , r.arrivalTime , r.burstTime , r.numOfLines);
		sjf.makeTable();
		String res = "";
		for(int i=0 ; i<sjf.getGantDiagram().size(); i++)
			res += (sjf.getGantDiagram().get(i));
		for(int i=0 ; i<r.numOfLines ; i++) {
			PID1.add(sjf.getPID()[i]);
			AT1.add(sjf.getAT()[i]);
			BT1.add(sjf.getBT()[i]);
			FT1.add(sjf.getFinishTime()[i]);
			Priority1.add(r.Priority[i]);
			TA1.add(sjf.getTurnAroundTime()[i]);
			wait1.add(sjf.getWaitTime()[i]);
			WTA1.add(sjf.getWTA()[i]);
			
		}
		//myDiagram.setText(res);
		myMessage.setText("   Shortest Job First");
		ObservableList<Process> list1 =  FXCollections.observableArrayList();
		for(int i=0 ; i<r.numOfLines ; i++)
			list1.add(new Process(PID1.get(i),AT1.get(i),BT1.get(i),Priority1.get(i),FT1.get(i),TA1.get(i),wait1.get(i),WTA1.get(i)));
		
		PID.setCellValueFactory(new PropertyValueFactory<Process,Integer>("PID"));
		AT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime"));
		BT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("burstTime"));
		Priority.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Priority"));
		FT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("FinishTime"));
		TA.setCellValueFactory(new PropertyValueFactory<Process,Integer>("TurnAroundTime"));
		wait.setCellValueFactory(new PropertyValueFactory<Process,Integer>("waitTime"));
		WTA.setCellValueFactory(new PropertyValueFactory<Process,Double>("WTA"));
		table.setItems(list1);

	}
	public void generateDiagramSRTF(ActionEvent e) throws IOException {
		ArrayList<Integer> PID1      = new ArrayList <Integer>();
		ArrayList<Integer> AT1       = new ArrayList <Integer>();
		ArrayList<Integer> BT1       = new ArrayList <Integer>();
		ArrayList<Integer> FT1       = new ArrayList <Integer>();
		ArrayList<Integer> Priority1 = new ArrayList <Integer>();
		ArrayList<Integer> TA1       = new ArrayList <Integer>();
		ArrayList<Integer> wait1     = new ArrayList <Integer>();
		ArrayList<Double> WTA1       = new ArrayList  <Double>(); 	
		ShortestRemainingTimeFirst SRTF = new ShortestRemainingTimeFirst(r.PID , r.arrivalTime , r.burstTime , r.numOfLines);
		SRTF.makeTable();
		String res = "";
		for(int i=0 ; i<SRTF.getGantDiagram().size(); i++)
			res += (SRTF.getGantDiagram().get(i));
		for(int i=0 ; i<r.numOfLines ; i++) {
			PID1.add(SRTF.getPID()[i]);
			AT1.add(SRTF.getAT()[i]);
			BT1.add(SRTF.getBT()[i]);
			FT1.add(SRTF.getFinishTime()[i]);
			Priority1.add(r.Priority[i]);
			TA1.add(SRTF.getTurnAroundTime()[i]);
			wait1.add(SRTF.getWaitTime()[i]);
			WTA1.add(SRTF.getWTA()[i]);
			
		}		
		//myDiagram.setText(res);
		myMessage.setText("   Shortest Remaining Time First");
		ObservableList<Process> list2 =  FXCollections.observableArrayList();
		for(int i=0 ; i<r.numOfLines ; i++)
			list2.add(new Process(PID1.get(i),AT1.get(i),BT1.get(i),Priority1.get(i),FT1.get(i),TA1.get(i),wait1.get(i),WTA1.get(i)));
		PID.setCellValueFactory(new PropertyValueFactory<Process,Integer>("PID"));
		AT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime"));
		BT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("burstTime"));
		Priority.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Priority"));
		FT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("FinishTime"));
		TA.setCellValueFactory(new PropertyValueFactory<Process,Integer>("TurnAroundTime"));
		wait.setCellValueFactory(new PropertyValueFactory<Process,Integer>("waitTime"));
		WTA.setCellValueFactory(new PropertyValueFactory<Process,Double>("WTA"));
		table.setItems(list2);
		System.out.println();
		for(int i=0 ; i<r.numOfLines ;i++)
			System.out.println(PID1.get(i) + " " + AT1.get(i) + " " + BT1.get(i) +" " + FT1.get(i) +" " + Priority1.get(i) +" " + TA1.get(i) +" " + wait1.get(i)+" " + WTA1.get(i));



		
	}
	public void generateDiagramEP(ActionEvent e) throws IOException {
		ArrayList<Integer> PID1      = new ArrayList <Integer>();
		ArrayList<Integer> AT1       = new ArrayList <Integer>();
		ArrayList<Integer> BT1       = new ArrayList <Integer>();
		ArrayList<Integer> FT1       = new ArrayList <Integer>();
		ArrayList<Integer> Priority1 = new ArrayList <Integer>();
		ArrayList<Integer> TA1       = new ArrayList <Integer>();
		ArrayList<Integer> wait1     = new ArrayList <Integer>();
		ArrayList<Double> WTA1       = new ArrayList  <Double>(); 
		ExplicitPriority EP = new ExplicitPriority(r.PID , r.arrivalTime , r.burstTime,r.Priority , r.numOfLines);
		EP.makeTable();
		String res = "";
		for(int i=0 ; i<EP.getGantDiagram().size(); i++)
			res += (EP.getGantDiagram().get(i));
		for(int i=0 ; i<r.numOfLines ; i++) {
			PID1.add(EP.getPID()[i]);
			AT1.add(EP.getAT()[i]);
			BT1.add(EP.getBT()[i]);
			FT1.add(EP.getFinishTime()[i]);
			Priority1.add(r.Priority[i]);
			TA1.add(EP.getTurnAroundTime()[i]);
			wait1.add(EP.getWaitTime()[i]);
			WTA1.add(EP.getWTA()[i]);
			
		}		
		//myDiagram.setText(res);
		myMessage.setText("  Eplicit Priority with preemption");
		ObservableList<Process> list3 =  FXCollections.observableArrayList();
		for(int i=0 ; i<r.numOfLines ; i++)
			list3.add(new Process(PID1.get(i),AT1.get(i),BT1.get(i),Priority1.get(i),FT1.get(i),TA1.get(i),wait1.get(i),WTA1.get(i)));
		PID.setCellValueFactory(new PropertyValueFactory<Process,Integer>("PID"));
		AT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime"));
		BT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("burstTime"));
		Priority.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Priority"));
		FT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("FinishTime"));
		TA.setCellValueFactory(new PropertyValueFactory<Process,Integer>("TurnAroundTime"));
		wait.setCellValueFactory(new PropertyValueFactory<Process,Integer>("waitTime"));
		WTA.setCellValueFactory(new PropertyValueFactory<Process,Double>("WTA"));
		table.setItems(list3);


		
	}
	public void generateDiagramMP(ActionEvent e) throws IOException {
		ArrayList<Integer> PID1      = new ArrayList <Integer>();
		ArrayList<Integer> AT1       = new ArrayList <Integer>();
		ArrayList<Integer> BT1       = new ArrayList <Integer>();
		ArrayList<Integer> FT1       = new ArrayList <Integer>();
		ArrayList<Integer> Priority1 = new ArrayList <Integer>();
		ArrayList<Integer> TA1       = new ArrayList <Integer>();
		ArrayList<Integer> wait1     = new ArrayList <Integer>();
		ArrayList<Double> WTA1       = new ArrayList  <Double>(); 
		MultiProgrammed MP = new MultiProgrammed(r.PID , r.arrivalTime , r.burstTime , r.numOfLines);
		MP.makeTable();
		for(int i=0 ; i<r.numOfLines ; i++) {
			PID1.add(MP.getPID()[i]);
			AT1.add(MP.getAT()[i]);
			BT1.add(MP.getBT()[i]);
			FT1.add(MP.getFinishTime()[i]);
			Priority1.add(r.Priority[i]);
			TA1.add(MP.getTurnAroundTime()[i]);
			wait1.add(MP.getWaitTime()[i]);
			WTA1.add(MP.getWTA()[i]);
			
		}		
		//myDiagram.setText("");
		myMessage.setText("   MultiProgrammed with I/O waiting");
		ObservableList<Process> list4 =  FXCollections.observableArrayList();
		for(int i=0 ; i<r.numOfLines ; i++)
			list4.add(new Process(PID1.get(i),AT1.get(i),BT1.get(i),Priority1.get(i),FT1.get(i),TA1.get(i),wait1.get(i),WTA1.get(i)));
		PID.setCellValueFactory(new PropertyValueFactory<Process,Integer>("PID"));
		AT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime"));
		BT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("burstTime"));
		Priority.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Priority"));
		FT.setCellValueFactory(new PropertyValueFactory<Process,Integer>("FinishTime"));
		TA.setCellValueFactory(new PropertyValueFactory<Process,Integer>("TurnAroundTime"));
		wait.setCellValueFactory(new PropertyValueFactory<Process,Integer>("waitTime"));
		WTA.setCellValueFactory(new PropertyValueFactory<Process,Double>("WTA"));
		table.setItems(list4);

		
	}





}
