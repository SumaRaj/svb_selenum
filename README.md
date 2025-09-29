# svb_selenum
# SVB QA Interview Prep – Selenium + API (Java, Maven, JUnit 5)

Zero-install automation project designed to run in **GitHub Codespaces**.  
Covers:
- **Selenium 4** (Firefox headless via Selenium Manager – no driver setup)
- **JUnit 5**
- **REST Assured** for API basics
- **(Day 3–4) Appium cloud run template** (no local emulators)
- **(Day 3–4) SQL drills with in-memory H2**

---

## Quickstart (Codespaces)

1. Click **Code → Codespaces → Create codespace on main**.
2. When the container builds, run tests:

```bash
mvn -q -Dtest=SimpleGoogleTest test
mvn -q -Dtest=ApiBasicsTest test
mvn -q -Dtest=WaitsAndDynamicContentTest,LoginFlowPomTest test
