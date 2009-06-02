char functionY(int y, char z)
{
	unctonX(y);
	return (char) z + y;
}

int functionX(int x)
{
	int constantInt = 54;

	return x + constantInt;
}

int main(void)
{
	int a, b;
	char c;
	a = b = 7;
	c = 'p';

	functionY(a, c);

	return 0;
}
