import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/demo'
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body("""{"valid": true}""")
    }
}