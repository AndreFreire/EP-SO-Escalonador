import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Collections;

public class Util {
	public static LinkedList<Process> readProcess(){
		LinkedList<Process> process = new LinkedList<Process>();		
		try { 
			FileReader priorityFile = new FileReader("processos/prioridades.txt");
			BufferedReader readPriority = new BufferedReader(priorityFile);
			 

			for(int i = 1; i <= 10; i++){
				LinkedList <String> commands = new LinkedList<String>();
				FileReader currentProcess = new FileReader("processos/" + String.format("%02d", i) + ".txt");
				BufferedReader readProcess = new BufferedReader(currentProcess);
				String name = readProcess.readLine();
				String line = readProcess.readLine();
				while (line != null) {
					commands.add(line);
					line = readProcess.readLine();
				}
				int priority = Integer.parseInt(readPriority.readLine());
				process.add(new Process(name, priority, commands, priority));
				readProcess.close();
			}
			Collections.sort(process);
			priorityFile.close(); 
			return process;
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
			return null;
		}
	}

	public static int readQuantum(){
		try { 
			FileReader quantumFile = new FileReader("processos/quantum.txt");
			BufferedReader readQuantum = new BufferedReader(quantumFile);
			String line = readQuantum.readLine();
			quantumFile.close(); 
			return Integer.parseInt(line);
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
			return -1;
		}
	}
}
