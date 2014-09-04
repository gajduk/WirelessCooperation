package options;

public interface OptionHandler <T> {
	
	public T getValue(String args[]);
	
	public T getValue(String args);
	
	public String getPossibleValues();
		
}
