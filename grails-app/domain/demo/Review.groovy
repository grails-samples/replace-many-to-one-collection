package demo

import grails.compiler.GrailsCompileStatic
import grails.gorm.annotation.Entity

@GrailsCompileStatic
@Entity
class Review {
    String author
    String quote
    static belongsTo = [book: Book]

    static constraints = {
        quote nullable: true
    }

    static mapping = {
        version false
        quote type: 'text'
    }
}