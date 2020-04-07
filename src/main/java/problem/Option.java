package problem.model;

import java.lang.IllegalArgumentException;

import java.util.List;

public class Option {
    private String name;
    private String shortName;
    private Boolean required;
    private Boolean hasArg;
    private Boolean exists;

    private String value;
    private List<Option> requiredOptions;

    public Option(String name, String shortName, Boolean required, Boolean hasArg, List<Option> requiredOptions) {
        this.name = name;
        this.shortName = shortName;
        this.required = required;
        this.hasArg = hasArg;
        this.requiredOptions = requiredOptions;
    }

    public boolean match(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (this.name.equals(arg) || this.shortName.equals(arg)) {
                if (this.hasArg && (i == args.length - 1 || args[i+1].charAt(0) == '-'))
                    throw new IllegalArgumentException("Option %d should have a value".format(this.name));
                else
                    this.value = args[i+1];
                this.exists = true;
                return true;
            }
        }
        if (!checkExistance())
            throw new IllegalArgumentException("Option %d is required".format(this.name));
        return false;
    }

    public boolean checkRequiredOptions() {
        for (Option opt: requiredOptions) {
            if (!opt.exists)
                return false;
        }
        return true;
    }

    public boolean addRequiredOption(Option opt) {
        if (requiredOptions.contains(opt))
            return false;
        requiredOptions.add(opt);
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean getExist() {
        return this.exists;
    }

    private boolean checkExistance() {
        if (!required)
            return true;
        else
            return exists;
    }

}
