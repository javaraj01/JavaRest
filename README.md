JAVA Coding Challenge

Your task is to create a Spring Boot application with APIs and tables for the products screen in a retail
application. You need to implement the business logic as described in the challenge. You are free to
define the necessary tables and properties for the Product entity. Use java version 8 or above. Below are
the API requirements:
1. API to List Active Products:
• Endpoint: GET /api/products
• Description: Get the list of active products sorted by the latest first.
2. API to Search Products:
• Endpoint: GET /api/products/search
• Parameters: productName (optional), minPrice (optional), maxPrice (optional),
minPostedDate (optional), maxPostedDate (optional)
• Description: Search for products based on the given criteria (product name, price range,
and posted date range).
3. API to Create a Product:
• Endpoint: POST /api/products
• Request Body: Product object (name, price, status)
• Description: Create a new product, but the price must not exceed $10,000. If the price is
more than $5,000, the product should be pushed to the approval queue.

4. API to Update a Product:
• Endpoint: PUT /api/products/{productId}
• Request Body: Product object (name, price, status)
• Description: Update an existing product, but if the price is more than 50% of its previous
price, the product should be pushed to the approval queue.

5. API to Delete a Product:
• Endpoint: DELETE /api/products/{productId}
• Description: Delete a product, and it should be pushed to the approval queue.
6. API to Get Products in Approval Queue:
• Endpoint: GET /api/products/approval-queue
• Description: Get all the products in the approval queue, sorted by request date (earliest
first).

7. API to Approve a Product:
• Endpoint: PUT /api/products/approval-queue/{approvalId}/approve

• Description: Approve a product from the approval queue. The product state should be
updated, and it should no longer appear in the approval queue.

8. API to Reject a Product:
• Endpoint: PUT /api/products/approval-queue/{approvalId}/reject
• Description: Reject a product from the approval queue. The product state should remain
the same, and it should no longer appear in the approval queue.

Note: You need to design the database tables for the Product entity and the Approval Queue entity to
support the above functionalities. You can use your preferred database (e.g., MySQL, PostgreSQL) and
define appropriate data types and lengths for the properties.

Please implement the Spring Boot application with the above APIs and database tables according to the
provided requirements. Remember to handle appropriate error responses and validations.
