package demo

class UrlMappings {

    static mappings = {
        '/book'(controller: 'book', action:"index", method: 'GET')
        '/book'(controller: 'book', action:"update", method: 'PUT')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
