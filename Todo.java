//A java program which simulates the working of a Todo list on a command line interface.

//Importing required packages.
import java.util.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Todo{
    //Giving a standard name of file which will store the Todo list.
    public static String filename = "file.txt";
    public static void main(String[] args){
    // Enclosing this part in the try catch block because it is possible that the user will not provide any operation.
      try{
        // Depending upon the first argument we choose the operation to perform.
        switch(args[0]){
            case "add": add(args[1]);
            break;
            case "show": show();
            break;
            case "remove": remove(Integer.parseInt(args[1])-1);
            break;
            case "delete": delete();
            break;
            default: System.out.println("Operation not recognized");
        }
        // If the user does not provide any operation then we give them an introduction of what they can do.
      } catch(ArrayIndexOutOfBoundsException e){
          System.out.println("Welcome to Todo List maker. Use the following commands on command prompt to make and maintain your Todo list:");
          System.out.println("1. java Todo add [task]: To add a task in the list\n2. java Todo show: To display the Todo tasks.\n3. java Todo remove [task_index]: To remove a task.\n4. java Todo delete: To delete all the tasks.");
          System.out.println("To enter or remove a task having multiple words, enclose the task in \" \". Example: java Todo add \"Pay Bills\"");
      }
    }
    // A function to add a task in Todo list.
    public static void add(String task){
        // We simply create a file, if not present, and start writing in it line by line. 
        try{
            FileWriter fw = new FileWriter(filename, true);
            fw.write(task+"\n");
            fw.close();
            System.out.println(task+" added to the Todo list");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    // A function to show all the tasks currently present in the list.
    public static void show(){
        // Make a list of tasks and print while iterating through them.
        ArrayList<String> tasks = getArrayList();
        for(int i=0; i<tasks.size(); i++){
            System.out.println(i+1+". "+tasks.get(i));
        } 
    }
    // A function to remove a task by providing its serial number.
    public static void remove(int TaskToRemove){
        // Take the serial number of task as input.
        // Make a list of tasks and if the input number lies in the list then remove it and write the file again.
        ArrayList<String> tasks = getArrayList();
        if(TaskToRemove>=0 && TaskToRemove<tasks.size()){
            String RemovedTask = tasks.remove(TaskToRemove);
            File file = new File(filename);
            file.delete();
            try{
                FileWriter fw = new FileWriter(filename, true);
                for(int j=0; j<tasks.size(); j++){
                    fw.write(tasks.get(j)+"\n");
                } 
                fw.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println(RemovedTask+" is deleted from the Todo list.");
            return;
        }
        else{
            System.out.println("Task not found");
        }
    }
    // A function to delete the whole list.
    public static void delete(){
        // Delete the file if present.
        File file = new File(filename);
        if(file.delete()){
            System.out.println("All Todo list data has been deleted.");
        }
        else{
            System.out.println("No data available to delete.");
        }
    }
    // A function to convert the txt file of tasks into an ArrayList<String>.
    public static ArrayList<String> getArrayList(){
        try{
            FileReader fr = new FileReader(filename);
            String task;
            ArrayList<String> tasks = new ArrayList<String>();
            int iterator;
            // Attach a new item in the list everytime a new line character is found.
            while((iterator=fr.read())!=-1){
                task = "";
                while(iterator != 10){
                    task = task + (char)iterator;
                    iterator = fr.read();
                }
                tasks.add(task);
            }
            fr.close();
            return tasks;
        } catch(IOException e){
            // If file is not present then return an empty ArrayList<String>.
            return new ArrayList<String>();
        }
    }
}