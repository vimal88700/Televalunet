# Televalunet Android

Unofficial Android messaging client based on the official Telegram Android source.

## Build requirements

- Android Studio 2025.1.4
- JDK 17
- Android SDK 36
- Android NDK 27.2.12479018

The GitHub Actions workflow builds the standalone release variant and uploads the APK as a workflow artifact. Before distributing builds, replace upstream placeholder signing, Firebase, API, package, and branding values with values owned by the project maintainer. Never commit API hashes, signing passwords, or other secrets.

See [FEATURE_ROADMAP.md](FEATURE_ROADMAP.md) for implemented features, planned modules, limitations, and privacy notes.
