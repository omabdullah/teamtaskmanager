import java.util.*;

public class loginScreen{

  //instance variables
  ArrayList<Members> members_list = new ArrayList<Members>();
  Scanner input = new Scanner(System.in);
  String user;
  String password;
  String currentUser;
  boolean loggedin = false;
  boolean quit = false; //user doesn't want to login anymore

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //display loginscreen
  public void displayLoginScreen(){

    //go through list of members and see if user input 
    // is valid member to login aka login attempt
    do{
      ClearScreen.clearScreen();
      System.out.println("Please enter your username or type cancel to quit:");
      user = input.nextLine();
      
      //user wants to stop logging in if cancel typed in
      if(user.equalsIgnoreCase("cancel")){
        ClearScreen.clearScreen();
        System.out.println("Exiting Productivity Tool. Thank you. Goodbye");
        quit = true;
        return;
      }

      System.out.println("Please enter your password:");
      password = input.nextLine();

      for(int x = 0; x < members_list.size(); x++){
        if(user.equalsIgnoreCase(members_list.get(x).getUser()) && 
          password.equalsIgnoreCase(members_list.get(x).getPassword())){
            ClearScreen.clearScreen();
            System.out.println(colors.GREEN + "Correct login" + colors.RESET);
            currentUser = members_list.get(x).getName();
            loggedin = true;
            break;
          }
      }
      //if logged in success then stop, else keep asking for login info
      if(loggedin)
        break;
      else{
        ClearScreen.clearScreen();
        System.out.println(colors.RED + "Incorrect Login" + colors.RESET);
        System.out.println("Press Enter to continue...");
        input.nextLine();
      }
    }while(!loggedin);

  }//end displayLoginScreen
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  //set member_list arraylist to read information to allow for login
  public void setMembersList(ArrayList<Members> members_list){
    this.members_list = members_list;
  }

  //get status if user loggedin
  public boolean loggedIn(){
    return loggedin;
  }

  //get status if user doesn't want to login anymore and quit system
  public boolean userQuit(){
    return quit;
  }

  //returns name of current logged in users
  public String getCurrentUser(){
    return currentUser;
  }

  //set loggedin to status 
  public void setLoggedIn(boolean status){
    loggedin = status;
  }

}//end loginScreen
