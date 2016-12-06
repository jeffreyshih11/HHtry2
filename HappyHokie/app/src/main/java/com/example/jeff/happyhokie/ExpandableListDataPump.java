package com.example.jeff.happyhokie;

/**
 * Created by Craig
 * Class that grabs the info about the restaurant deals from the XML
 */
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Calendar;


public class ExpandableListDataPump {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    //DealGetter dealGetter = new DealGetter(day);
    //ArrayList<FullDeal> dealList = dealGetter.getAllDeals();
   // public ArrayList<FullDeal> getListODeals() {
     //   return dealList;
   // }
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        /**
         * In this block is where we will put xml parsing logic
         * TODO: XML parsing
         */

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }
}
