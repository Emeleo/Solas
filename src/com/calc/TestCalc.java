package com.calc;

public class TestCalc implements BasicCalc {
	int str;
	int bon;
public TestCalc(int Strength, int Bonus){
str=Strength;
bon=Bonus;
}
	@Override
	public int doCalc() {
	//Square root of your bonus + your Strength / 2 and add 20
		return (int) (((str+Math.sqrt(bon))/2)+20) ;
	}

	@Override
	public String Name() {
		// TODO Auto-generated method stub
		return "TestCalc";
	}

	@Override
	public String Description() {
		// TODO Auto-generated method stub
		return "Determines Your max hit....In bed...";
	}

}
