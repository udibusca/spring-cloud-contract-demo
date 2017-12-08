import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/person'
        headers {
            contentType(applicationJson())
        }
        body([
                name: $(consumer(anyNonBlankString())),
                email: $(consumer(optional(regex(email()))), producer('abc@abc.com'))
        ])
    }
    response {
        status 201
        headers {
            contentType(applicationJson())
        }
        body([
                id: $(producer(anyUuid())),
                name: fromRequest().body('$.name'),
                email: fromRequest().body('$.email')
        ])
    }
}