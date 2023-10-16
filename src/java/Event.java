public class Event implements Comparable<Event>{

    private String eventName;
    private int eventID;
    private int month;
    private int day;

    public Event(){
        this.eventName = "";
        this.eventID = 0;
        this.month = 0;
        this.day = 0;
    }
    public Event(String name,int id,int m,int d){
        this.eventName = name;
        this.eventID = id;
        this.month = m;
        this.day = d;
    }
    public Event(Event event){
        this.eventName = event.getName();
        this.eventID = event.getID();
        this.month = event.getMonth();
        this.day = event.getDay();
    }
    
    @Override
    public int compareTo(Event otherEvent){
        if(this.month<otherEvent.month){
            return -1;
        }else if (this.month == otherEvent.month){
            if(this.day<otherEvent.day){
                return -1;
            }else if(this.day == otherEvent.day){
                if(this.eventID < otherEvent.eventID){
                    return -1;
                }else if(this.eventID == otherEvent.eventID){
                    return 0;
                }   
            }
        }
        return 1;
    }

    public String getName(){
        return this.eventName;
    }

    public int getID(){
        return this.eventID;
    }
    
    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public void setName(String n){
        this.eventName = n;
    }

    public void setID(int id){
        this.eventID = id;
    }

    public void setDay(int d){
        this.day = d;
    }

    public void setMonth(int m){
        this.month = m;
    }
    public String getDate(){
         return(month + "/" +day);
    }

    public String display(){
        return(eventName+"\n   Due: " + month + "/" +day);
    }
    @Override
    public String toString(){
        return (eventName+";"+eventID+";"+month+";"+day+"\n");
    }
}   

