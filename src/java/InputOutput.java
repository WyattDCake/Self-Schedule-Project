import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InputOutput {
    
    private String filePath = "src/data/testdata.txt";
    private BufferedReader reader;
    private FileWriter writer; 
    public InputOutput(){
        try{
            reader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Event> inputSchedule(){
        if(reader == null){
            System.out.println("Reader was not initalized");
            return new ArrayList<Event>();
        }
        try{
            ArrayList<Event> schedule = new ArrayList<Event>();
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(";");
                if(!(schedule.contains(new Event(parts[0],Integer.valueOf(parts[1]),Integer.valueOf(parts[2]),Integer.valueOf(parts[3]))))){
                    schedule.add(new Event(parts[0],Integer.valueOf(parts[1]),Integer.valueOf(parts[2]),Integer.valueOf(parts[3])));
                }
            }
            return schedule;
        }
        catch(IOException e){
        e.printStackTrace();
        return new ArrayList<Event>();    
        }
    }
    
    public void outputSchedule(ArrayList<Event> schedule){
        try{
            writer = new FileWriter(filePath);
        } catch(IOException e){
            e.printStackTrace();
        }
        try {
            for(Event event : schedule){
                writer.write(event.toString());
            }
            } catch(IOException e){
                e.printStackTrace();
        }
        close();
    }
   
    public void close(){
        try{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
