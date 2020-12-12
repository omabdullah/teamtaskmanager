import java.util.*;

public class Main {
    
    //instance variables
    static ArrayList<Teams> teams_list = new ArrayList<Teams>();
    static ArrayList<Members> members_list = new ArrayList<Members>();
    //static ArrayList<Tasks> task_list = new ArrayList<Tasks>();
    //static ArrayList<TaskCategories> task_categories_list = new ArrayList<TaskCategories>();

    public static void main(String[] args){

        //set default Members_list
        Members one = new Members("Esai","esai123","esai123");
        members_list.add(one);
        one = new Members("Luis","luis123","luis123");
        members_list.add(one);
        one = new Members("Osama","osama123","osama123");
        members_list.add(one);

        //set default team_list
        Teams two = new Teams("Alpha");
        two.setCreator("Esai");
        teams_list.add(two);


        //display login screen, set lists in loginscreen & MainScreen, create MainScreen
        loginScreen l = new loginScreen();
        l.setMembersList(members_list);
        l.displayLoginScreen();
        MainScreen mm = new MainScreen();
        mm.setMembersList(members_list);
        mm.setTeamsList(teams_list);

        
        while(!l.userQuit()){
            System.out.println("Welcome " + l.getCurrentUser());
            mm.setUser(l.getCurrentUser());
            mm.displayScreen();
            if(mm.userLoggedOut()){
                ClearScreen.clearScreen();
            System.out.println(l.getCurrentUser() + " logged out");
            l.setLoggedIn(false);
            }
        l.displayLoginScreen();
        }//end while
        
    }//end main

}//end class main
