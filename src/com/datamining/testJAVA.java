package com.datamining;

import java.util.ArrayList;
import java.util.List;

public class testJAVA
{

    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list =  list.subList(0, 3);
        System.out.println(list);

    }

}
