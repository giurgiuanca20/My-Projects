package org.example.spring1;

public class UrlMapping {
  public static final String API = "/api";
  public static final String ITEMS = API + "/items";
  public static final String WEEKLYS= API + "/weeklys";
  public static final String TODAYS= API + "/todays";
  public static final String MONTHLY= API + "/monthlys";
  public static final String COLORS= API + "/colors";
  public static final String GOOGLE= API + "/google";
  public static final String ADDGOOGLE="/addGoogle";
  public static final String AUTH = API + "/auth";

  public static final String SIGN_IN = "/signIn";
  public static final String SIGN_UP = "/signUp";

  public static final String ID_PART = "/{id}";
  public static final String FILTERED_ITEMS_PART = "/filtered";

  public static final String CHANGE_NAME_PART = "/change-name";
  public static final String SAVETODAY = "/saveToday";
  public static final String GETTODAY = "/getToday";
  public static final String DELETETODAY = "/deleteToday";
  public static final String SAVEWEEKLY= "/saveWeekly";
  public static final String GETWEEKLY = "/getWeekly";
  public static final String DELETEWEEKLY = "/deleteWeekly";
  public static final String SAVEMONTHLY= "/saveMonthly";
  public static final String GETMONTHLY = "/getMonthly";
  public static final String DELETEMONTHLY = "/deleteMonthly";
  public static final String SENDNRMONTHLY = "/sendNrMonthly";
  public static final String GETNRMONTHLY = "/getNrMonthly";
  public static final String GETCOLOR = "/getColor";
  public static final String UPDATECOLOR = "/updateColor";
}
