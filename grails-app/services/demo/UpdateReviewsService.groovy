package demo

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class UpdateReviewsService {

    BookDataService bookDataService
    ReviewDataService reviewDataService

    @Transactional
    Book updateReviews(String name, List<BookReview> reviews ) {
        Book book = bookDataService.findJoinReviews(name)
        if (!book) {
            return null
        }

        clearReviews(book)
        addReviews(book, reviews)

        if ( !book.save() ) {
            log.error 'could not save book'
        }
        book
    }

    void clearReviews(Book book) {
        List<Serializable> ids = []
        book.reviews.collect().each { Review review ->
            book.removeFromReviews(review)
            ids << review.id
        }
        reviewDataService.delete(ids)
    }

    void addReviews(Book book, List<BookReview> reviews) {
        reviews?.each { BookReview bookReview ->
            Review review = new Review(quote: bookReview.quote, author: bookReview.author)
            book.addToReviews(review)
        }
    }
}