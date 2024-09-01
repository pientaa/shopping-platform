package com.pientaa.shoppingplatform.pricing.external.product

import com.pientaa.shoppingplatform.pricing.domain.model.Money
import com.pientaa.shoppingplatform.pricing.domain.model.Product
import com.pientaa.shoppingplatform.pricing.external.product.dto.ProductExternalDTO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Currency
import java.util.UUID

/**
 * Implementation of the [ProductClient] interface using Feign for communication with the Product Service.
 *
 * This class encapsulates the logic to retrieve a product and map it to the internal [Product] model.
 *
 * ### Future Considerations:
 *
 * 1. **Retryable Mechanisms:**
 *    - The current implementation does not include retry logic. If the product service is temporarily unavailable,
 *      the request will fail immediately. To improve resilience, consider adding retry logic either in the Feign client
 *      configuration or within this class using Spring Retry or Resilience4j.
 *
 * 2. **Error Handling:**
 *    - Currently, the implementation throws a `NoSuchElementException` if the product is not found or if the response
 *      status is not `HttpStatus.OK`. Future enhancements could involve more granular error handling, such as custom exceptions
 *      that provide more context about the failure, or integration with a centralized error management system.
 *    - Consider logging or notifying about the error for better traceability and debugging.
 *
 * 3. **Timeout Configuration:**
 *    - Ensure that appropriate timeouts are configured at the Feign client level to avoid excessive delays in case
 *      the Product Service is slow to respond.
 *    - If necessary, implement a circuit breaker pattern to prevent cascading failures in case the Product Service becomes
 *      unresponsive.
 *
 * 4. **Fallback Mechanism:**
 *    - Currently, there is no fallback mechanism. In case of a failure, the client will throw an exception.
 *      Consider adding a fallback mechanism that either returns a default product, cached data, or provides an alternative
 *      response to maintain service availability.
 *
 * 5. **Security Considerations:**
 *    - Ensure that the Feign client is configured with appropriate security credentials, such as OAuth2 tokens,
 *      to securely communicate with the Product Service.
 *
 * @param productFeignClient The Feign client used to communicate with the Product Service.
 */
@Service
class ProductClientImpl(
    private val productFeignClient: ProductFeignClient,
) : ProductClient {
    override fun getProduct(id: UUID): Product =
        productFeignClient.getProduct(id)
            .takeIf { it.statusCode == HttpStatus.OK }
            ?.body
            ?.toModel() ?: throw NoSuchElementException("Product not found")
}

private fun ProductExternalDTO.toModel(): Product = Product(id, Money.of(amount, Currency.getInstance(currency)))
