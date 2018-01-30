package demo

import grails.compiler.GrailsCompileStatic
import grails.gorm.annotation.Entity

@GrailsCompileStatic
@Entity
class Book {
    String name
    static hasMany = [reviews: Review]

    static constraints = {
        name nullable: true
    }

    static mapping = {
        version false
    }
}