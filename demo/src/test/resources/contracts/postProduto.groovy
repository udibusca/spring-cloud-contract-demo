import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/produto'
        headers {
            contentType(applicationJson())
        }
        body([
                name: $(consumer(anyNonBlankString()))
//                name: $(consumer(regex('[0-9]{5}')))
//                name: producer(regex(nonBlank()))
        ])
    }
    response {
        status 201
        headers {
            contentType(applicationJson())
        }
//        body (consumer(optional(regex(nonBlank()))))
        body([
                id: $(producer(anyUuid())),
                name: fromRequest().body('$.name')
//                name: $(producer(anyNonBlankString()))
//                name: producer(regex(nonBlank()))
        ])
    }
}