package ross.goncharuk;

import org.jsoup.nodes.Element;
import ross.goncharuk.service.ElementProcessing;

import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String format = "utf8";
        String id = "make-everything-ok-button";
        String query = "#make-everything-ok-button, a[title=Make-Button], a[class=btn btn-success]";

        ElementProcessing elementProcessing = new ElementProcessing();

        //Universal foreach loop to iterate all parameter arguments by provided query

        for (String parameter : args){
            Element element = elementProcessing.getElementByQuery(new File(parameter), format, query);
            String path = elementProcessing.getElementsHtmlPath(element);
            System.out.println(path);
        }
        System.out.println();

        //Other options

        /*A separate processing and display for 1st and 2nd parameter arguments - as requested in task description

        Element firstPage = elementProcessing.getElementById(new File(args[0]), format, id);
        String firstPagesElementsPath = elementProcessing.getElementsHtmlPath(firstPage);
        System.out.println(firstPagesElementsPath);

        Element secondPage = elementProcessing.getElementByQuery(new File(args[1]), format, query);
        String secondPagesElementsPath = elementProcessing.getElementsHtmlPath(secondPage);
        System.out.println(secondPagesElementsPath);*/

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        /*Added a loop with some input options to provide a possibility to search for
        any other element either by ID or by a cssQuery.
        The search is being done in second parameter argument (arg[1])
        The result intentionally returns an element.toString() - just to give something to display
        */
        while (isRunning){
            System.out.println("Search element from 2nd page by: \n 1. id \n 2. query \n 3. Exit program");

            int input = scanner.nextInt();
            String idOrQuery;
            Element result;
            switch (input){
                case 1:
                    System.out.println("Enter id: ");
                    idOrQuery = scanner.next();
                    result = elementProcessing.getElementById(new File(args[1]),format,idOrQuery);
                    System.out.println("Element's details: " + result.toString());
                    break;
                case 2:
                    System.out.println("Enter query: ");
                    idOrQuery = scanner.next();
                    result = elementProcessing.getElementByQuery(new File(args[1]),format,idOrQuery);
                    System.out.println("Element's details: " + result.toString());
                    break;
                default:
                    isRunning = false;
            }
        }
    }
}
