/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GenericMaximum
{

    public static void main(String[] args)
    {

      Integer[] intArr = {5, 3, 7, 1, 4, 9, 8, 2};
	  String[] strArr = {"pear", "orange", "apple", "grape"};

	  int intStr = findMinimum(intArr);//
	  String str = findMinimum(strArr);//

      System.out.println(intStr+"\n"+str+"\n");

    }



    public static <T extends Comparable<? super T>> T findMinimum(T[] arr)
    {
	   T minvalue = arr[0];

	    for (int i = 1; i < arr.length; i++)
	    {
		  if (arr[i].compareTo(minvalue) < 0)
			minvalue = arr[i];

	    }

	    return minvalue;
    }

}