package test

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.ComponentScan
import static io.restassured.RestAssured.given
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.notNullValue

import spock.lang.Specification

@SpringBootTest(classes = [Application.class] , webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = "com")
@EnableAutoConfiguration
class GetUserReposSpecification extends Specification{

	@LocalServerPort
	private int port;

	def "Get user repos without filter"() {

		given: 'Get user with repos'
		def requestSpecification = given().port(port)

		when: 'Calling the endpoint'
		def responseSpecification = requestSpecification
				.when()
				.get("/userrepos")

		then: 'should receive RID in the response same as request'
		responseSpecification
				.then()
				.body("GitHub", notNullValue())
				.body("GitLab", notNullValue())
				.body()
				.statusCode(200)
	}
	
	
	def "Get user repos with filter"() {
		
				given: 'Get user with repos'
				def requestSpecification = given().port(port).param("owenership", true)
		
				when: 'Calling the endpoint'
				def responseSpecification = requestSpecification
						.when()
						.get("/userrepos")
		
				then: 'should receive RID in the response same as request'
				responseSpecification
						.then()
						.body("GitHub", notNullValue())
						.body("GitLab", notNullValue())
						.body()
						.statusCode(200)
			}
	
}