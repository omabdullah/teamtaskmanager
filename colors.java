public class colors {

    //instance variables
    public static final String RESET  = "\u001B[0m";
  
    public static final String BLACK  = "\u001B[30m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";
    public static final String WHITE  = "\u001B[37m";

    public static String ChooseColor(String c){
      if(c.equalsIgnoreCase("Red"))
        return RED;
      else if(c.equalsIgnoreCase("Green"))
        return GREEN;
      else if(c.equalsIgnoreCase("Yellow"))
        return YELLOW;
      else if(c.equalsIgnoreCase("Blue"))
        return BLUE;
      else if(c.equalsIgnoreCase("Purple"))
        return PURPLE;
      else if (c.equalsIgnoreCase("Cyan"))
        return CYAN;
      else
        return WHITE;
    }

}//end color class
