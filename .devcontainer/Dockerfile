FROM mcr.microsoft.com/devcontainers/java:17

# Install Firefox for headless Selenium
RUN apt-get update && apt-get install -y firefox-esr && rm -rf /var/lib/apt/lists/*

ENV MOZ_HEADLESS=1
