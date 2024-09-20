# Swim authentication and authorisation example

## Authentication

#### Generating keys
Typically, this will be handled using an OAuth provider. The following steps are for testing purposes only.
1) Generate a JWT using a website like https://www.scottbrady91.com/tools/jwt
2) Specify the key to be `RS256`.
3) Copy `n` (modulus) and `e` (public exponent).
4) Convert to decimal using https://github.com/nstreamio/swim-auth-example/blob/main/src/main/java/utils/PublicKeyConverter.java
5) Replace in https://github.com/nstreamio/swim-auth-example/blob/main/src/main/resources/server.recon
   Alternatively, a direct link to the public key can be provided, if your OAuth provider supports it.

#### Generating JWT
1) Add the appropriate claims to the JWT in https://www.scottbrady91.com/tools/jwt
2) Generate the JWT, which will be signed with the key generated in the previous step.

Example JWT:

```js
{
  "iss": "Nstream",
  "aud": "nstream-app",
  "sub": "5be86359073c434bad2da3932222dabe",
  "client_id": "my_client_app",
  "exp": 1727767652,
  "iat": 1726764052,
  "jti": "8f6ab3b04906321bd34b11be72433dda",
  "user_type": "admin"
}
```

In https://github.com/nstreamio/swim-auth-example/blob/main/src/main/resources/server.recon

`iss` corresponds to `@issuer(...)`

`aud` corresponds to `@audience(...)`

`user_type` is a custom claim which corresponds to `@claim(user_type: "admin")`

`exp` is the expiration of the JWT and is checked by the server. If the expiration of your token has a different key,
the Swim server can be configured to read it using `@expiration("custom_exp")`

#### Send the JWT using a Swim client.
1)  Create a Swim client.
2) Open a connection to the remote Swim server.
3) Send an `authenticate(...)` message with the previously generated JWT.

Example using the JS client: `swim.authenticate("warps://example.com", {"access_token": YOUR_JWT});`

Notice that the name `access_token` is used for the key, and it matches the definition `@token("access_token")` in https://github.com/nstreamio/swim-auth-example/blob/main/src/main/resources/server.recon

If the authentication has been successful, the swim client will receive an `authed(...)` message and if defined, the `didAuthenticate(...)` callback of the client will be invoked.
Once authenticated, the remote Swim server will automatically resolve the identity of the client for all future messages, until the client is deauthenticated.
Deauthentication can happen due to connection failure and will require re-authentication. In such cases, the corresponding callback `didDeauthenticate(...)` of the client will be invoked.

## Authorisation

Granular control over the access to different nodes and lanes on the server is controlled using an Authentication Policy.

1) Create an authentication policy such as: https://github.com/nstreamio/swim-auth-example/blob/main/src/main/java/swim_auth/AuthPolicy.java
2) Define your custom logic.

The identity of the user, derived from the JWT during the authentication step, contains all claims and information that has been provided to the JWT.
The envelope contains information about the current request, such as the node, lane and the type of the request.
