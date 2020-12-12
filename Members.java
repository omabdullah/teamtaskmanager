import java.sql.Struct;
import java.util.*;

public class Members {
    
    //instance variables
    String name;
    String user;
    String password;
    Scanner input = new Scanner(System.in);
    int tasks_complete;

    //default constructor
    public Members(){
    }

    //constructor
    public Members(String name,String user,String password){
        this.name = name;
        this.user = user;
        this.password = password;
    }

    //return member name
    String getName(){
        return name;
    }

    //return member username
    String getUser(){
      return user;
    }

    //return member password
    String getPassword(){
      return password;
    }

    

    

    //set member information
    public void createMember(){
      System.out.println("Please enter the name of the member:");
      name = input.nextLine();
      System.out.println("Please enter the username of the member:");
      user = input.nextLine();
      System.out.println("Please enter the password of the member:");
      password = input.nextLine();
      System.out.println(colors.GREEN + "SUCCESS" + colors.RESET);
      System.out.println("User account for " + name + " created");
    }

    //edit member information
    public boolean editMember(){
      String temp="";
      boolean modified = false;
      boolean correct = false;
      ClearScreen.clearScreen();
      System.out.println("Please enter password to edit user " + getName());
      temp = input.nextLine();

      if (temp.equals(getPassword())){
        correct = true;
      }

      while(!correct){
        ClearScreen.clearScreen();
        System.out.println(colors.RED + "ERROR: "+ colors.RESET +"Incorrect Password"  + "\nPlease enter password to edit user " + getName() + " or enter \"Quit\" to quit");
        temp = input.nextLine();
        if(temp.equals(getPassword())){
          correct = true;
        }
        else if (temp.equalsIgnoreCase("quit")){
          break;
        }
      }
      if(correct){
        System.out.println("Please Enter new name");
        temp = input.nextLine();
        this.name = temp;
        System.out.println("Please Enter new username");
        temp = input.nextLine();
        this.user = temp;
        System.out.println("Please Enter new password");
        temp = input.nextLine();
        this.password = temp;
        modified = true;
      }



      return modified;
    }

    public void completedTask(){
      tasks_complete++;
    }

    public int getCompletedTask(){
      return tasks_complete;
    }

}