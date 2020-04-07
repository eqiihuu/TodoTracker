package problem;

import java.util.*;

public class Command {
    private Map<String, IOption> optionMap; //Map<String, Option>
    private static final String CSV_FILE = "--csv-file";
    private static final String ADD_TODO = "--add-todo";
    private static final String TODO_TEXT = "--todo-text";
    private static final String COMPLETE = "--completed";
    private static final String DUE = "--due";
    private static final String PRIORITY = "--priority";
    private static final String CATEGORY = "--category";
    private static final String COMPLETE_TODO = "--complete-todo";
    private static final String DISPLAY = "--display";

    private static final String SHOW_INCOMPLETE = "--show-incomplete";
    private static final String SHOW_CATEGORY = "--show-category";
    private static final String SORT_BY_DATE = "--sort-by-date";
    private static final String SORT_BY_PRIORITY = "--sort-by-priority";


    public Command() {
        // Create option list
        optionMap = new HashMap<>();
        // Create and add all options to the option list
        optionMap.put(CSV_FILE, new Option(CSV_FILE, "-f",true, true, Arrays.asList()));
        // Add_Todo
        optionMap.put(ADD_TODO, new Option(ADD_TODO, "-a", false, false, Arrays.asList(TODO_TEXT)));
        optionMap.put(TODO_TEXT, new Option(TODO_TEXT, "-t", false, true, Arrays.asList(ADD_TODO)));
        optionMap.put(COMPLETE, new Option(COMPLETE, "-c", false, false, Arrays.asList(ADD_TODO)));
        optionMap.put(DUE, new Option(DUE, "-d", false, true, Arrays.asList(ADD_TODO)));
        optionMap.put(PRIORITY, new Option(PRIORITY, "-p", false, true, Arrays.asList(ADD_TODO)));
        optionMap.put(CATEGORY, new Option(CATEGORY, "-g", false, true, Arrays.asList(ADD_TODO)));
        // Complete_Todo
        optionMap.put(COMPLETE_TODO, new Option(COMPLETE_TODO, "-l", false, true, Arrays.asList()));
        //Display
        optionMap.put(DISPLAY, new Option(DISPLAY, "-i", false, true, Arrays.asList()));
        optionMap.put(SHOW_INCOMPLETE, new Option(SHOW_INCOMPLETE, "-s", false, true, Arrays.asList(DISPLAY)));
        optionMap.put(SHOW_CATEGORY, new Option(SHOW_CATEGORY, "-s", false, true, Arrays.asList(DISPLAY)));
        optionMap.put(SORT_BY_DATE, new Option(SORT_BY_DATE, "-s", false, false, Arrays.asList(DISPLAY)));
        optionMap.put(SORT_BY_PRIORITY, new Option(SORT_BY_PRIORITY, "-s", false, false, Arrays.asList(DISPLAY)));
    }


    public void parseArgs(String[] args) throws IllegalArgumentException {
        for (String k: optionMap.keySet()) {
            IOption opt = optionMap.get(k);
            opt.match(args);
        }
        for (String k: optionMap.keySet()) {
            IOption opt = optionMap.get(k);
            opt.checkRequiredOptions(optionMap);
        }
    }

    public boolean process() {
        if (optionMap.get(ADD_TODO).getExist()) // optionList.get("addTodo").getExists()
            addTodo();
        if (optionMap.get(COMPLETE_TODO).getExist())
            completeTodo();
        if (optionMap.get(DISPLAY).getExist())
            display();
        return true;
    }

    //TODO:
    public boolean addTodo() {
        return true;
    }

    //TODO:
    public boolean completeTodo() {
        return true;
    }

    //TODO:
    public boolean display() {
        return true;
    }
}
