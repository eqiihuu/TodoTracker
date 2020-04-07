package problem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command {
    private List<Option> optionList; //Map<String, Option>
    private static final String CSV_FILE = "--csv-file";
    private static final String ADD_TODO = "--add-todo";

    public Command() {
        // Create option list
        optionList = new ArrayList<>();
        // Create options
        Option csvFileOption = new Option(CSV_FILE, "-f",true, true, new ArrayList<Option>());
        Option addOption = new Option(ADD_TODO, "-a", false, false, new ArrayList<Option>());
        Option textOption = new Option("--todo-text", "-t", false, true, new ArrayList<Option>());
        Option completeOption = new Option("--completed", "-c", false, false, new ArrayList<Option>());
        //add required options for each option, if necessary
        addOption.addRequiredOption(textOption);
        textOption.addRequiredOption(addOption);
        completeOption.addRequiredOption(addOption);

        // add all options to the option list
        optionList.add(csvFileOption); // optionList.put("addTodo", addOption);
        optionList.add(addOption);
        optionList.add(textOption);
        optionList.add(completeOption);

    }


    public void parseArgs(String[] args) {
        for (int i = 0; i < optionList.size(); ++i) {
            Option opt = optionList.get(i);
            opt.match(args);
        }
        for (int i = 0; i < optionList.size(); ++i) {
            Option opt = optionList.get(i);
            if (!opt.checkRequiredOptions())
                throw new IllegalArgumentException("Option %d requires some other options".format(opt.getName()));
        }
    }

    public boolean process() {
        if (optionList.get(1).getExist()) // optionList.get("addTodo").getExists()
            addTodo();
        if (optionList.get(4).getExist())
            completeTodo();
        return true;
    }

    public boolean addTodo() {
        return true;
    }
    public boolean completeTodo() {
        return true;
    }

    private Map<String, Option> optionListToMap(List<Option> optionList) {
        Map<String, Option> optionMap = new HashMap<>();
        for (Option opt: optionList) {
            optionMap.put(opt.getName(), opt);
        }
        return optionMap;
    }
}
