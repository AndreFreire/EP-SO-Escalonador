import java.util.LinkedList;

public class Process implements Comparable<Process>{
	String name;
	int priority;
	int credit;
	int blockedTime;
	LinkedList<String> commands;
	String X;
	String Y;
	
	
	public Process(String name, int priority, LinkedList<String> commands, int credit) {
		super();
		this.name = name;
		this.priority = priority;
		this.commands = commands;
		this.credit = credit;
		this.X = "";
		this.Y = "";
		Util.writeLog("Carregando " + name);				
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public LinkedList<String> getCommands() {
		return commands;
	}
	public void setCommands(LinkedList<String> commands) {
		this.commands = commands;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getX() {
		return X;
	}
	public void setX(String x) {
		X = x;
	}
	public String getY() {
		return Y;
	}
	public void setY(String y) {
		Y = y;
	}
	public int getBlockedTime() {
		return blockedTime;
	}

	public void setBlockedTime(int blockedTime) {
		this.blockedTime = blockedTime;
	}
	@Override
	public int compareTo(Process process){
		return process.getCredit() - this.getCredit();
	}
	
}
