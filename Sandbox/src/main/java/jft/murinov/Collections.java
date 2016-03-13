package jft.murinov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dima on 13.03.2016.
 */
public class Collections {

    public static void main(String[] args){
        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

        languages.add("HTML");
/*
        languages.add("C#");
        languages.add("Python");
*/


        for (String l: languages){
            System.out.println("Я хочу выучить " + l);
        }


        for (int i = 0; i < languages.size(); i++){
            System.out.println("Я хочу выучить " + languages.get(i));
        }
    }
}
