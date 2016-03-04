import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver


//System.setProperty("geb.env", "remote_ie") //This needs to be passed from the build SW.

//Load the test props
Properties prop = new Properties()
InputStream ip = this.getClass().getClassLoader().getResourceAsStream("test.properties")
prop.load(ip)
ip.close()


println prop.get("test.webdriver.chrome.driver")

//The following need to come from the test.properties.
System.setProperty("webdriver.chrome.driver", prop.get("test.webdriver.chrome.driver").toString())
System.setProperty("webdriver.ie.driver", prop.get("test.webdriver.ie.driver").toString())
System.setProperty("webdriver.firefox.bin", prop.get("test.webdriver.firefox.bin").toString())

System.setProperty("webdriver.ie.driver.loglevel", prop.get("test.webdriver.ie.driver.loglevel").toString())
System.setProperty("webdriver.ie.driver.logfile", prop.get("test.webdriver.ie.driver.logfile").toString())

println "webdriver.ie.driver = " + System.getProperty("webdriver.ie.driver")
println "webdriver.ie.driver.loglevel = " + System.getProperty("webdriver.ie.driver.loglevel")
println "webdriver.ie.driver.logfile = " + System.getProperty("webdriver.ie.driver.logfile")

//quitCachedDriverOnShutdown = false

//default is to use firefox
//driver = { 

	//System.setProperty("unexpectedAlertBehaviour","ignore");
	
	//String property = System.getProperty( "webdriver.chrome.driver" );
	//DesiredCapabilities cap = new DesiredCapabailities()
	//cap.setPreference(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, org.openqa.selenium.UnexpectedAlertBehaviour.IGNORE)
	//return new InternetExplorerDriver(cap)
	
	//return new FirefoxDriver()
	//return a remote web driver!
	
	//Local FF
	//File pathToBinary = new File("D:\\Firefox\\Firefox.exe");
	//FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	
	//Local FF - JT	
	//FirefoxProfile firefoxProfile = new FirefoxProfile();	
	//FirefoxDriver _driver = new FirefoxDriver(ffBinary,firefoxProfile);
	//FirefoxDriver _driver = new FirefoxDriver(firefoxProfile);
	
	//return _driver
	
	//remote IE
	//new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), DesiredCapabilities.internetExplorer())
//}

println System.getProperty( "geb.env" );


waiting {
	timeout = 15
	retryInterval = 0.5
}


switch(System.getProperty( "geb.env" )){
	case 'ie':
		println "getting IE driver"
		driver = {
			return new InternetExplorerDriver()
		}
	break
	case 'chrome':
		driver = {
			return new ChromeDriver();
		}
	break
	case 'firefox':
		driver = {
//			FirefoxProfile firefoxProfile = new FirefoxProfile(new File("C:/Users/adam.edgar/AppData/Roaming/Mozilla/Firefox/Profiles/xzyybowa.default"));
//			FirefoxProfile firefoxProfile = new FirefoxProfile(new File("C:/Users/reservoir.admin/AppData/Roaming/Mozilla/Firefox/Profiles/a6izpfvc.default"));
			FirefoxProfile firefoxProfile = new FirefoxProfile(new File("C:/Users/james.toddington/AppData/Roaming/Mozilla/Firefox/Profiles/4xf933sp.default"));
			FirefoxDriver _driver = new FirefoxDriver(firefoxProfile);
			return _driver
		}
	break
	case 'remote_ie':
		driver = {
			DesiredCapabilities dp = DesiredCapabilities.internetExplorer()
			dp.setCapability("version", 11)
			return new RemoteWebDriver(new URL("http://172.16.9.144:4444/wd/hub"), dp)
			//return new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.internetExplorer())
		}
	break
	case 'remote_firefox':
		driver = {
			FirefoxProfile firefoxProfile = new FirefoxProfile(new File("C:/Users/james.toddington/AppData/Roaming/Mozilla/Firefox/Profiles/4xf933sp.default"));
			FirefoxDriver _driver = new RemoteWebDriver(new URL("http://172.16.9.144:4444/wd/hub"), new FirefoxDriver(firefoxProfile));
			return _driver
		}
	break
	default:
		return new ChromeDriver()
	break
} 



