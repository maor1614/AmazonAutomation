package base;

import excelData.JSONReader;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestUtilities extends BaseTest {

	protected void openUrl(String url) {
		log.info("get new  url:  " + url);
		getDriver().get(url);
	}

	protected void openNewTab(String url) {
		getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(0));
		getDriver().get(url);
	}

	// STATIC SLEEP
	protected void sleep(long millis) {
		try {
			log.info("sleeping a : " + millis + " millisecond");
			Thread.sleep(millis);
		} catch (InterruptedException ie) {
			log.error("InterruptedException: ", ie);
			Thread.currentThread().interrupt();
		}
	}

	protected JSONReader dJson(String JsonPath) {
		JSONReader readJ = new JSONReader(JsonPath);
		return readJ;
	}

	@DataProvider(name = "files")
	protected static Object[][] files() {
		return new Object[][]{
				{1, "index.html"},
				{2, "logo.png"},
				{3, "text.txt"}
		};
	}


	/**
	 * Switch to iFrame using it's locator
	 */
	protected void switchToFrame(WebElement element) {
		log.info("swich to frame :" + " " + element);
		getDriver().switchTo().frame(element);
	}

	/**
	 * Todays date in yyyyMMdd format
	 */
	private static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}

	/**
	 * Current time in HHmmssSSS
	 */
	private String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
	}

	/**
	 * Get logs from browser console
	 */
	protected List<LogEntry> getBrowserLogs() {
		LogEntries log = getDriver().manage().logs().get("browser");
		List<LogEntry> logList = log.getAll();
		return logList;
	}

	protected void scroll_down_by_y(int y) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0," + y + ")", "");

	}
	protected void scroll_page_down_end(){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	public boolean ElementsIsDisplayed(WebElement element) {
		boolean isElementDisplayed;
		try {
			isElementDisplayed = element.isDisplayed();
			return isElementDisplayed;
		} catch (Exception e) {
			isElementDisplayed = false;
			log.error(e.getMessage());
			return isElementDisplayed;

		}
	}

	public boolean elementsIsEnabled(WebElement element) {
		try {
			element.isEnabled();
			log.info("The element is Displayed");
			return true;
		} catch (NoSuchElementException ex) {
			log.error("The element  is not Displayed");
			return false;

		}
	}

	public void swichWindow() {
		for (String winHandle : getDriver().getWindowHandles()) {
			getDriver().switchTo().window(winHandle);
		}
	}

	public boolean waitForElement(WebElement element, int seconds) {
		boolean isElementPresent;
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isElementPresent = element.isDisplayed();
			sleep(200);
			return isElementPresent;
		} catch (Exception e) {
			isElementPresent = false;
			System.out.println(e.getMessage());
			return isElementPresent;
		}
	}


	protected String Replace(String element) {
		String str = element;
		str = str.replaceAll("[^0-9.]", "");
		return str;
	}

	protected void click_text_link(String text) {
		List<WebElement> allLinks = getDriver().findElements(By.tagName("a"));
		for (WebElement specificlink : allLinks) {
			if (specificlink.getText().equals(text)) {
				specificlink.click();
				break;
			}
		}
	}

	protected void click_text_linkJS(String text) {
		List<WebElement> allLinks = getDriver().findElements(By.tagName("a"));
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		for (WebElement specificlink : allLinks) {
			if (specificlink.getText().equals(text)) {
				wait.until(ExpectedConditions.elementToBeClickable(specificlink));
				JavascriptExecutor executor = (JavascriptExecutor) getDriver();
				executor.executeScript("arguments[0].click();", specificlink);
				break;
			}
		}
	}

	protected double ReplaceAndDouble(WebElement element) {
		String str = element.getText();
		str = str.replaceAll("[^0-9.]", "");
		double num = Double.valueOf(str);
		return num;
	}

	protected double ReplaceAndDouble_text(String text) {
		String str = text;
		str = str.replaceAll("[^0-9.]", "");
		double num = Double.valueOf(str);
		return num;
	}

	protected void scroolToElement(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);

	}


	protected boolean waitElementDisplayed(WebElement el) {
		if (el == null)
			return false;

		for (int i = 0; i < 2; i++) { // 2 tries to make tap
			try {
				el.isDisplayed();
				return true;
			} catch (Exception e) {
				log.error("waitElement: FAILED\n" + e.getMessage());
			}
			log.error("waitElement(): RETRY '" + i + "' ----------------");
		}
		return false;
	}


	protected String extract_NumbersOnly(String element) {
		String str = element;
		str = str.replaceAll("[^0-9.]", "");
		return str;
	}

	protected String extract_LettersOnly(String element) {
		String str = element;
		str = str.replaceAll("[^a-zA-Z ]", "");
		return str;
	}

	protected List<String> addStringsToList(List<String> lst) {
		List<String> listStrings = new ArrayList<String>();
		listStrings.clear();
		for (String a : lst) {
			listStrings.add(a);
		}
		return listStrings;
	}

	protected List<String> convertFromWebElentsToString(List<WebElement> lst) {
		List<String> listStrings = new ArrayList<String>();
		listStrings.clear();
		for (WebElement a : lst) {
			listStrings.add(a.getText());

		}
		return listStrings;
	}

	protected Boolean compareTwoStringLists(List<String> list1, List<String> list2) {
		for (final String s1 : list1) {
			int i;
			for (i = 0; i < list2.size(); i++) {
				if (s1.equals(list2.get(i))) {
					break;
				}
			}
			if (i == list2.size()) {
				return false;
			}
		}
		return true;
	}

	protected List<String> compareTwoStringLists2(List<String> list1, List<String> list2) {
		List<String> differences = list1.stream()
				.filter(element -> !list2.contains(element))
				.collect(Collectors.toList());
		return differences;
	}

	protected List<String> text_Split(String text) {
		Pattern pattern = Pattern.compile("[,;:]");
		String[] results = pattern.split(text);
		List<String> list = Arrays.asList(results);
		return list;
	}

	protected void waitForPageLoad(WebDriver driver) {
		new WebDriverWait(driver, 180).until((ExpectedCondition<Boolean>) wd ->
				((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	protected void scroll_page_up() {
		sleep(700);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,-1200)", "");

	}

	protected void scroll_page_down() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,500)", "");

	}

	protected void scroll_page_downN() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,1200)", "");

	}


	}


