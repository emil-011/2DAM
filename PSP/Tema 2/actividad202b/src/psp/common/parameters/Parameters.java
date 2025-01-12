package psp.common.parameters;

public interface Parameters {

	/**
	 * Procces arguments and extract them
	 * 
	 * @param arguments Argument from command line
	 * @return True if parameters Ok, false if not
	 */
	boolean proccessArguments(String[] arguments);

	/**
	 * Obteins integer parameter value in given position
	 * 
	 * @param position Position of the parameter in the command line
	 * @return Value of parameter
	 * @throws ParametersException If invalid position or parameter is not integer
	 */
	int getIntParameter(int position);

	/**
	 * Obteins integer parameter value in given position
	 * 
	 * @param position Position of the parameter in the command line
	 * @return Value of parameter
	 * @throws ParametersException If invalid position or parameter is not long
	 */
	long getLongParameter(int position);

	/**
	 * Obteins double parameter value in given position
	 * 
	 * @param position Position of the parameter in the command line
	 * @return Value of parameter
	 * @throws ParametersException If invalid position or parameter is not double
	 */
	double getDoubleParameter(int position);

	/**
	 * Obteins string parameter value in given position
	 * 
	 * @param position Position of the parameter in the command line
	 * @return Value of parameter
	 * @throws ParametersException If invalid position or parameter is not string
	 */
	String getStringParameter(int position);
}
