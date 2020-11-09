public class NonEmptyStackError implements BalanceError {

	public char top;
	public int size;

	public NonEmptyStackError(char topOfStack, int sizeOfStack)
	{
		top = topOfStack;
		size = sizeOfStack;
	}

	public String toString()
	{
		return "NonEmptyStackError: {top:" + top + ", size:" + size + "}";
	}
}
