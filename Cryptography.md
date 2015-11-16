

# Introduction #

Here we'll discuss two different areas of cryptography, data confidentiality (both symmetric and asymmetric key encryption) and data integrity & authentication (secure hash functions, message authentication code ad digital signatures). _The_ most important thing to remember when it comes to cryptography is not to "roll your own" cryptographic algorithms!

## Data Confidentiality ##
There are two basic flavours of encryption; symmetric & asymmetric.

### Symmetric Key Encryption ###
  * No separation of read/write privileges
  * Good performance (fast for both encryption & decryption)
  * Small key sizes
  * Example: Media streaming application, high speed, high throughput message buses

#### Example ####
  * Bob wants to send a message to Alice, `m`
  * Bob and Alice share a secret key, `k`
  * Function `F` - encrypts `m` using `k` producing `c`: `c = F(m, k)`
  * Function `F-1` decrypts `c` using `k`: `m = F-1(F(m, k), k)`

#### Things to Remember ####
  * Symmetric key encryption is almost always acceptable when encryption and decryption needs to be performed by the same entity
  * Do not share key among more than two nodes (one at each end of communication channel)

### Asymmetric Key Encryption ###
  * Separation of read/write privileges (more below)
  * Much slower than symmetric encryption
  * Larger key sizes

#### Example ####
  * Alice has two keys, one secret `ks` and one public `kp`
  * Function `F` - encrypts `m` using `kp` producing `c`: `c = F(m, kp)`
  * Function F-1 decrypts c using `ks`: `m = F-1(F(m, kp), ks)`
  * Alice keeps the private key, and distributes the public keys to Bob, Chris & Dave
  * Bob, Chris and Dave encrypt messages with `kp` but because they don't have Alice's `ks` they are unable to send messages to each other

#### Things to Remember ####
  * Performance is much worse than symmetric encryption but we do retain (separate) control over who can read and who can write.

## Data Integrity & Authentication ##

### Secure Hash Functions ###

A secure hash function is an algorithm which essentially generates a finger print for data of an arbitrary size, they form mappings and have the following properties:
  * Efficient
    * Given a message `m` the function `h(m)` can be easily computed
  * Pre-image resistant
    * Given a hash of a message `h(m)` it should be very difficult to compute `m`, that is, you cant go backwards
  * Collision resistant
    * Infeasible to find different `m1` ad `m2` such that `h(m1) = h(m2)` - generally, easiest for a hash function to violate this property (both MD5 & SHA-1 do)

### MACs & Digital Signatures ###
There are two main types of primitives that provide data integrity and data origin authentication:
  * Message Authentication Codes - symmetric key
    * No separation of sign/verify privileges
    * Good performance
    * Small key sizes
  * Digital Signatures - asymmetric key
    * Separation of sign/verify privileges
    * Much slower than symmetric encryption
    * Larger key sizes

### Core Security Controls ###
Security is a cross cutting concern, OAP is an interesting method of addressing it:
  * Authentication
    * Application-managed
    * Infrastructure-managed
  * Authorisation
    * Coarse-grained
    * Fine-grained
  * Forensic information
    * Auditing
    * Logging

**It's all about the endpoints!**
Endpoints are your first and last chance to enforce security around your trust zones, Entry & Exit points are the two obvious endpoints but we also need to remember our friend the Error endpoint:
  * On Entry
    * Authorize access
      * Authorization based on identity
      * Authorization based on internal state
    * Validate parameters & input (see below)
  * On Exit
    * Ensure proper encoding of results
  * On Error
    * Ensure that internal structure isn't revealed

To avoid malicious input exposing a bug in your system you need to validate input parameters both lexically and grammatically, you can do this by means of a black and a white list and by logging all input validation failures so as to observe the evolving attempts of attackers on your system. Validating at different touch points promotes a defence-in-depth approach.

Similarly on the way out of the system we should take care that output in encoded as a language data element before use with dynamic languages:
  * HTML/JavaScript in dynamic pages
  * XML/JSON for Rich Internet Applications
  * Parameter markets for SQL

#### Application Session State Controls ####
Distributed applications must maintain their sate across stateless protocols, if you attempt to store all session state on the server things quickly become complicated when you need to scale horizontally to handle load, how do you replicate session state between server nodes in a timely fashion to allow clients to communicate with any load-balanced host? Similarly if you push the management on to the client it becomes a burden headache there too, requiring additional security controls and objects which have to be passed both ways.

Identifiers must be:
  * Unique (you need a good/safe random number generator)
  * Non-guessable
  * Non-forgeable

You might find, depending on your usage, that an identifier alone is insufficient to fully maintain application's state between requests so additional identifiers might be required to convey the origin of the code making the request and/or the step in a multi-request transaction for example. Again don't try to be clever and invent your own protocol.

#### Periodic Credential Change ####
  * Credentials must be changed periodically
    * Digital certificates generally every 1-2 years
    * Passwords to domains
    * Database access
  * How often depends on a number of factors
    * What are the credentials protecting
    * What systems can be accessed
    * Have the credentials been compromised?

#### Preventing JavaScript Hijacking Control ####
There are three main techniques:
  1. Implement CSRF control for the endpoint
  1. Serve JSON with mime-type application/json
  1. Return a value that isn't executable