package cd.rentdooapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cd.rentdooapp.model.Chore;

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
    private String chores;


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
    public String getChores() { return this.chores; }

    public void setChores(String newChores) { this.chores = newChores; }

    public ArrayList<Chore> chores_stringToArrayList(String newChores) {
        String[] tempString = newChores.split("\\r?\\n");
        List<String> newList = Arrays.asList(tempString);
        ArrayList<String> userChoresStrings = new ArrayList<>(newList);
        ArrayList<Chore> userChores = new ArrayList<>();
        for(int i=0; i<userChoresStrings.size(); i++){
            Chore tempChore = new Chore();
            tempChore.setName(userChoresStrings.get(i));
            tempChore.setDone(false);
            tempChore.setAssigned(this.getName());

            userChores.add(tempChore);
        }

        return userChores;
    }


    public void updateUserValues(String name, String email, String number, String rent, String chores){
        this.setName(name);
        this.setEmail(email);
        this.setNumber(number);
        this.setRent(Double.parseDouble(rent));
        this.setChores(chores);
        //this.setGroup(groupNum);
    }
}













