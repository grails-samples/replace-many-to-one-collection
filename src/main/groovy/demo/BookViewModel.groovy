package demo

import groovy.transform.CompileStatic

@CompileStatic
class BookViewModel {
    String name
    List<String> reviews

    static BookViewModel of(Book p) {
        new BookViewModel(name: p.name, reviews: p.reviews*.quote as List<String>)
    }
}
