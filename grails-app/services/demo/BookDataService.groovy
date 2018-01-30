package demo

import grails.gorm.services.Join
import grails.gorm.services.Service
import groovy.transform.CompileStatic

@CompileStatic
@Service(Book)
interface BookDataService {
    Book save(String name)
    Book find(String name)

    @Join('reviews')
    Book findJoinReviews(String name)
}