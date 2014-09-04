package options;


public abstract class AbstractOptionHandler <T> implements OptionHandler<T> {

	private String name;
	private String description;
	private T default_value;
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public T getDefaultValue() {
		return default_value;
	}
	
	public String getSDefaultValue() {
		return default_value.toString();
	}

	public AbstractOptionHandler(String name, String description,
			T default_value) {
		super();
		this.name = name;
		this.description = description;
		this.default_value = default_value;
	}
	
	protected String getValueString(String args[]) {
		for ( int i = 0 ; i < args.length-1 ; ++i ) {
			if ( args[i].equals("-"+getName()) ) {
				return args[i+1];
			}
		}
		return getSDefaultValue();
	}
	
	public final T getValue(String args) {
		return getValue(args.split(" "));
	}
	
	public static String getHelpHeader() {
		return "    name  default value               possible values    descritpion";
	}
	
	@Override
	public String toString() {
		return String.format("%8s%15s%30s    %s", getName(),getSDefaultValue(),getPossibleValues(),getDescription());
	}
	
}
