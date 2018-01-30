package demo

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import groovy.json.JsonOutput

@Integration
class BookControllerSpec extends RestSpec {

    def "invoking updated replace the reviews collection"() {
        given:
        RestBuilder restBuilder = new RestBuilder()

        when:
        RestResponse restResponse = get("/book?name={name}") {
            urlVariables([name: 'Daemon'])
        }

        then:
        restResponse.status == 200
        restResponse.json.name == 'Daemon'
        restResponse.json.reviews.size() == 2
        restResponse.json.reviews*.author.sort() == ['Chicago Sun-Times', 'Paste Magazine'].sort()
        restResponse.json.reviews*.quote.sort() == ['Daemon does for surfing the Web what Jaws did for swimming in the ocean.', 'Daemon is wet-yourself scary, tech-savvy, mind-blowing!'].sort()

        when:
        restResponse = put("/book") {
            json(JsonOutput.toJson([name: 'Daemon',
            reviews: [[author: 'Slashdot.com', quote: 'This is a techno-thriller with a healthy dose of techno but absolutely no letdown on the thrill.']]]))
        }

        then:
        restResponse.status == 200
        restResponse.json.name == 'Daemon'
        restResponse.json.reviews.size() == 1
        restResponse.json.reviews*.author == ['Slashdot.com']
        restResponse.json.reviews*.quote.sort() == ['This is a techno-thriller with a healthy dose of techno but absolutely no letdown on the thrill.']

        when:
        restResponse = get("/book?name={name}") {
            urlVariables([name: 'Daemon'])
        }

        then:
        restResponse.status == 200
        restResponse.json.name == 'Daemon'
        restResponse.json.reviews.size() == 1
        restResponse.json.reviews*.author == ['Slashdot.com']
        restResponse.json.reviews*.quote.sort() == ['This is a techno-thriller with a healthy dose of techno but absolutely no letdown on the thrill.']
    }
}


