package options;

public class OptionLong extends AbstractOptionHandler<Long> {

	public OptionLong(String name, String description, Long default_value) {
		super(name, description, default_value);
	}

	@Override
	public Long getValue(String[] args) {
		return Long.parseLong(getValueString(args));
	}

	@Override
	public String getPossibleValues() {
		return "any long";
	}
	
}
