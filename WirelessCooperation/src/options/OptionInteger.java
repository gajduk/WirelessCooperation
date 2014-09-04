package options;

public class OptionInteger extends AbstractOptionHandler<Integer> {

	public OptionInteger(String name, String description, Integer default_value) {
		super(name, description, default_value);
	}

	@Override
	public Integer getValue(String[] args) {
		return Integer.parseInt(getValueString(args));
	}

	@Override
	public String getPossibleValues() {
		return "any integer";
	}
	
}
