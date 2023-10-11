/**
 * Take Input
 *      Redirect to appropriate location
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class UserInterface {

    private Scanner scanner;
    private ArrayList<Event> schedule;
    private InputOutput IO;
    
    UserInterface(Scanner scanner){
        this.scanner = scanner;
        this.IO = new InputOutput();
        this.schedule = IO.inputSchedule();
        Collections.sort(schedule);
    }
    
    public void start(){
    String input = "";;
    while(!input.equals("Q")){
        displayCommands();
        input = scanner.nextLine();
        input = input.trim();
        input = input.toUpperCase();
        switch(input){
            case "D":
                displaySchedule(schedule);
                break;
            case "W":
                System.out.println("Called Add Work Schedule");
                break;
            case "S":
                System.out.println("Called Add School Work");
                break;
            case "O":
                System.out.println("Called Other Tasks");
                break;
            case "E":
                System.out.println("Called Edit Task");
                break;
            case "C":
                System.out.println("Called Completed");
                break;
            case "Q":
                //call function
                break;
            default:
                break;
        }
    }
    IO.outputSchedule(schedule);
    IO.close();
    }

    public void displayCommands(){
        System.out.println("Enter Command:");
        System.out.println(" -(D) Display Schedule");
        System.out.println(" -(W) Add Work Shifts");
        System.out.println(" -(S) Add School Work");
        System.out.println(" -(O) Add Other Tasks");
        System.out.println(" -(E) Edit Task");
        System.out.println(" -(C) Completed Task");
        System.out.println(" -(Q) Quit Application ");
    }

    public void displaySchedule(ArrayList<Event> schedule){
        for(Event event : schedule){
            event.display();
        }
    }
}
