import java.util.*;

public class Tasks{

    //instance variables
    String name;
    String description;
    //need subtask class option here
    GregorianCalendar due_date;
    String assigned_to;
    GregorianCalendar created_on;
    String created_by;
    String status;
    String color;
    String schedule_date_weekly = null;
    String schedule_date_monthly = null;
    Scanner input = new Scanner(System.in);

    //default constructor
    public Tasks(){
        System.out.println("Please enter the name of the Task:");
        name = input.nextLine();
        System.out.println("Please enter the description of the Task:");
        description = input.nextLine();
        System.out.println("Please enter the due date, Please include - : YYYY-MM-DD");
        String s[] = input.nextLine().split("-");
        due_date = new GregorianCalendar(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]));
        created_on = new GregorianCalendar();
        status = "incomplete";
        ClearScreen.clearScreen();
        displayColors();
        System.out.println("Please enter a color to assign to the task");
        setColor(input.nextLine());
    }

    //return task name
    String getTaskName(){
        return name;
    }
    
    //set created_by to user who created it
    public void setCreator(String user_name){
        created_by = user_name;
    }

    //set task description
    public void setTaskDescription(String task_desc){
        description = task_desc;
    }

    //set due date
    /*public void setDueDate(String task_dd){
        due_date = task_dd;
    }*/

    //set task name
    public void setTaskName(String task_name){
        name = task_name;
    }

    //set who task is assigned to
    public void setAssignedto(String team_name){
        assigned_to = team_name;
    }

    

    //assign task a color
    public void setColor(String colorChoice){
        color = colors.ChooseColor(colorChoice);
    }

    //set task status
    public void setTaskStatus(String task_status){
        status = task_status;
    }

    //return task status
    public String getTaskStatus(){
      return status;
    }

    //display colors available to select for task
    public void displayColors(){
        System.out.println("RED");
        System.out.println("GREEN");
        System.out.println("YELLOW");
        System.out.println("BLUE");
        System.out.println("PURPLE");
        System.out.println("CYAN");
    }

    //return color of task
    public String getColor(){
        return color;
    }

    //set weekly task schedule
    public void setScheduleWeekly(){
        String answer = "";
        while(!answer.equalsIgnoreCase("Su") && !answer.equalsIgnoreCase("Mo") &&
              !answer.equalsIgnoreCase("Tu") && !answer.equalsIgnoreCase("We") &&
              !answer.equalsIgnoreCase("Th") && !answer.equalsIgnoreCase("Fr") &&
              !answer.equalsIgnoreCase("Sa")){
            ClearScreen.clearScreen();
            System.out.println("What day would you like to schedule this task on?");
            System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");
            answer = input.nextLine();
        }
        schedule_date_weekly = answer;
    }

    //set monthly task schedule
    public void setScheduleMonthly(){
        String answer = "1";
        do{
            ClearScreen.clearScreen();
            System.out.println("What day would you like to schedule this task on? Format DD");
            System.out.println("01 - 31");
            answer = input.nextLine();
        }while(!(Integer.parseInt(answer) > 0 && Integer.parseInt(answer) <= 31));
        schedule_date_monthly = answer;
    }

}//end class Tasks