package demo

import groovy.transform.CompileStatic

@CompileStatic
class BookController {

    UpdateReviewsService updateReviewsService
    BookDataService bookDataService

    def index(String name) {
        if ( !name ) {
            render status: 422
            return
        }
        Book book = bookDataService.findJoinReviews(name)
        if ( !book ) {
            render status: 404
            return
        }
        [book: book]
    }

    def update(BookUpdateCommand cmd) {
        if ( cmd.hasErrors() ) {
            render status: 422
            return
        }
        Book book = updateReviewsService.updateReviews(cmd.name, cmd.reviews)

        if ( !book ) {
            render status: 404
            return
        }
        if ( book.hasErrors() ) {
            render status: 422
            return
        }

        render(model: [book: book], view: '/book/index')

    }
}