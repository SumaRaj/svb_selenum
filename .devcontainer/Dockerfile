# Java 17 + Maven base
FROM mcr.microsoft.com/devcontainers/java:17

# Install Firefox for Selenium headless runs
USER root
RUN apt-get update && \
    apt-get install -y --no-install-recommends firefox-esr && \
    rm -rf /var/lib/apt/lists/*

# Set display so headless runs don't try to open a GUI
ENV MOZ_HEADLESS=1
