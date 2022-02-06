package tandt.automation.testng;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import tandt.common.Log;
import tandt.commontest.model.ResultType;
import tandt.commontest.model.TestCase;
import tandt.commontest.model.TestResult;
import tandt.commontest.model.TestSuite;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class WebTestngListener extends TestListenerAdapter {
    private Log log = new Log(WebTestngListener.class);
    private TestSuite suite;
    private List<TestCase> testCases;
    private TestCase testCase;

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);

        String testName = result.getName();
        String description = getTestDescription(result);
        String params = getTestParams(result);

        testCase = new TestCase();
        testCase.setName(testName);
        testCase.setDescription(description);
        testCase.setStart(System.currentTimeMillis());

        startTestCase(testName, description, params);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);

        TestResult testResult = new TestResult();
        testResult.setResult(ResultType.PASS);
        testCase.setResult(testResult);
        testCases.add(testCase);

        endTestCase(getTestName(result), "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);

        String testName = result.getName();
        String message = getTestMessage(result);
        String stackTrace = getStackTraceString(result.getThrowable());

        TestResult testResult = new TestResult();
        testResult.setResult(ResultType.FAIL);
        testResult.setMessage(message);
        testResult.setMessage(stackTrace);
        testCase.setResult(testResult);
        testCases.add(testCase);

        endTestCase(testName, "FAILED", message, getStackTraceStringArray(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);

        String testName = result.getName();
        String message = getTestMessage(result);
        String stackTrace = getStackTraceString(result.getThrowable());

        TestResult testResult = new TestResult();
        testResult.setResult(ResultType.SKIPPED);
        testResult.setMessage(message);
        testResult.setMessage(stackTrace);
        testCase.setResult(testResult);
        testCases.add(testCase);

        endTestCase(testName, "SKIPPED", message, getStackTraceStringArray(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        onTestFailedButWithinSuccessPercent(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);

        String testName = result.getName();
        String message = getTestMessage(result);
        String stackTrace = getStackTraceString(result.getThrowable());

        TestResult testResult = new TestResult();
        testResult.setResult(ResultType.SKIPPED);
        testResult.setMessage(message);
        testResult.setMessage(stackTrace);
        testCase.setResult(testResult);
        testCases.add(testCase);

        endTestCase(testName, "SKIPPED", message, getStackTraceStringArray(result));
    }

    @Override
    public void onStart(ITestContext context) {
        super.onStart(context);

        suite = new TestSuite();
        suite.setName(context.getName());
        testCases = new ArrayList<>();
        suite.setTestcases(testCases);

        startTestSuite(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
        finishTestSuite(context.getName(), getPassedTests().size(), getFailedTests().size(), getSkippedTests().size());
    }

    private void startTestCase(String testName, String description, String params) {

        log.info("------------------------- Started Test '" + testName + "' -------------------------");
        log.info("Decription: " + description);
        log.info("Parameters: " + params);
    }

    private void endTestCase(String testName, String result) {

        log.info("Result: " + result);
        log.info("------------------------- Ended Test '" + testName + "' -------------------------");
    }

    private void endTestCase(String testName, String result, String testMessage, String[] stackTrace) {
        log.info("Result: " + result);
        log.info("Message: " + testMessage);
        log.error(stackTrace);
        log.info("------------------------- Ended Test '" + testName + "' -------------------------");
    }

    private void startTestSuite(String test) {

        log.info("***** START TEST SUITE: " + test);
    }

    private void finishTestSuite(String name, int pass, int fail, int skip) {
        log.info("***** END TEST SUITE: " + name + " (" + "PASSED: " + pass + "; " + "FAILED: " + fail + "; " + "SKIPPED: " + skip + ")");
    }

    private void onTestFailedButWithinSuccessPercent(ITestResult result) {
        log.info("Test failed but it is in defined success ratio " + getTestName(result));
    }

    /**
     * getTestName method
     *
     * @param tr
     * @return String
     */
    public String getTestName(ITestResult tr) {
        return tr.getName();
    }

    /**
     * getTestDescription method
     *
     * @param tr
     * @return String
     */
    private String getTestDescription(ITestResult tr) {
        String message = "";
        if (tr.getParameters().length > 1) {
            message = ": " + tr.getParameters()[1].toString();
        }
        return message;
    }

    /**
     * getTestParams method
     *
     * @param tr
     * @return String
     */
    public String getTestParams(ITestResult tr) {
        int iLength = tr.getParameters().length;
        StringBuilder message = new StringBuilder();

        if (tr.getParameters().length > 0) {
            message = new StringBuilder(tr.getParameters()[0].toString());

            for (int iCount = 0; iCount < iLength; iCount++) {
                if (iCount == 0) {
                    message = new StringBuilder(tr.getParameters()[0].toString());
                } else {
                    message.append(", ").append(tr.getParameters()[iCount].toString());
                }
            }
        }

        return message.toString();
    }

    /**
     * getTestMessage method
     *
     * @param result
     * @return String
     */
    public String getTestMessage(ITestResult result) {
        boolean found = false;

        if (result != null && result.getThrowable() != null) {
            found = true;
        }

        if (found) {
            return result.getThrowable().getMessage() == null ? "" : result.getThrowable().getMessage();
        } else {
            return "";
        }
    }

    private String[] getStackTraceStringArray(ITestResult tr) {
        StackTraceElement[] stackTraces = tr.getThrowable().getStackTrace();
        String[] stackTraceLogs = new String[stackTraces.length];
        for (int index = 0; index < stackTraces.length; index++) {
            stackTraceLogs[index] = stackTraces[index].toString();
        }
        return stackTraceLogs;
    }

    private String getStackTraceString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.getBuffer().toString();
    }
}
