package base;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.time.Duration.ofSeconds;


public class BasePageObject extends TestUtilities {

	protected WebDriver driver;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
		PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(5)), this);
	}
	@Override
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		}  catch (InterruptedException e) {
			log.error("sleep: FAILED {}" + e.getMessage());
			Thread.currentThread().interrupt(); /* this line will keep Thread.interrupted() returns true */
			throw new IllegalStateException ("Invalid sleep!");
		}
	}

	/**
	 * Open page with given URL
	 */
	@Override
	protected void openUrl(String url) {
		driver.get(url);
	}

	/**
	 * Find element using given locator
	 */
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	/**
	 * Find all elements using given locator
	 */
	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * Click on element with given locator when its visible
	 */
	protected boolean click(WebElement el) {

		if (el == null)
			return false;

		for (int i = 0; i < 2; i++) { // 2 tries to make tap
			try {
				waitFor(ExpectedConditions.elementToBeClickable(el),10);
				Actions actions = new Actions(driver);
				actions.moveToElement(el).click().perform();
				sleep(200);
				return true;
			} catch (Exception e) {
				log.error("click(): FAILED\n" + e.getMessage());
			}
			log.error("click(): RETRY '" + i + "' ----------------");
		}
		return false;
	}
	protected boolean m_click(WebElement el) {
		if (el == null)
			return false;

		for (int i = 0; i < 3; i++) { // 3 tries to make tap
			try {
				waitFor(ExpectedConditions.elementToBeClickable(el),10);
				el.click();
				sleep(200);
				return true;
			} catch (Exception e) {
				log.error("click(): FAILED\n" + e.getMessage());
			}
			log.error("click(): RETRY '" + i + "' ----------------");
		}
		return false;
	}


//	/**
//	 * Type given text into element with given locator
//	 */
//	protected void type(WebElement element, String text) {
//		waitFor(ExpectedConditions.visibilityOf(element), 10);
//		element.sendKeys(text);
//	}

	/**
	 * Type given text into element with given Webelement
	 */
	protected boolean type(WebElement el, String text) {
		if (el == null)
			return false;

		for (int i = 0; i < 2; i++) { // 2 tries to make tap
			try {
				Actions actions = new Actions(driver);
				actions.sendKeys(el, text).perform();
				sleep(200);
				return true;
			} catch (Exception e) {
				log.error("type(): FAILED\n" + e.getMessage());
			}
			log.error("type(): RETRY '" + i + "' ----------------");
		}
		return false;
	}

	protected boolean mytype(WebElement el, String text) {
		if (el == null)
			return false;
		try {
			el.sendKeys(text);
			sleep(1000);
			return true;
		} catch (Exception e) {
			log.error("type(): FAILED\n" + e.getMessage());
		}
		return false;
	}

	protected boolean type_wait_visibility(WebElement el, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if (el == null)
			return false;
		try {
			wait.until(ExpectedConditions.visibilityOf(el)).sendKeys(text);
			sleep(1000);
			return true;
		} catch (Exception e) {
			log.error("type(): FAILED\n" + e.getMessage());
		}
		return false;
	}



	/**
	 * Type given text into element with given Webelement
	 */
	protected String text(WebElement el) {
		String a=null;
		int count = 0;
		boolean succesed = false;
		while (count < 2 && !succesed)
			try {
				a=el.getText();
				succesed = true;
				sleep(200);
			} catch (StaleElementReferenceException e) {
				e.toString();
				log.info("Trying to recover from a stale element :" + e.getMessage());
				count = count + 1;
			}
		return a;
	}

	protected WebElement getEl(WebElement el) {
		try {
			return el;
		}
		catch(StaleElementReferenceException ex)
		{
			return el;
		}
	}



	public WebElement retry_getEl(WebElement element) {
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
				element.getSize();
				result = true;
				break;
			} catch(NoSuchElementException ex) {
			}
			attempts++;
		}
		return element;
	}


	public boolean IsDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("The element  " + element.getText() + " is Displayed");
			return true;
		} catch (NoSuchElementException ex) {
			log.warn("The element  " + element + " is not Displayed");
			return false;

		}
	}

	/**
	 * Click on element with given locator when its visible
	 */
	protected boolean getTextElement(WebElement el) {
		if (el == null)
			return false;

		for (int i = 0; i < 2; i++) { // 2 tries to make tap
			try {
				el.getText();
				return true;
			} catch (Exception e) {
				log.error("click(): FAILED\n" + e.getMessage());
			}
			log.error("click(): RETRY '" + i + "' ----------------");
		}
		return false;
	}

	/**
	 *
	 text into element with given element
	 */
	protected String getText(WebElement element) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOf(element), 10);
				log.info("Get tex of element");
				return element.getText();
			} catch (StaleElementReferenceException e) {
				log.error("getText(): not found");
			}
			attempts++;

		}
		return  null;
	}


	/** Get URL of current page from browser */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/** Get title of current page */
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}

	/** Get source of current page */
	public String getCurrentPageSource() {
		return driver.getPageSource();
	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 0;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(WebElement element, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOf(element),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	protected void waitForClickable(WebElement element, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.elementToBeClickable(element),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	protected boolean retry_click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if (element == null)
			return false;
		for (int i = 0; i < 3; i++) { // 3 tries to make tap
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				sleep(2000);
				return true;
			} catch (Exception e) {
				log.error("click(): FAILED\n" + e.getMessage());
			}
			log.error("click(): RETRY '" + i + "' ----------------");
		}
		return false;
	}

	/** Wait for alert present and then switch to it */
	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert();
	}

	public void switchToWindowWithTitle(String expectedTitle) {
		// Switching to new window
		String firstWindow = driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> windowsIterator = allWindows.iterator();

		while (windowsIterator.hasNext()) {
			String windowHandle = windowsIterator.next().toString();
			if (!windowHandle.equals(firstWindow)) {
				driver.switchTo().window(windowHandle);
				if (getCurrentPageTitle().equals(expectedTitle)) {
					break;
				}
			}
		}
	}

	/** Switch to iFrame using it's locator */
	@Override
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	/** Press Key on locator */
	protected void pressKey(By locator, Keys key) {
		find(locator).sendKeys(key);
	}

	/** Press Key using Actions class */
	public void pressKeyWithActions(Keys key) {
		log.info("Pressing " + key.name() + " using Actions class");
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
	}

	/** Perform scroll to the bottom */
	public void scrollToBottom() {
		log.info("Scrolling to the bottom of the page");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}


	/** Drag 'from' element to 'to' element */
	protected void performDragAndDrop(By from, By to) {
		// Actions action = new Actions(driver);
		// action.dragAndDrop(find(from), find(to)).build().perform();
	}
	/** Perform mouse hover over element */
	protected void hoverOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	protected  void scrollAndClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver ;
		executor.executeScript("arguments[0].click();", element);

	}
	@Override
	protected void scroolToElement(WebElement element){
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

	}
	protected void scrool_page_up(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)", "");

	}
	protected void scrool_page_down(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)", "");

	}


	protected boolean mouse_move(WebElement el) {
		sleep(200);
		if (el == null)
			return false;
		for (int i = 0; i < 2; i++) { // 2 tries to make tap
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(el).perform();
				sleep(200);
				return true;
			} catch (Exception e) {
				log.error("move mouse() : FAILED\n" + e.getMessage());
			}
			log.error("move mouse(): RETRY '" + i + "' ----------------");
		}
		return false;
	}
	/** Add cookie */
	public void setCookie(Cookie ck) {
		log.info("Adding cookie " + ck.getName());
		driver.manage().addCookie(ck);
		log.info("Cookie added");
	}

	/** Get cookie value using cookie name */
	public String getCookie(String name) {
		log.info("Getting value of cookie " + name);
		return driver.manage().getCookieNamed(name).getValue();
	}

	/** Open page with given URL */
	protected void openUrl1(String url) {
		driver.get(url);
	}




}
