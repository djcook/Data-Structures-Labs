public class TimeTester
{
	public static void main(String[] args)
	{
		Time test = new Time();
		System.out.printf("%s\n",test.toString());
		test.addMinutes(-270);
		System.out.printf("%s\n",test.toString());
		Time test2 = new Time(1430);
		System.out.printf("%s\n", test2.toString());
		System.out.printf("%s\n", test2.getMilitary());
		test2.addHours(-870);
		System.out.printf("%s\n", test2.toString());
		System.out.printf("%s\n", test2.getMilitary());
		test2.addMinutes(-540);
		System.out.printf("%s\n", test2.toString());
		System.out.printf("%s\n", test2.getMilitary());
	}

}
