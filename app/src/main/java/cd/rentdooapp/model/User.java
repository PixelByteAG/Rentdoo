package cd.rentdooapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shuge on 2017-10-21.
 */

public class User {

    //initialize all variables for user
    private int id;
    private String name;
    private String email;
    private String password;
    private String number;
    private String role;
    private Integer group;
    private double rent;
    private ArrayList<String> chores = new ArrayList<>();


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public int getGroup() { return group; }

    public void setGroup(int group) {this.group = group; }

    public double getRent() { return rent;}

    public void setRent(double rent){ this.rent = rent;}

    public String getRole() { return role;}

    public void setRole(String role) { this.role = role;}

    //Methods related to chores
    public ArrayList<String> getChores() { return this.chores; }

    public void setChores(ArrayList<String> newChores) { this.chores = newChores; }

    public String choresToString() {
        String returnString = "";
        for (int i=0; i<chores.size(); i++){
            if(i == chores.size()-1){
                returnString = returnString + chores.get(i);
            }
            else{
                returnString = returnString + chores.get(i) + "\n";
            }
        }
        return returnString;
    }

    public void stringToChores(String newChores) {
        String[] tempString = newChores.split("\"\\\\s*,\\\\s*\"");
        List<String> newList = Arrays.asList(tempString);
        ArrayList<String> userChores = new ArrayList<>(newList);
        setChores(userChores);
    }

    public void addChore(String newChore) { this.chores.add(newChore); }

    public void removeChore(String oldChore)
    {
        for (int i=0; i<chores.size(); i++){
            String tempChore = chores.get(i);
            if(tempChore.equals(oldChore)){
                chores.remove(i);
                return;
            }
        }
        //no error checking is needed as the leaseholder can only remove existing chores
    }
}













