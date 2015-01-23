//Name: Pedro Garate
//Date: 04/27/13
//***********************************************************************
//  TwoSorts.java    Author: Pedro Garate     Date: Apr. 27th, 2013
//
//  The applet displays bars of various heights, corresponding to the
//  integer values in two arrays.
//  It displays the bars after each "pass" of the sorting algorithms.
//  The passes on the arrays are controlled by a button. Each time the
//  button is pressed one element in each array is sorted and then the
//  bars are displayed. The blue bars use the selection sort and the
//  green bars use the insertion sort.
//***********************************************************************

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class TwoSorts extends Applet 
{
   private final int APPLET_WIDTH = 400;
   private final int APPLET_HEIGHT = 350;
   
   private int[] array1, array2;
   private boolean sorted1 = false, sorted2 = false, found;
   private int arraySize, passCounter, value, index1=0, index2=0;
      
   private Button pass;

   public void init()
   {
	   arraySize = (int)(Math.random()*20) + 10;
	   array1 = new int[arraySize];
	   array2 = new int[arraySize];
	   
	   for (int i = 0; i < arraySize; i++)
	   {
		   found = false;
		   while (!found)
		   {
			   found = true;
			   value = (int)(Math.random()*100);
			   for (int j = 0; j < i; j++)
			   {
				   if (value == array1[j])
				   {
					   found = false;
					   break;
				   }
			   }
		   }
		   array1[i] = value;
		   array2[i] = value;
	   }
	   
	   pass = new Button("Sort Me!");
	   pass.addActionListener(new ButtonListener());
	   
	   setBackground(Color.yellow);
	   setSize(APPLET_WIDTH, APPLET_HEIGHT);
	   add(pass);
	   
	   final Label topLabel = new Label("Blue Selection / Green Insertion / Black Sorted!");
	   add(topLabel);
	   
	   passCounter = 0;
   }
   
   public void paint (Graphics page)
   {
	   if (sorted1 == false)
	   	   page.setColor(Color.blue);
	   else
		   page.setColor(Color.black);
	   
	   int xPos = 10, yPos = 150;
	   
	   for (int i = 0; i < arraySize; i++)
	   {
		   page.fillRect(xPos, yPos - array1[i], 10, array1[i]);
		   xPos += 13;
	   }
	   
	   if (sorted2 == false)
	   	   page.setColor(Color.green);
	   else
		   page.setColor(Color.black);
	   
	   xPos = 10;
	   yPos = 300;
	   
	   for (int i = 0; i < arraySize; i++)
	   {
		   page.fillRect(xPos, yPos - array2[i], 10, array2[i]);
		   xPos += 13;
	   }
	   
	   if (sorted1 == true && sorted2 == true)
		   pass.setLabel("Done!");	   
   }
   
   public void selectionSort()
   {
	   int min, temp;
	   if (index1 < arraySize-1)
	   {
		   min = index1;
		   for (int scan = index1+1; scan < arraySize; scan++)
			   if(array1[scan] < array1[min])
				   min = scan;
		   temp = array1[min];
		   array1[min] = array1[index1];
		   array1[index1] = temp;
		   index1++;
	   }
	   else sorted1 = true;
   }
   
   public void insertionSort()
   {
	   if (index2 < arraySize)
	   {
		   int key = array2[index2];
		   int position = index2;
		   
		   while(position > 0 && key < array2[position-1])
		   {
			   array2[position] = array2[position-1];
			   position--;
		   }
		   array2[position] = key;
		   index2++;
	   }
	   else sorted2 = true;
	}
   
   private class ButtonListener implements ActionListener
   {
	   public void actionPerformed (ActionEvent event)
	   {
		   passCounter++;
		   pass.setLabel("Pass " + Integer.toString(passCounter));
		   selectionSort();
		   insertionSort();
		   repaint();		   
	   }
   }
}
