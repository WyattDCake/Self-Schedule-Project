public class Event implements Comparable<Event>{

    private String eventName;
    private int eventID;
    private int month;
    private int day;

    
    public Event(String name,int id,int m,int d){
        this.eventName = name;
        this.eventID = id;
        this.month = m;
        this.day = d;
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

    public String display(){
        return(eventName+"\n   Due: " + month + "/" +day);
    }
    @Override
    public String toString(){
        return (eventName+";"+eventID+";"+month+";"+day+"\n");
    }
}   

