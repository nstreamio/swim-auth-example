package swim_auth;

import swim.api.auth.Identity;
import swim.api.policy.AbstractPolicy;
import swim.api.policy.PolicyDirective;
import swim.warp.Envelope;

public class AuthPolicy extends AbstractPolicy {

    protected <T> PolicyDirective<T> authorize(Envelope envelope, Identity identity) {
        // Add your custom authorization logic here.
        if (identity.isAuthenticated()) {
            return allow();
        } else {
            return forbid();
        }
    }

}

