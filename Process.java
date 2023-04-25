import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Process extends Random100 {
	private final SimpleIntegerProperty PID;
	private final SimpleIntegerProperty arrivalTime ;
	private final SimpleIntegerProperty burstTime;
	private final SimpleIntegerProperty Priority;
	private final SimpleIntegerProperty FinishTime;
	private final SimpleIntegerProperty TurnAroundTime;
	private final SimpleIntegerProperty waitTime;
	private final SimpleDoubleProperty  WTA;
	public Process(Integer PID, Integer arrivalTime, Integer burstTime,
			Integer Priority, Integer FinishTime, Integer TurnAroundTime,
			Integer waitTime, Double WTA) {
		super();
		this.PID              = new SimpleIntegerProperty(PID);
		this.arrivalTime      = new SimpleIntegerProperty(arrivalTime);
		this.burstTime        = new SimpleIntegerProperty(burstTime);
		this.Priority         = new SimpleIntegerProperty(Priority);
		this.FinishTime       = new SimpleIntegerProperty(FinishTime);
		this.TurnAroundTime   = new SimpleIntegerProperty(TurnAroundTime);
		this.waitTime         = new SimpleIntegerProperty(waitTime);
		this.WTA              = new SimpleDoubleProperty (WTA);
	}
	public Integer getPID() {
		return PID.get();
	}
	public Integer getArrivalTime() {
		return arrivalTime.get();
	}
	public Integer getBurstTime() {
		return burstTime.get();
	}
	public Integer getPriority() {
		return Priority.get();
	}
	public Integer getFinishTime() {
		return FinishTime.get();
	}
	public Integer getTurnAroundTime() {
		return TurnAroundTime.get();
	}
	public Integer getWaitTime() {
		return waitTime.get();
	}
	public Double getWTA() {
		return WTA.get();
	}


}
