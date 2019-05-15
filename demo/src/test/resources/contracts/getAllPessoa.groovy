import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/pessoa'
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body([
                [
                        id: $(producer(anyUuid())),
                        name: $(producer(anyUuid())),
                        email: $(producer(anyEmail()))
                ],
                [
                        id: $(producer(anyUuid())),
                        name: $(producer(anyUuid())),
                        email: $(producer(anyEmail()))
                ],               [
                        id: $(producer(anyUuid())),
                        name: $(producer(anyUuid())),
                        email: $(producer(anyEmail()))
                ]
        ])
    }
}