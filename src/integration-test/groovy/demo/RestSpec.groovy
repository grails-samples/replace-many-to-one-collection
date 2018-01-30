package demo

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import spock.lang.Specification

class RestSpec extends Specification {

    RestBuilder restBuilder = new RestBuilder()

    RestResponse get(String path, Closure cls) {
        restBuilder.get("http://localhost:${serverPort}${path}", cls)
    }

    RestResponse put(String path, Closure cls) {
        restBuilder.put("http://localhost:${serverPort}${path}", cls)
    }
}
