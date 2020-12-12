import java.util.*;

public class TeamMenu {
    
    //instance variables
    ArrayList<Members> members_list = new ArrayList<Members>();
    Scanner input = new Scanner(System.in);
    String optionSelected;
    Teams current_team;
    String current_user;
    int user_tasks_completed;
    
    //constructor
    TeamMenu(Teams team){
        current_team = team;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //display Team menu screen option
    public void displayScreen(){
        
        do{

            ClearScreen.clearScreen();
            System.out.println(current_team.getName() + " | Tasks Completed: " + current_team.getTasksCompleted());
            System.out.println("----------------");
            current_team.getMembers();
            current_team.getTasks();
            current_team.getTaskCategories();
            printOptions();
            optionSelected = input.nextLine();

            //exit option, exit to mainScreen
            if(optionSelected.equalsIgnoreCase("exit")){
                return;
            }

            //add member option
            else if(optionSelected.equalsIgnoreCase("add member")){
                ClearScreen.clearScreen();
                displayUsers();
                System.out.println("Please enter the name of the member to add:");
                optionSelected = input.nextLine();
                if(addMember(optionSelected)){
                    System.out.println(colors.GREEN + "Success" + colors.RESET);
                    System.out.println(optionSelected + " added to Team " + current_team.getName());
                    System.out.println("Press Enter to continue...");
                    optionSelected = input.nextLine();
                }
                else{
                    System.out.println(colors.RED + "ERROR" + colors.RESET);
                    System.out.println("Could not added user\nPress Enter to continue...");
                    optionSelected = input.nextLine();
                }
            }

            //delete member
            else if (optionSelected.equalsIgnoreCase("delete member")){
              ClearScreen.clearScreen();
              current_team.getMembers();
              if(current_team.current_members.size() == 0){
                System.out.println("No members to delete");
                System.out.println("Press Enter to continue...");
                optionSelected = input.nextLine();
              }
              else{
                System.out.println("Please enter the name of the member to delete:");
                optionSelected = input.nextLine();
                  if(removeMember(optionSelected)){
                    System.out.println(colors.GREEN + "Success" + colors.RESET);
                    System.out.println(optionSelected + " removed from Team " + current_team.getName());
                    System.out.println("Press Enter to continue...");
                    optionSelected = input.nextLine();
                  }
                  else{
                    System.out.println(colors.RED + "ERROR" + colors.RESET);
                    System.out.println("Could not remove user\nPress Enter to continue...");
                    optionSelected = input.nextLine();
                  }
              }
            }

            //Create task option
            else if(optionSelected.equalsIgnoreCase("create task")){
                ClearScreen.clearScreen();
                current_team.createTask(current_team.getName(),current_user);
                System.out.println(colors.GREEN + "Success" + colors.RESET);
                System.out.println("Task was created");
                System.out.println("Press Enter to continue...");
                optionSelected = input.nextLine();
            }
            
            //delete task
            else if(optionSelected.equalsIgnoreCase("delete task")){
                ClearScreen.clearScreen();
                current_team.getTasks();
                if(current_team.task_list.size() == 0){
                  System.out.println("No Tasks to delete");
                  System.out.println("Press Enter to continue...");
                  optionSelected = input.nextLine();
                }
                else{
                  System.out.println("Please enter the task to be deleted:");
                  optionSelected = input.nextLine();
                    if (current_team.deleteTask(optionSelected)){
                    System.out.println(colors.GREEN + "Success" + colors.RESET);
                    System.out.println("Task was deleted");
                    System.out.println("Press Enter to continue...");
                    optionSelected = input.nextLine();
                    }
                    else{
                      System.out.println(colors.RED + "ERROR" + colors.RESET);
                      System.out.println("Could not delete task\nPress Enter to continue...");
                      optionSelected = input.nextLine();
                    }
                }
            }

            // Edit task option
            else if (optionSelected.equalsIgnoreCase("edit task")){
                ClearScreen.clearScreen();
                current_team.getTasks();
                if(current_team.task_list.size() == 0){
                  System.out.println("No Tasks to edit");
                  System.out.println("Press Enter to continue...");
                  optionSelected = input.nextLine();
                }
                else{
                  System.out.println("Please select a task to edit");
                  optionSelected = input.nextLine();
                  if(current_team.modifyTask(optionSelected)){
                    System.out.println(colors.GREEN + "Success: " + colors.RESET +"Task has been modified");
                    System.out.println("Press Enter to continue...");
                    optionSelected = input.nextLine();
                  }
                  else{
                    System.out.println(colors.RED + "Error: " + colors.RESET + "Task not found");
                    System.out.println("Press Enter to continue...");
                    optionSelected = input.nextLine();
                  }
                }
            }

            //create task category
            else if(optionSelected.equalsIgnoreCase("create task category")){
                ClearScreen.clearScreen();
                current_team.createTC(current_user);
                System.out.println(colors.GREEN + "Success" + colors.RESET);
                System.out.println("Task Category was created");
                System.out.println("Press Enter to continue...");
                optionSelected = input.nextLine();
            }

            //delete task category option
            else if(optionSelected.equalsIgnoreCase("delete task category")){
                ClearScreen.clearScreen();
                if(current_team.task_Categories_list.size() == 0){
                    System.out.println("No task categories available");
                    System.out.println("Press enter to continue...");
                    optionSelected = input.nextLine();
                }
                else{
                    current_team.getTaskCategories();
                    if(current_team.deleteTC()){
                        System.out.println(colors.GREEN + "Success" + colors.RESET);
                        System.out.println("Task category was deleted");
                        System.out.println("Press Enter to continue...");
                        optionSelected = input.nextLine();
                    }
                    else{
                        System.out.println(colors.RED + "ERROR" + colors.RESET);
                        System.out.println("No Task Category of that name to delete");
                        System.out.println("Press Enter to continue...");
                        optionSelected = input.nextLine();
                    }
                }
            }

            //select task category option
            else if(optionSelected.equalsIgnoreCase("Select task category")){
                ClearScreen.clearScreen();
                current_team.getTaskCategories();
                if(current_team.selectTC()){
                    ClearScreen.clearScreen();
                    current_team.displayTCMenu();
                }
                else{
                    if(current_team.task_Categories_list.size() == 0){
                      System.out.println(colors.RED + "ERROR" + colors.RESET);
                      System.out.println("No Task Categories Available");
                      System.out.println("Press enter to continue...");
                      optionSelected = input.nextLine();
                    }
                    else{
                      System.out.println(colors.RED + "ERROR" + colors.RESET);
                      System.out.println("No Task Category of that name to select");
                      System.out.println("Press Enter to continue...");
                      optionSelected = input.nextLine();    
                    }
                }
            }

            //schedule task option
            else if(optionSelected.equalsIgnoreCase("schedule task")){
              ClearScreen.clearScreen();
              current_team.getTasks();
              if(current_team.task_list.size() == 0){
                System.out.println(colors.RED + "ERROR" + colors.RESET);
                System.out.println("No Tasks to schedule");
                System.out.println("Press enter to continue...");
                optionSelected = input.nextLine();
              }
              else{
                boolean found = false;
                System.out.println("Please enter the task to set a schedule for:");
                optionSelected = input.nextLine();
                for(int x = 0; x < current_team.task_list.size(); x++){
                  if(current_team.task_list.get(x).getTaskName().equalsIgnoreCase(optionSelected)){
                    found = true;  
                  }
                }
                if(found)
                  scheduleTask(optionSelected);
                else{
                  System.out.println(colors.RED + "ERROR" + colors.RESET);
                  System.out.println("Not a task listed");
                  System.out.println("Press enter to continue...");
                  optionSelected = input.nextLine();
                }
              }
            }

            //mark task as complete
            else if(optionSelected.equalsIgnoreCase("mark task complete")){
              ClearScreen.clearScreen();
              current_team.getTasks();
              markTask();
              System.out.println("Press enter to continue...");
              optionSelected = input.nextLine();
            }

            //check team Productivity
            else if (optionSelected.equalsIgnoreCase("team productivity")){
              ClearScreen.clearScreen();
              if(current_user.equalsIgnoreCase(current_team.getCreator())){
                System.out.println(colors.GREEN + "Success" + colors.RESET);
                System.out.println("Valid Access to Productivity Information");
                System.out.println("Tasks Completed by each user");
                getMemberProductivity();
                System.out.println("Press enter to continue...");
                optionSelected = input.nextLine();
              }
              else{
                System.out.println(colors.RED + "ERROR" + colors.RESET);
                System.out.println("Invalid Access to Productivity Information");
                System.out.println("Press enter to continue...");
                optionSelected = input.nextLine();
              }
            }

        }while(!optionSelected.equalsIgnoreCase("exit"));
            
    }//end display screen
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //set member_list arraylist to read information in list
    public void setMembersList(ArrayList<Members> members_list){
        this.members_list = members_list;
    }

    //print out users in system
    public void displayUsers(){
        System.out.println("Current Users:");
        for(int x = 0; x < members_list.size(); x++)
            System.out.println(x+1 + ". " + members_list.get(x).getName());
        System.out.println();
    }

    //add member to current team
    public boolean addMember(String user_name){
        boolean added = false;
        for(int x = 0; x < members_list.size(); x++){
            if(user_name.equalsIgnoreCase(members_list.get(x).getName())){   
              for(int y = 0; y < current_team.current_members.size(); y++){
                if(user_name.equalsIgnoreCase(current_team.current_members.get(y).getName())){
                  System.out.println("Member already in Team");
                  return added;
                }
              }
                current_team.current_members.add(members_list.get(x));
                added = true;
                return added;
            }
        }
    return added;
    }

    //remove member from current team
    public boolean removeMember(String user_name){
      boolean removed = false;
      for (int x = 0; x < current_team.current_members.size(); x++){
        if(user_name.equalsIgnoreCase(current_team.current_members.get(x).getName())){
          current_team.current_members.remove(x);
          removed = true;
          return removed;
        }
      }
      return removed;
    }

    //set current_user to name of who is logged on
    public void setCurrentUser(String name){
        current_user = name;
    }

    //print options menu list for user to select
    public void printOptions(){
      System.out.println("Please select a team option:");
            System.out.println("Add Member | Delete Member | Create Task | Edit Task");
            System.out.println("Delete Task | Schedule Task | Create Task Category");
            System.out.println("Delete Task Category | Select Task Category");
            System.out.println("Mark Task Complete | Team Productivity");
            System.out.println("Exit"); 
    }

    //schedule task inputed by user
    public void scheduleTask(String task_input){
      boolean found = false;
      //check if user input is task listed
      int x;
      for(x = 0; x < current_team.task_list.size(); x++){
        if(task_input.equalsIgnoreCase(current_team.task_list.get(x).getTaskName())){
          found = true;
          break;
        }
      }

      if(found){
        ClearScreen.clearScreen();
        String answer = "";
        while(!answer.equalsIgnoreCase("cancel")){
          System.out.println("Is this a weekly or monthly task? Or enter cancel to exit");
          answer = input.nextLine();
          if(answer.equalsIgnoreCase("weekly")){
            current_team.task_list.get(x).setScheduleWeekly();
            System.out.println(colors.GREEN + "Success" + colors.RESET);
            System.out.println("Task schedule to occur weekly on " + current_team.task_list.get(x).schedule_date_weekly);
            System.out.println("Press enter to continue..."); 
            answer = input.nextLine();
            return; 
          }
          else if(answer.equalsIgnoreCase("monthly")){
            current_team.task_list.get(x).setScheduleMonthly();
            System.out.println(colors.GREEN + "Success" + colors.RESET);
            System.out.print("Task schedule to occur monthly on " + current_team.task_list.get(x).schedule_date_monthly);
            System.out.println(" of every month");
            System.out.println("Press enter to continue..."); 
            answer = input.nextLine();
            return; 
          }
          else if(answer.equalsIgnoreCase("cancel")){
            System.out.println("Canceling schedule option");
            System.out.println("Press enter to continue...");
            optionSelected = input.nextLine();
            return;
          }
          else{
            System.out.println(colors.RED + "ERROR" + colors.RESET);
            System.out.println("Not a valid option");
            System.out.println("Press enter to continue...");
            answer = input.nextLine();
          }

        }
      }
      else{
        System.out.println(colors.RED + "ERROR" + colors.RESET);
        System.out.println("No Task of that name found");
        System.out.println("Press enter to continue...");
        optionSelected = input.nextLine();
      }

    }

    //set task as complete based on user input
    public void markTask(){
      System.out.println("Which task would you like to mark as complete?");
      String choice = input.nextLine();
      boolean found = false;
      int x;
     
      for(x = 0; x < current_team.task_list.size(); x++){
        if(choice.equalsIgnoreCase(current_team.task_list.get(x).getTaskName())){
          found = true;
          break;
        }
      }

      //mark task as complete
      if(found && current_team.task_list.get(x).getTaskStatus().equalsIgnoreCase("incomplete")){
        current_team.task_list.get(x).setTaskStatus("complete");
        System.out.println(colors.GREEN + "Success" + colors.RESET);
        System.out.println("Task is now completed");
        current_team.addTaskCompleted();
        memberTaskCompleted();
      }
      //task is already marked as completed and cannot be marked again
      else if(found){
        System.out.println(colors.RED + "ERROR" + colors.RESET);
        System.out.println("Task is already marked as completed");
      }
      //no task found that user inputed
      else{
        System.out.println(colors.RED + "ERROR" + colors.RESET);
        System.out.println("No Task of that name found");
      }
    }

    // user tasks completed
    public void memberTaskCompleted(){
      
      for(int x=0; x < current_team.current_members.size(); x++)
      {
        if (current_user.equalsIgnoreCase(current_team.current_members.get(x).getName()))
        {
          current_team.current_members.get(x).completedTask();
          break;
        }
      }
    }

  public void getMemberProductivity(){
    for(int x=0; x < current_team.current_members.size(); x++){
      System.out.println(current_team.current_members.get(x).getName() + " : " + current_team.current_members.get(x).getCompletedTask());
    }
  }


    
    

}
