package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.io.PrintWriter;
import java.util.Iterator;

public class Tools {

    public static HashMap wordCounter(String data){
        HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
        String[] words = data.split(" ");

        for(String word : words){
            if (word.endsWith(".") || word.endsWith(",")){ word = lastCharRemover(word); }

            if(wordsMap.containsKey(word)){
                wordsMap.put(word, wordsMap.get(word) + 1);
            }else{
                wordsMap.put(word, 1);
            }
        }
        return wordsMap;
    }

    public static List<String> frequencySorter(HashMap wordsMap){
        List list = new LinkedList(wordsMap.entrySet());

        Collections.sort(list, new Comparator(){
            public int compare(Object o1, Object o2){
               return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
           }
        });
        return list;
    }

    public static void rowWriter(List list, PrintWriter output){
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            output.println(entry.getKey() + " - " +  entry.getValue() + " korda");
        }
    }

    private static String lastCharRemover(String word){ return word.substring(0, word.length() - 1); }

}
