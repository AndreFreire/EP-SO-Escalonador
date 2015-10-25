import java.util.Collections;
import java.util.LinkedList;

public class BCP {
	LinkedList<Process> blocked;
	LinkedList<Process> ready;
	Process executing;
	int quantum;
	public BCP() {
		this.quantum = Util.readQuantum();
		this.ready = Util.readProcess();
		blocked = new LinkedList<Process>();
	}
	public void executeProcesses(){
		String command;
		int count = 0;
		boolean block;
		boolean finish;
		while(this.ready.size() > 0 || this.blocked.size() > 0){
			organizeProcesses();
			block = false;
			finish = false;
			if(this.ready.size() > 0){
				this.executing = ready.removeFirst();
				Util.writeLog("Executando " + this.executing.getName());
				for(count = 1; count <= this.quantum; count++){
					command = this.executing.commands.removeFirst();
					if(command.indexOf("X=") > -1){
						this.executing.setX(command.substring(2));
					}else{
						if(command.indexOf("Y=") > -1){
							this.executing.setY(command.substring(2));
						}else
							if(command.equals("E/S")){
								blockProcess(count);
								block = true;
								break;
							}else{
								if(command.equals("SAIDA")){
									finishProcess(count);
									finish = true;
									break;
								}
							}
					}
				}
				if(!finish && !block){
					interruptProcess(count-1);
					this.ready.add(this.executing);
				}
			}else{
				decrementBlocked();
			}			
		}
	}
	private void organizeProcesses() {
		Collections.sort(this.ready);
		if(this.ready.size() > 0 && this.ready.getFirst().getCredit() == 0){
			for(Process process: this.ready){
				process.setCredit(process.getPriority());
			}
		}
	}
	private void finishProcess(int count) {
		decrementBlocked();
		Util.writeLog("Interrompendo " + this.executing.getName() + " após " + count + " instruções");
		Util.writeLog(this.executing.getName() + " terminado. X=" + this.executing.getX() + ". Y=" + this.executing.getY());
	}
	private void interruptProcess(int count) {
		decrementCredit();
		decrementBlocked();
		Util.writeLog("Interrompendo " + this.executing.getName() + " após " + count + " instruções");	
	}
	private void decrementBlocked() {
		LinkedList<Process> copy = new LinkedList<Process>(this.blocked);
		for(Process process : this.blocked){
			process.setBlockedTime(process.getBlockedTime() -1 );
			if(process.getBlockedTime() == 0){
				this.ready.add(process);
				copy.remove(process);
			}
		}
		this.blocked = copy;
	}
	public void blockProcess(int count){
		Util.writeLog("E/S iniciada em " + this.executing.getName());
		interruptProcess(count);
		this.executing.setBlockedTime(2);
		this.blocked.add(this.executing);
	}
	public void decrementCredit(){
		this.executing.setCredit(this.executing.getCredit()-1);
	}
}
