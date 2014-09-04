package options;

public class OptionDouble extends AbstractOptionHandler<Double> {

	public OptionDouble(String name, String description, Double default_value) {
		super(name, description, default_value);
	}

	@Override
	public Double getValue(String[] args) {
		return Double.parseDouble(getValueString(args));
	}

	@Override
	public String getPossibleValues() {
		return "any double";
	}

}
