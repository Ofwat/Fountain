import spock.lang.Stepwise;
import functional.pages.FountainHomePage;
import geb.Browser;
import geb.spock.GebSpec


@Stepwise
class HelloGebSpec extends GebSpec{
	
	def 'go to login'() {
        when:
		    to FountainHomePage
			//waitFor { companyType.text() != "place holder" }
			
        then:
    	   	//browser.page.title == "Fountain: Home"
			assert at(FountainHomePage)
    }
	
	def 'select all current WASC option and get report'(){
		when:
			at(FountainHomePage)
		then:
			companyType.value(2)
		then:
			waitFor FountainHomePage.checkDropDowns //we can wait for the other boxes to be populated
		then:
			//click the go button. 
			goButton.click()
	}


		
}


