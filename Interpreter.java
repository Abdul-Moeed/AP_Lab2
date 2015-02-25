package ap_lab2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abdul Moeed
 */
public class Interpreter {

    Map <String, Integer> variables;
    
    public static void main(String[] args) throws IOException {
        Reader fr = new Reader();
        fr.read_file("test.txt");
        //fr.dump_file();
        Interpreter intr = new Interpreter();
        intr.variables = new HashMap<String, Integer>();
        intr.exec_program(fr);   
    }
    
    public void exec_program(Reader fr) {
        for(int i=0;i<fr.total_lines;i++) {
            exec_ins(fr.instructions.get(i));
        }
    }
    
    public void exec_ins(String command) {
        if(check_syntax(command) == -1){
            return;
        }
        
        String[] tokens = command.split(" ");
        if (tokens[0].equalsIgnoreCase("Let")){
            variables.put(tokens[1], Integer.parseInt(tokens[3]));
        }
        else if(tokens[0].equalsIgnoreCase("Print")){
            System.out.println(variables.get(tokens[1]));
        }
        else{
            if("+".equals(tokens[3])){
                variables.put(tokens[0], variables.get(tokens[0]) + Integer.parseInt(tokens[4]));
            }
            if("-".equals(tokens[3])){
                variables.put(tokens[0], variables.get(tokens[0]) - Integer.parseInt(tokens[4]));
            }
            if("/".equals(tokens[3])){
                variables.put(tokens[0], variables.get(tokens[0]) / Integer.parseInt(tokens[4]));
            }
            if("*".equals(tokens[3])){
                variables.put(tokens[0], variables.get(tokens[0]) * Integer.parseInt(tokens[4]));
            }
            else{
            }
        }
    }
    static int check_syntax(String command) {
        String[] tokens = command.split(" ");
        if (tokens[0].equalsIgnoreCase("Let")) {
            if(Character.isDigit(tokens[1].charAt(0))) {
                return -1;
            }
            if(!"=".equals(tokens[2])) {
                return -1;
            }
        }
        else if (tokens[0].equalsIgnoreCase("Print")) {
        }
        else {}
        return 1;
    }
}
