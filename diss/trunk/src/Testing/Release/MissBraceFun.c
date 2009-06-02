int functionX(int x)
{
	int constantInt = 54;

	return x + constantInt;
}

char functionY(int y, char z)
{
	return (char) z + y;


int main(void)
{
	int a, b;
	char c;
	a = b = 7;
	c = 'p';

	functionX(a);

	return 0;
}
