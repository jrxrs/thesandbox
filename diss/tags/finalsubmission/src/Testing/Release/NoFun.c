int functionX(int x)
{
	int constantInt = 54;

	return x + constantInt;
}

char functionY(int y, char z)
{
	doesNotExist(y);
	return (char) z + y;
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
