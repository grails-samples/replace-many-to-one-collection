package demo

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

@GrailsCompileStatic
class BookUpdateCommand implements Validateable {
    String name
    List<BookReview> reviews

    static constraints = {
        name nullable: false, blank: false
        reviews nullable: true, minSize: 0
    }
}