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
                addWorkShift(schedule, scanner);
                break;
            case "S":
                addSchoolWork(schedule,scanner);
                break;
            case "O":
                addOtherEvent(schedule, scanner);
                break;
            case "E":
                editTask(schedule, scanner);
                break;
            case "C":
                completedTask(schedule, scanner);
                break;
            case "Q":
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

    public void displayNumberedSchedule(ArrayList<Event> schedule){
        int count = 1;
        for(Event event : schedule){
            System.out.print(count+". ");
            event.display();
            count++;
        }
    }

    public void addWorkShift(ArrayList<Event> schedule,Scanner scanner){
        System.out.println();
        System.out.println("What Time is the Shift? (StartTime-EndTime):");
        String time = scanner.nextLine();
        int day,month;
        System.out.println("What Day? (MM/DD):");
        String input = scanner.nextLine();
        String[] parts = input.split("/");
        month = Integer.valueOf(parts[0]);
        day = Integer.valueOf(parts[1]);
        Event shift = new Event("Work Shift : "+time,2, month, day);
        schedule.add(shift);
        Collections.sort(schedule);
    }
    
    public void addSchoolWork(ArrayList<Event> schedule, Scanner scanner){
        System.out.println();
        System.out.println("What Class?:");
        String classNum = scanner.nextLine();
        System.out.println("What is due?:");
        String assignment = scanner.nextLine();
        System.out.println("When is it due? (MM/DD):");
        String input = scanner.nextLine();
        String[] parts = input.split("/");
        int month = Integer.valueOf(parts[0]);
        int day = Integer.valueOf(parts[1]);
        Event task = new Event(classNum+" : "+assignment,1,month,day);
        schedule.add(task);
        Collections.sort(schedule);
    }

    public void addOtherEvent(ArrayList<Event> schedule, Scanner scanner){
        System.out.println();
        System.out.println("What needs to be done?:");
        String task = scanner.nextLine();
        System.out.println("When?(MM/DD):");
        String input = scanner.nextLine();
        String[] parts = input.split("/");
        int month = Integer.valueOf(parts[0]);
        int day = Integer.valueOf(parts[1]);
        Event event = new Event(task,3,month,day);
        schedule.add(event);
        Collections.sort(schedule);
    }

    public void completedTask(ArrayList<Event> schedule, Scanner scanner){
        System.out.println();
        displayNumberedSchedule(schedule);
        System.out.println("Which Task is Completed?:");
        int input = Integer.valueOf(scanner.nextLine());
        if(input >= 0 && input <=schedule.size()){
            schedule.remove(input-1);
        }
    }
    
    public void editTask(ArrayList<Event> schedule, Scanner scanner){
        System.out.println();
        displayNumberedSchedule(schedule);
        System.out.println("Which Do You Want to Edit?:");
        int input = Integer.valueOf(scanner.nextLine());
        Event newEvent = schedule.get(input-1);
        schedule.remove(input-1);
        System.out.println("What would you like to change?:");
        System.out.println(   "-name   -day   -month");
        String place = scanner.nextLine();
        System.out.println("What would you like it to be?:");
        String change = scanner.nextLine();
        switch(place){
            case "name":
                newEvent.setName(change);
                break;
            case "day" :
                newEvent.setDay(Integer.valueOf(change));
                break;
            case "month" :
                newEvent.setMonth(Integer.valueOf(change));
                break;
            default:
                System.out.println("No Change was Made");
        }
        schedule.add(newEvent);
        Collections.sort(schedule);
    }
}
