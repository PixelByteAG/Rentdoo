package cd.rentdooapp.model;

import java.util.Date;

/**
 * Created by krashton1 on 10/30/2017.
 */

public class Chore {
    //initialize all variables for chore
    private int id;
    private String name;
    private String assigned;
    private Boolean done;
    private Date date;

    //initialize all setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setDate(Date date){this.date=date;}

    public Date getDate(){return date;}
}

