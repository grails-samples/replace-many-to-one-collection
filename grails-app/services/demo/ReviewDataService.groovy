package demo

import grails.gorm.services.Service
import grails.gorm.services.Where
import groovy.transform.CompileStatic

@CompileStatic
@Service(Review)
interface ReviewDataService {
    Review save(String quote, String author, Book book)

    @Where({id in ids})
    void delete(List<Serializable> ids)
}