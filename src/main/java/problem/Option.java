package problem;

import java.lang.IllegalArgumentException;

import java.util.List;
import java.util.Map;

public class Option implements IOption{
    private String name;
    private String shortName;
    private Boolean required;
    private Boolean hasArg;
    private Boolean exists;

    private String value;
    private List<String> requiredOptions;

    public Option(String name, String shortName, Boolean required, Boolean hasArg, List<String> requiredOptions) {
        this.name = name;
        this.shortName = shortName;
        this.required = required;
        this.hasArg = hasArg;
        this.requiredOptions = requiredOptions;
    }

    public boolean match(String[] args) throws IllegalArgumentException {
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

    public void checkRequiredOptions(Map<String, IOption> optionMap) throws IllegalArgumentException {
        for (String opt: requiredOptions) {
            if (!optionMap.get(opt).getExist())
                throw new IllegalArgumentException("Option %d requires some other options".format(this.name));
        }
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
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
