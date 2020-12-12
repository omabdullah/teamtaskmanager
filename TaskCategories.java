import java.util.*;

public class TaskCategories {

    //instance variables
    public ArrayList<Tasks> task_list = new ArrayList<Tasks>();
    String name;
    String description;
    String created_by;
    String created_on;
    Scanner input = new Scanner(System.in);

    //default constructor
    public TaskCategories(){
        System.out.println("Enter the name of the Task Category:");
        name = input.nextLine();
        System.out.println("Enter the description of the Task Category:");
        description = input.nextLine();
        System.out.println("Enter the date created:");
        created_on = input.nextLine();
    }

    //constructor
    public TaskCategories(String name, String description, String created_by, String created_on){
        this.name = name;
        this.description = description;
        this.created_by = created_by;
        this.created_on = created_on;
    }

    //return task category name
    String getTCname(){
        return name;
    }

    //set creator of task category
    void setCreator(String current_user){
        created_by = current_user;
    }

    //print out tasks assigned to team
    public void getTasks(){
        if(task_list.size() == 0)
            System.out.println("\tNo Tasks");
        else{
            System.out.println("\tTASKS:");
            System.out.println("\t------");
            for(int x = 0; x < task_list.size(); x++){
                System.out.println("\t" + (x+1) + ". " + task_list.get(x).getColor() + task_list.get(x).getTaskName() + colors.RESET);
                if(task_list.get(x).schedule_date_weekly != null)
                    System.out.println("\t\tOccurs weekly on " + task_list.get(x).schedule_date_weekly);
                else if(task_list.get(x).schedule_date_monthly != null)
                    System.out.println("\t\tOccurs monthly on " + task_list.get(x).schedule_date_monthly);
            }
        }
    }

}