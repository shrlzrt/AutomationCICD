package sharlynzarate.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 2; //It will rerun 2x after failure

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}
}
