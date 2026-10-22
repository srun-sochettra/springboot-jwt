# Contributing

Keep changes focused on the existing authentication exercise. Use synthetic accounts and an isolated database.

Run wrapper checks and report actual results. The context smoke test does not verify registration, SQL mapping, token parsing or role enforcement. Prioritise schema correctness, private signing configuration and multi-role mapping before advertising a working authentication service.

Useful regression scenarios include duplicate/concurrent registration, missing default role, failed role insertion, invalid request fields, wrong passwords, missing users and expired/malformed tokens. Test actual HTTP filter behaviour separately from JwtService unit tests. Do not infer role-based restrictions from authority loading alone.

Describe the problem, focused change, API/schema impact, checks and recovery in pull requests. Keep credentials, tokens and private account records out of commits. Follow [SECURITY.md](SECURITY.md) for sensitive reports.
