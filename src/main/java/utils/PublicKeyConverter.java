package utils;

import java.math.BigInteger;
import java.util.Base64;

public class PublicKeyConverter {

    public static void main(String[] args) {
        String n = "qsY3Ky__MCM5c3p4ZRdZPn-QnvI84-P1GuY5q9zWbEGKXY2I4bJ_ZvAG092RHxUeAKE7SyfP6TPiOmpc5KVwd3WPLHcGXeNvCunxDsscVdBFQhq3_NUqgJDijOVK1ze5eKQiSbE2VyQ-bRoXN3j7rRPHGKf-OdBvCmeYU6zk6td8wgzNKxtBSnsuZBqeO5JFMxDGtDZc3hooH4CIWPqPj_h9lnikLXCtp2JwXkQW0jRKvaNpv_tPDN6O6Cs9XCm4n5nxjj8xM3W-x8USuPc3sCx3s3BzY8xLFi2xvX_GrGdiAU63xGEV8s03AN_f6xVCX0TAIh1ek1ppVBDvblK9y9eMASi5AMR62gJ5lOq3x_G93oWop-6zeupmOXYxPqtCGvqY4BN2AZUg8q8UmFSGrWImrTjbpocC4LoYB5NT-9f5jKxa6C-7LQp-6gF1KYAKtGi4SMPN5lJNRxXNpSz4Uqq8xcKjNSXeuy9tdh-94ktdikzWILJKkBjIIdDGYqQ5";
        String e = "AQAB";

        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
        BigInteger publicExponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));
        System.out.println("modulus: " + modulus);
        System.out.println("publicExponent: " + publicExponent);
    }
}
