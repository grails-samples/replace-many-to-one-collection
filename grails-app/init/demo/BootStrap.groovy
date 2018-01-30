package demo

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    BookDataService bookDataService
    ReviewDataService reviewDataService

    def init = { servletContext ->
        Book book = bookDataService.save('Daemon')
        reviewDataService.save('Daemon does for surfing the Web what Jaws did for swimming in the ocean.', 'Chicago Sun-Times',  book)
        reviewDataService.save('Daemon is wet-yourself scary, tech-savvy, mind-blowing!', 'Paste Magazine',  book)
    }

    def destroy = {
    }
}
