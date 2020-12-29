package gmailAPI.restAssured;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;


public class Log implements ITestListener{

    public Log() {  }
     
    // Initialize Log4j logs

    private static Logger Log = Logger.getLogger(Log.class.getName());

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName) {
        Log.info("****************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$ " + sTestCaseName + "  $$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.info("******************************************************************");
     }


    public static void endTestCase(String sTestCaseName) {
        Log.info("XXXXXXXXXXXXXXXX " + "-E---N---D-" + "   XXXXXXXXXXXXXX");
    }


    // Need to create these methods, so that they can be called

    public static void info(String message) {
          Log.info(message);
          ExtentTestManager.getTest().log(Status.INFO, message);
        }
     public static void warn(String message) {
         Log.warn(message);
         ExtentTestManager.getTest().log(Status.WARNING, message);
            }
     public static void error(String message) {
        Log.error(message);
        ExtentTestManager.getTest().log(Status.ERROR, message);
        }

    public static void fatal(String message) {
        Log.fatal(message);
        ExtentTestManager.getTest().log(Status.FATAL, message);
      }
    public static void debug(String message) {
        Log.debug(message);
        ExtentTestManager.getTest().log(Status.DEBUG, message);
      }
 
  public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentReport.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
}
