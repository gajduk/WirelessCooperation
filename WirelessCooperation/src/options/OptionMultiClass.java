package options;

import java.util.HashMap;

public class OptionMultiClass <T> extends AbstractOptionHandler<T> {
	
	private HashMap<String,T> option_values;
	private String s_default_value;
	
	public HashMap<String, T> getOption_values() {
		return option_values;
	}
	
	public OptionMultiClass(String name, String description,
			 T default_value,String s_default_value,HashMap<String, T> option_values) {
		super(name,description,default_value);
		this.option_values = option_values;
		this.s_default_value = s_default_value;
	}
	
	public T getValue(String args[]) {
		return option_values.get(getValueString(args));
	}
	
	public String getSDefaultValue() {
		return s_default_value;
	}

	@Override
	public String getPossibleValues() {
		return option_values.keySet().toString();
	}

}
