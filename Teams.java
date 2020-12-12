import java.util.*;

public class Teams {
    
    //instance variables
    ArrayList<Members> current_members = new ArrayList<Members>(); //list of members of team
    ArrayList<Tasks> task_list = new ArrayList<Tasks>();
    ArrayList<TaskCategories> task_Categories_list = new ArrayList<TaskCategories>();
    String team_name;
    Scanner input = new Scanner(System.in);
    TaskCategories task_cat_selected;
    String creator;
    int tasks_completed;

    //default constructor
    public Teams(){
    }

    //constructor
    public Teams(String team_name){
        this.team_name = team_name;
    }

    //return team name
    String getName(){
        return team_name;
    }

    //set Team information
    public void createTeam(String creator){
        System.out.println("Please enter the name of the team");
        team_name = input.nextLine();
        this.creator = creator;
        tasks_completed = 0;
    }

    //print out both getMembers and getTasks info
    public void getInfo(){
        System.out.println("Team " + team_name + "\n---------------");
        getMembers();
        getTasks();
    }

    //print out members in team
    public void getMembers(){
        if(current_members.size() == 0)
            System.out.println("No Members");
        else{
            System.out.println("MEMBERS:");
            System.out.println("--------");
            for(int x = 0; x < current_members.size(); x++){
                System.out.println(x+1 + ". " + current_members.get(x).getName());
            }
        }
    }
    
    //print out tasks assigned to team
    public void getTasks(){
        if(task_list.size() == 0)
            System.out.println("No Tasks");
        else{
            System.out.println("TASKS:");
            System.out.println("------");
            for(int x = 0; x < task_list.size(); x++){
                System.out.println(x+1 + ". " + task_list.get(x).getColor() + task_list.get(x).getTaskName() + colors.RESET);
                if(task_list.get(x).schedule_date_weekly != null)
                    System.out.println("\t\tOccurs weekly on " + task_list.get(x).schedule_date_weekly);
                else if(task_list.get(x).schedule_date_monthly != null)
                    System.out.println("\t\tOccurs monthly on " + task_list.get(x).schedule_date_monthly);
            }
        }
    }

    //print out task categories list assigned to team
    public void getTaskCategories(){
        if(task_Categories_list.size() == 0){
            System.out.println("No Task Categories");
        }
        else{
            System.out.println("TASK CATEGORIES:");
            System.out.println("----------------");
            for(int x = 0; x < task_Categories_list.size(); x++){
                System.out.println(x+1 + ". " + task_Categories_list.get(x).getTCname());
                task_Categories_list.get(x).getTasks();
            }
        }
    }

    //create a task for team
    void createTask(String team_name, String current_user){
        Tasks task = new Tasks();
        task.setAssignedto(team_name);
        task.setCreator(current_user);
        task_list.add(task);
    }

    //deletetask
    boolean deleteTask(String optionSelected){
      boolean removed = false;
      for (int x = 0; x < task_list.size(); x++){
        if(optionSelected.equalsIgnoreCase(task_list.get(x).getTaskName())){
          task_list.remove(task_list.get(x));
          removed = true;
          return removed;
        }
      }
      return removed;
    }

    //edit task
    boolean modifyTask(String optionSelected){
        String temp;
        boolean modified = false;
        for(int x = 0; x < task_list.size(); x++){
            if(optionSelected.equalsIgnoreCase(task_list.get(x).getTaskName())){
                
                System.out.println("Please enter the new name of the Task:");
                temp = input.nextLine();
                task_list.get(x).setTaskName(temp);
                System.out.println("Please enter the new description of the Task:");
                temp = input.nextLine();
                task_list.get(x).setTaskDescription(temp);
                /*System.out.println("Please enter the new due date:");
                temp = input.nextLine();
                task_list.get(x).setDueDate(temp);*/
                System.out.println("Please enter new person task is assigned to");
                temp = input.nextLine();
                task_list.get(x).setAssignedto(temp);
                /*System.out.println("Please enter the new status of the task:");
                temp = input.nextLine();
                task_list.get(x).setTaskStatus(temp);*/

                ClearScreen.clearScreen();
                
                task_list.get(x).displayColors();
                System.out.println("Please enter a color to assign to the task");
                temp= input.nextLine();
                task_list.get(x).setColor(temp);
                modified = true;
            }

        }
        return modified;
    }

    //create Task Category
    void createTC(String current_user){
        TaskCategories tc = new TaskCategories();
        tc.setCreator(current_user);
        task_Categories_list.add(tc);
    }

    //delete Task Category
    boolean deleteTC(){
        boolean deleted = false;
        System.out.println("Please enter the Task Category you would like to delete:");
        String TCname = input.nextLine();
        for(int x = 0; x < task_Categories_list.size(); x++)
            if(task_Categories_list.get(x).getTCname().equalsIgnoreCase(TCname)){
                task_Categories_list.remove(x);
                deleted = true;
                return deleted;
            }
        return deleted;
    }

    //return true if task category is found in list, false if else
    boolean selectTC(){
        boolean found = false;
        if(task_Categories_list.size() == 0)
          return false;
        System.out.println("Please select a task category");
        String s = input.nextLine();
        for(int x = 0; x < task_Categories_list.size(); x++)
            if(task_Categories_list.get(x).getTCname().equalsIgnoreCase(s)){
                task_cat_selected = task_Categories_list.get(x);
                found = true;
                return found;
            }
        return found;
    }

    //print task_list
    public void printTaskList(){
        for(int x = 0; x < task_list.size(); x++){
            System.out.println(x+1 + ". " + task_list.get(x).getColor() + 
            task_list.get(x).getTaskName() + colors.RESET);      
            if(task_list.get(x).schedule_date_weekly != null)
                System.out.println("\t\tOccurs weekly on " + task_list.get(x).schedule_date_weekly);
            else if(task_list.get(x).schedule_date_monthly != null)
                System.out.println("\t\tOccurs monthly on " + task_list.get(x).schedule_date_monthly);
        }
    }

    //set creator to string inputed
    public void setCreator(String creator){
      this.creator = creator;
    }

    //return creator of team
    public String getCreator(){
      return creator;
    }

    //add one to tasks_completed overall
    public void addTaskCompleted(){
      tasks_completed++;
    }

    

    //return number of tasks completed by team
    public int getTasksCompleted(){
      return tasks_completed;
    }

//task categories menu
//////////////////////////////////////////////////////////////////////////////////////////////////////

    //display Task categories menu options
    public void displayTCMenu(){
        String s;
            
        do{

            ClearScreen.clearScreen();
            System.out.println(task_cat_selected.getTCname() + "\n--------------");
            task_cat_selected.getTasks();
            System.out.println("Please select a Task Category Option:");
            System.out.println("Add Task | Remove Task | Exit");
            s = input.nextLine();

            //exit option
            if(s.equalsIgnoreCase("exit"))
                return;
            
            //add task option
            if(s.equalsIgnoreCase("add task")){
                boolean added = false;
                ClearScreen.clearScreen();
                System.out.println("Current tasks in " + task_cat_selected.getTCname());
                task_cat_selected.getTasks();
                if(task_list.size() == 0){
                    System.out.println("No Tasks you can add");
                    System.out.println("Press enter to continue...");
                    s = input.nextLine();
                }
                else{
                    printTaskList();//////////////////////////////working here
                    System.out.println("Which task would you like to add to " + task_cat_selected.getTCname());
                    s = input.nextLine();

                    for(int x = 0; x < task_list.size(); x++){
                        if(task_list.get(x).getTaskName().equalsIgnoreCase(s)){
                            task_cat_selected.task_list.add(task_list.get(x));
                            added = true;
                            break;
                        }
                    }
                    if(added){
                        System.out.println(colors.GREEN + "Success" + colors.RESET);
                        System.out.println("Task added to " + task_cat_selected.getTCname());
                        System.out.println("Press Enter to continue...");
                        s = input.nextLine();
                    }
                    else{
                        System.out.println(colors.RED + "ERROR" + colors.RESET);
                        System.out.println("Could not add task to " + task_cat_selected);
                        System.out.println("Press Enter to continue...");
                        s = input.nextLine();
                        
                    }
                }
            }
            
            //remove task option
            if(s.equalsIgnoreCase("remove task")){
                boolean removed = false;
                if(task_cat_selected.task_list.size() == 0){
                    removed = false;
                }
                else{
                    ClearScreen.clearScreen(); //need to finish this
                    task_cat_selected.getTasks();
                    System.out.println("Which task would you like to remove from " + task_cat_selected.getTCname());
                    s = input.nextLine();

                    for(int x = 0; x < task_list.size(); x++){
                        if(task_list.get(x).getTaskName().equalsIgnoreCase(s)){
                            task_cat_selected.task_list.remove(x);
                            removed = true;
                            break;
                        }
                    }
                }
                if(removed){
                    System.out.println(colors.GREEN + "Success" + colors.RESET);
                    System.out.println("Task removed from " + task_cat_selected.getTCname());
                    System.out.println("Press Enter to continue...");
                    s = input.nextLine();
                }
                else{
                    if(task_cat_selected.task_list.size() == 0){
                      System.out.println(colors.RED + "ERROR" + colors.RESET);
                      System.out.println("No Tasks to Remove from " + task_cat_selected.getTCname());
                      System.out.println("Press Enter to continue...");
                      s = input.nextLine();  
                    }
                    else{
                      System.out.println(colors.RED + "ERROR" + colors.RESET);
                      System.out.println("Could not remove task from " + task_cat_selected.getTCname());
                      System.out.println("Press Enter to continue...");
                      s = input.nextLine();  
                    }
                }
            }
            
        }while(!s.equalsIgnoreCase("exit"));
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

}//end class Teams