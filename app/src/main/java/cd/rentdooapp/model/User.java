package cd.rentdooapp.model;

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
    private Integer group;

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

    public Integer getGroup() { return group; }

    public void setGroup(int group) {this.group = group; }
}
