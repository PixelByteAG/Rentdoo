package cd.rentdooapp.model;

import java.util.Date;

/**
 * Created by krashton1 on 10/30/2017.
 */

public class Bill {
    //initialize all variables for bill
    private int id;
    private String name;
    private int amount;
    private Boolean paid;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setDate(Date date){this.date=date;}

    public Date getDate(){return date;}
}

