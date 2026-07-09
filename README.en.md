# nexo-wallet-mobile

Fictitious **Nexo Finanzas** Android wallet and its **Appium automation**: a Java mobile framework
with Cucumber (BDD) and the Page Object Model, designed to run in **two modes** — simulated (no
emulator, CI-friendly) or real (against the app on an emulator/device).

![CI](https://github.com/fercarballo/nexo-wallet-mobile/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-21-orange)
![Appium](https://img.shields.io/badge/Appium-9-662D91?logo=appium&logoColor=white)

> ⚠️ **Fictitious data.** Users, accounts and balances are for demonstration only.

## Verification status (read first)

| Component | Status |
|---|---|
| BDD scenarios against the **simulated** wallet | ✅ **Executed and green** (6 scenarios) |
| **Appium capabilities** built from configuration | ✅ **Verified** (2 tests) |
| Execution against a **real Android emulator/device** | 🔴 **Not executed here** — requires the Android SDK and an emulator. The code is real and compiles; steps to run it are in `docs/runbooks/local-setup.md`. |

No test run is claimed that did not happen. See [`evidence/`](evidence/).

## In 5 minutes

**Prerequisites.** JDK 21 and Maven. (Real mode also needs Appium Server, Android SDK, an emulator
and the APK.)

```bash
mvn test                        # simulated mode (default) — 8 tests green
mvn test -DwalletMode=appium    # real mode — needs Appium + emulator + APK
```

## The core idea: one abstraction, two modes

Scenarios talk to a `WalletActions` abstraction and **never know about Appium**. A single switch
(`-DwalletMode=appium`) swaps the target — `SimulatedWallet` (in-memory, imitates the API contract)
or `AppiumWallet` (drives the real Android app). This is the same mock-vs-real-provider pattern used
for any dependency gated by a device, licence or credentials.

## Principles

- **Accessibility-id selectors** (`content-desc`) — stable, decoupled from text and layout.
- **Explicit waits** in `BaseScreen`; never `sleep`.
- **Isolation** — a fresh wallet with re-seeded state per scenario.

The full documentation set is in Spanish under [`docs/`](docs/).

## Ecosystem

Repository **3 of 7**. Automates the mobile channel, imitating the contract of the core
[`nexo-transfer-api`](https://github.com/fercarballo/nexo-transfer-api).

## License

MIT — see [`LICENSE`](LICENSE).
