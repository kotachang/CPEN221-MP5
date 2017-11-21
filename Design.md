Design Choices for Database
===

We will start by exploring a generic dataset for a business. We will declare 3 main data types:
+ Business
+ Review
+ User

For each we will have different associated properties.

### Business
Type of product
+ This is the product that the business provides.
+ This could be food, an object (printers), or a service (massage).

Name
+ This is simply the business's title.

Location
+ This could be represented as a street address or a coordinate pair.

Review
+ Reviews associated with this business (see Review type below).

### Review
Price
+ This is a rating of the price of the service provided by this business.
+ For example, how expensive the food was, how expensive the printers were compared to other stores, how expensive the massage was compared to other masseuse.
+ Could be a rating from 1 to 5, 5 being more expensive.

Quality of Product
+ Simply the quality of the product served.
+ For example, how good the food was, how well the printer performed, how good the massage was.
+ Could be a rating from 1 to 5, 5 being the better quality product.

Description
+ User's description of the service.
+ Represented by words, rather than a rating out of 5.

Quality of Service
+ Rating of the customer service given by business.
+ For example, the waitress's service, the printer salesperson's service, the masseuse's service. 
+ Could be a rating from 1 to 5, 5 being the better quality service.

Overall Experience
+ Represents the overall experience provided by the business
+ Could be an average of the ratings from Price, Quality of Product, and Quality of Service.
+ Could be a rating from 1 to 5, 5 being the better quality service.

User
+ Represent the user who submitted the review. (see User below)
+ Could be anonymous (to the user / business owner).

### User
Name
+ username of the user.
+ does not necessarily need to be their real name.

ID
+ Unique identifier for a user.
+ Could be represented by a string of numbers and letters (hashCode).

Review
+ Reviews given by the user for a business (see Review above)
