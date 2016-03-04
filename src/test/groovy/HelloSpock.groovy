
class HelloSpock extends spock.lang.Specification {
  def "length of Spock's and his friends' names"() {

	println "Running sample Spock spec"
	  
	expect:
    name.size() == length

    where:
    name     | length
    "Spock"  | 5
    "Kirk"   | 4
    "Scotty" | 6
  }
}  