package com.example.guiderunner2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();

        List<String> Game = new ArrayList<String>();
        Game.add("To the 'Game' column, write the name of the game you played.\nExample:Hades,Super Mario,Skyrim...");

        List<String> Platform = new ArrayList<String>();
        Platform.add("To the 'Platform' column, write the name of the platform you played on.\nExample:Pc,Ps4,Xbox...");

        List<String> Time = new ArrayList<String>();
        Time.add("To the 'Time' column,write how long it took you to complete the game.\nUse this format 'hour:minute:second' ");

        List<String> Difficulty_Condition = new ArrayList<String>();
        Difficulty_Condition.add("To the 'Difficulty' column, write the difficulty/condition of the game you played.Example: \nDifficulty: Normal, Hard\nCondition: Blindfolded, Without hit...");

        List<String> Link = new ArrayList<String>();
        Link.add("To the 'Link' column, Paste the Youtube link of your Speedrun.\nExample: 'https://www.youtube.com/watch?v=dQw4w9WgXcQ' ");


        expandableDetailList.put("Game", Game);
        expandableDetailList.put("Platform", Platform);
        expandableDetailList.put("Time", Time);
        expandableDetailList.put("Difficulty/Condition", Difficulty_Condition);
        expandableDetailList.put("Link", Link);


        return expandableDetailList;
    }
}
