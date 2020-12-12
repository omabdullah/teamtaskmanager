import java.util.*;



public class MainScreen{

  //instance variables
  ArrayList<Members> members_list = new ArrayList<Members>();
  ArrayList<Teams> teams_list = new ArrayList<Teams>();
  Scanner input = new Scanner(System.in);
  String optionSelected;
  boolean userLogout = false;
  Teams team;
  String current_user;

//Menu
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //display main screen, user can create, modify, delete members,tasks,task categories
  public void displayScreen(){

    //display users current teams, tasks to be added here
    System.out.println("Press Enter to continue...");
    optionSelected = input.nextLine();

    do{
      ClearScreen.clearScreen();
      printOptions();
      optionSelected = input.nextLine();

      //user logsout, stop everything
      if(optionSelected.equalsIgnoreCase("logout")){
        userLogout = true;
        return;
      }

      //display all users
      else if(optionSelected.equalsIgnoreCase("display users")){
        ClearScreen.clearScreen();
        displayUsers();
      }

      // Displays teams
      else if(optionSelected.equalsIgnoreCase("display teams")){
        ClearScreen.clearScreen();
        displayTeams();
        System.out.println("Press Enter to continue...");
        optionSelected = input.nextLine();
      }

      //select team option
      else if(optionSelected.equalsIgnoreCase("select team")){
        ClearScreen.clearScreen();
        if(teams_list.size() == 0){
          System.out.println("No teams available");
          System.out.println("Press Enter to continue...");
          optionSelected = input.nextLine();
        }
        else{
        displayTeams();
        System.out.println("Please select a team: ");
        optionSelected = input.nextLine();
          if(getTeamInfo(optionSelected)){
            TeamMenu tm = new TeamMenu(team);
            tm.setMembersList(members_list);
            tm.setCurrentUser(current_user);
            tm.displayScreen();
          }
          else{
            System.out.println("Press enter to continue...");
            optionSelected = input.nextLine();
          }
        }

      }

      //Create Member Option
      else if(optionSelected.equalsIgnoreCase("Create Member")){
        ClearScreen.clearScreen();
        Members m = new Members();
        m.createMember();
        if(checkDuplicateMember(m.getName())){
          System.out.println(colors.RED + "ERROR" + colors.RESET);
          System.out.println("User already exists and cannot be created");
          System.out.println("Deleting newly created account");
          System.out.println("Press enter to continue");
          optionSelected = input.nextLine();
        }
        else{
          members_list.add(m);
          System.out.println("Press enter to continue...");
          optionSelected = input.nextLine();    
        }
      }
      
      // Edit user information
      else if(optionSelected.equalsIgnoreCase("Edit Members")){
        ClearScreen.clearScreen();
        displayUsers();
        System.out.println("Please select a user to edit");
        System.out.println("---------------------------");
        optionSelected = input.nextLine();

        //check if there is user under optionSelected name available
        boolean flag = false;
        int x;
        for(x = 0; x < members_list.size(); x++){
          if(members_list.get(x).getName().equalsIgnoreCase(optionSelected)){
            flag = true;
            break;
          }
        }

        if(flag){
          if(members_list.get(x).editMember()){
              ClearScreen.clearScreen();
              System.out.println(colors.GREEN + "Success: " + colors.RESET + "Member succesfully edited");
              System.out.println("Press enter to continue...");
              optionSelected = input.nextLine();
          }
          else{
              ClearScreen.clearScreen();
              System.out.println(colors.RED + "Error: " + colors.RESET + "Failed to edit member");
              System.out.println("Press enter to continue...");
              optionSelected = input.nextLine();
          }
        }
        else{
          System.out.println(colors.RED + "ERROR" + colors.RESET);
          System.out.println("No user of that name available");
          System.out.println("Press Enter to continue...");
          optionSelected = input.nextLine();
        }
      }
      
      //delete member
      else if(optionSelected.equalsIgnoreCase("Delete Member")){ 
        ClearScreen.clearScreen();
        displayUsers();
        if(canDeleteUser()){
          System.out.println("Enter name of member to delete:");
          optionSelected = input.nextLine();    
          if(deleteMember(optionSelected)){
            System.out.println(colors.GREEN + "Success" + colors.RESET);
            System.out.println(optionSelected + " removed from database.");
            System.out.println("Press Enter to continue...");
            optionSelected = input.nextLine();
          }
          else{
            System.out.println(colors.RED + "ERROR" + colors.RESET);
            System.out.println("Could not remove user\nPress Enter to continue...");
            optionSelected = input.nextLine();
          }
        }
        else{
          System.out.println(colors.RED + "ERROR" + colors.RESET);
          System.out.println("No users to delete");
          System.out.println("Press Enter to continue...");
          optionSelected = input.nextLine();
        }
    }

      //create team option
      else if(optionSelected.equalsIgnoreCase("create new team")){  //NEED TO MAKE SURE NO DUPLICATES OPTION ADDED
        ClearScreen.clearScreen();
        Teams team = new Teams();
        team.createTeam(current_user);
        if(checkDuplicateTeam(team.getName())){
          System.out.println(colors.RED + "ERROR" + colors.RESET);
          System.out.println("Team already exists");
          System.out.println("Press Enter to continue...");
          optionSelected = input.nextLine();
        }
        else{
          teams_list.add(team);
          System.out.println(colors.GREEN + "Success" + colors.RESET);
          System.out.println("Team " + team.getName() + " created");
          System.out.println("Press enter to continue...");
          optionSelected = input.nextLine();
        }
      }

      //delete team
      else if(optionSelected.equalsIgnoreCase("delete team")){
        ClearScreen.clearScreen();
        if(teams_list.size() == 0){
          System.out.println("No Teams Available to delete");
          System.out.println("Press Enter to continue...");
          optionSelected = input.nextLine();
        }
        else{
          displayTeams();
          System.out.println("Enter name of team to delete:");
          optionSelected = input.nextLine();
          if(deleteTeam(optionSelected)){
            System.out.println(colors.GREEN + "Success" + colors.RESET);
            System.out.println(optionSelected + " removed from database.");
            System.out.println("Press Enter to continue...");
            optionSelected = input.nextLine();
          }
          else{
            System.out.println(colors.RED + "ERROR" + colors.RESET);
            System.out.println("Could not remove team\nPress Enter to continue...");
            optionSelected = input.nextLine();
          }
        }
      }

      ClearScreen.clearScreen();
    }while(!optionSelected.equalsIgnoreCase("logout"));
  
  userLogout = true;
  
  }//end displayScreen

/////////////////////////////////////////////////////////////////////////////////////////////////////////

//functions

  //return if user selected logout
  public boolean userLoggedOut(){
    return userLogout;
  }

  //set member_list arraylist to read information in list
  public void setMembersList(ArrayList<Members> members_list){
    this.members_list = members_list;
  }

  //set team_list arraylist to read information in list
  public void setTeamsList(ArrayList<Teams> teams_list){
    this.teams_list = teams_list;
  }

  //print out team lists by name
  public void displayTeams(){

    if(teams_list.size() == 0){
      System.out.println("No Teams Available");
      return;
    }

    for(int x = 0; x < teams_list.size(); x++)
      System.out.println(x+1 +". " + teams_list.get(x).getName());
  }

  //print out users in system
  public void displayUsers(){
    System.out.println("Current Users:");
    for(int x = 0; x < members_list.size(); x++)
      System.out.println(x+1 + ". " + members_list.get(x).getName());
    System.out.println("press Enter to continue...");
    input.nextLine();
  }

  //delete member given by option selected
  boolean deleteMember(String optionSelected){

    //stop current_user from deleting themselves
    if(optionSelected.equalsIgnoreCase(current_user)){
      System.out.println("Cannot delete current user logged on");
      return false;
    }

      boolean deleted = false;
      for (int x = 0; x < members_list.size(); x++){
        if(optionSelected.equalsIgnoreCase(members_list.get(x).getName())){
          members_list.remove(members_list.get(x));
          deleted = true;
          return deleted;
        }
      }
      return deleted;
    }

  //delete team given by option selected
  boolean deleteTeam(String optionSelected){
    boolean teamdelete = false;
    for (int x = 0; x < teams_list.size(); x++){
      if(optionSelected.equalsIgnoreCase(teams_list.get(x).getName())){
        teams_list.remove(teams_list.get(x));
        teamdelete = true;
        return teamdelete;
      }
    }
    return teamdelete;
  }


  //print out selected team info from Select Team option if any and return true if info found
  public boolean getTeamInfo(String team_name){
    boolean found = false; 
    int x;
    for(x = 0; x < teams_list.size(); x++){
      if(team_name.equalsIgnoreCase(teams_list.get(x).getName())){
        team = teams_list.get(x);
        found = true;
        break;
      }
    }
    if(found){
      ClearScreen.clearScreen();
      teams_list.get(x).getInfo();
      return found;
    }
    else{
      ClearScreen.clearScreen();
      System.out.println(colors.RED + "ERROR" + colors.RESET);
      System.out.println("No team of that name found");
      return found;
    }

  }

  //set current_user to keep track of who is currently logged in; current_user = name of user
  public void setUser(String user){
    current_user = user;
  }

  //get access to user currently logged in; current_user is name of user
  public String getUser(){
    return current_user;
  }

  //return true if users are available to be deleted excluding current_user
  public boolean canDeleteUser(){
    if(members_list.size() == 1 && members_list.get(0).getName() == current_user)
      return false;
    else
      return true;
  }

  //before creating member check if that member already has account
  public boolean checkDuplicateMember(String name){
      for(int x = 0; x < members_list.size(); x++){
        if(name.equalsIgnoreCase(members_list.get(x).getName()))
          return true;
      }
    return false;
  }

  //before creating team, check if that team already exists
  public boolean checkDuplicateTeam(String team_name){
    for(int x = 0; x < teams_list.size(); x++){
      if(team_name.equalsIgnoreCase(teams_list.get(x).getName()))
        return true;
    }
    return false;
  }

  //print options list menu
  public void printOptions(){
    System.out.println("Please select a Team or option:");
      System.out.println("-------------------------------");
      System.out.println("Create New Team | Select Team | Delete Team");
      System.out.println("Create Member | Delete Member | Display Users");
      System.out.println("Display Teams | Edit members | Logout");
  }

}

