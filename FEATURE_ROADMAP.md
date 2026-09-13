# Shrink High Feature Roadmap

This repository is an unofficial Telegram Android client based on the official Telegram Android source. It is organized as staged work so each change can be reviewed and built independently.

## Implemented in this sprint

| Area | Status | Notes |
| --- | --- | --- |
| Android CI | Implemented | GitHub Actions builds the standalone release APK and uploads it as an artifact. |
| Account capacity | Implemented | Account storage arrays and account iteration use a 100-account capacity. Server limits, device resources, and UX still need dedicated testing. |
| Download tuning | Implemented as a bounded configuration point | The downloader exposes an 8-worker tuning constant; it must be benchmarked on real devices before increasing further. |
| Local privacy utilities | Implemented as reusable utilities | EXIF stripping and app-private cache wiping are available for integration into user-triggered flows. |

## Planned modules

1. **Per-dialog wallpaper**: add a picker and app-private, dialog-scoped image preferences; no cloud upload is required.
2. **Decoy profile**: add an explicit opt-in secondary profile with clear user warnings and no claim that it can hide data from a compromised device or server.
3. **Offline outbox**: verify and test the existing SQLite queue, then add instrumentation tests for reconnect and retry behavior.
4. **Chat Drive**: index local metadata only, with duplicate detection, tags, folders, and bounded archive operations.
5. **Streaming and resumable transfers**: benchmark chunk scheduling, preserve cancellation, and add retry/backoff tests.
6. **Local AI**: integrate optional on-device models only after model licensing, APK size, memory use, and offline behavior are specified. No message content is sent to a cloud model by this plan.
7. **Productivity tools**: pinboard, read-later, keyword alerts, unified feeds, and scheduling should be separate UI modules with migration-safe local schemas.

## Deliberate exclusions

The client does not bypass Telegram server policy or other users' restrictions. In particular, local UI changes must not be represented as a way to defeat server-enforced protected-content, access-control, forwarding, or copyright restrictions. The implementation also does not promise forensic-proof deletion: cache wiping reduces local residue but cannot undo copies, backups, screenshots, or data already synchronized elsewhere.

## Build prerequisites

Use Android Studio 2025.1.4, Android SDK 36, Android NDK 27.2.12479018, and JDK 17. Before distributing an APK, replace the upstream placeholder signing and Firebase configuration with credentials owned by the project maintainer and use a distinct unofficial app name and icon.
