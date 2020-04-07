package problem;

import java.util.Map;

public interface IOption {
    public boolean match(String[] args);

    public void checkRequiredOptions(Map<String, IOption> optionMap);

    public String getName();

    public String getShortName();

    public boolean getExist();
}
