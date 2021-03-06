package com.example.jeff.happyhokie;

/**
 * Created by Craig
 * Custom adapter for our expandable listview
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private ArrayList<FullDeal> listODeals;

    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    DealGetter dealGetter;
    ArrayList<FullDeal> dealList;
    boolean description = false;


    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, InputStream data
    ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        dealGetter = new DealGetter(day, data);
        dealList = dealGetter.getAllDeals();
        //this.expandableListDetail = expandableListDetail;
    }

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, InputStream data, String rest
    ) {
        description = true;
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        dealGetter = new DealGetter(day, data);
        dealList = dealGetter.getWholeWeek(rest);
        //this.expandableListDetail = expandableListDetail;
    }
    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.dealList.get(listPosition).getAllDeals().get(expandedListPosition);
    }
    //    @Override
//    public Object getChild(int listPosition, int expandedListPosition) {
//        return this.dealList.get(this.expandableListTitle.get(listPosition))
//                .get(expandedListPosition);
//    }
    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);


        //TODO actually load in deals here
        SingleDeal deal = (SingleDeal) getChild(listPosition,expandedListPosition);

        if(description){
            expandedListTextView.setText(deal.getDealWithDetails());
        }
        else {
            expandedListTextView.setText(deal.getName());
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.dealList.get(listPosition).getAllDeals().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.dealList.get(listPosition);
    }
    //
    @Override
    public int getGroupCount() {
        return this.dealList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        String fill;
        if(description){
            fill = dealList.get(listPosition).sDay;
        }
        else{
            fill = dealList.get(listPosition).getRestaurant();
        }
        //String rest2 = dealList.get(listPosition).getRestaurant();
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        //listTitleTextView.setText(rest2);
        listTitleTextView.setText(fill);
        return convertView;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }
    //
    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
