package cd.rentdooapp.model;

import java.util.Date;

/**
 * Created by krashton1 on 10/30/2017.
 */

public class Rule {
    //initialize all variables for notice
    private int id;
    private String name;
    private String info;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

