public class EmptyStackError implements BalanceError {

	public int line;

	public EmptyStackError(int lineNumber)
	{
		line = lineNumber;
	}

	public String toString()
	{
		return "EmptyStackError: {line:" + line + "}";
	}
}
