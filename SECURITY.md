# Security and demo-use guidance

This is coursework, not an audited identity service. No supported-release programme or reporting deadline is established here.

## Review priorities

- JwtService contains a committed signing key and properties contain database credentials. Rotate any live values and review historical copies. The overlay does not remove secrets or alter history.
- Request fields lack supplied validation. No rate limiter, password policy, email verification, lockout or token revocation is implemented.
- Roles are loaded but no role-specific route restrictions are supplied. Account-status methods always return true.
- Registration's email check lacks a schema uniqueness constraint, and user/role inserts lack a supplied transaction boundary.
- Token parsing and user lookup can throw outside controller error handling. Verify safe filter responses rather than assuming every invalid token becomes 401.
- Registration failures return exception messages. Review error redaction before publication.
- The seed uses a plaintext password and personal account details. Do not treat it as a safe or working demo account.

The ignore rules do not explicitly exclude a plain .env file. Review staged configuration before committing. Use synthetic data and do not expose this configuration publicly.

Use a verified private maintainer channel for sensitive reports if available, or request one without posting details. No dedicated reporting address or enabled private-reporting feature was confirmed. Redact passwords, tokens and account details. These are static observations, not executed exploits or a complete audit.
