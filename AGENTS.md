# AGENTS.md

Instructions for AI coding agents working on **Waiting Room**.

## Read first

Before changing code, read `PROJECT_BIBLE.md` completely.

Waiting Room is a **personal Android app**, not a commercial, enterprise, or Play Store project. Prefer the simplest reliable solution that improves Claudio's actual experience.

## Product intent

Waiting Room is not a task manager. It reduces mental load by letting ideas wait safely until the right moment.

The app must feel calm, warm, domestic, spacious, and reassuring.

The approved Figma file is the visual reference:

- File: `Waiting Room UI`
- File key: `CdIi1zJuRoFYl20vyg9p6K`
- Page: `0:1`
- Reception: `1:3`
- Studio: `1:5`
- Bottom Sheet: `1:7`
- Archivio: `1:2`

These nodes are cropped reference images, not native structured Figma components. Treat them as visual sources of truth, but do not invent exact design tokens that are not actually available.

## Technical stack

- Kotlin
- Jetpack Compose
- Material 3
- Room
- ViewModel and repository pattern
- Flow / StateFlow where already used

## Default restrictions

Unless a task explicitly requires it, do not modify:

- Room entities
- DAOs
- repositories
- ViewModel business logic
- database schema
- navigation
- Gradle configuration
- signing configuration
- manifest
- unrelated screens

Never:

- delete or reset user data;
- use destructive migrations;
- expose secrets, keystores, tokens, or passwords;
- add dependencies without a clear need;
- perform broad refactors unrelated to the requested result;
- let multiple agents edit the same files concurrently.

## Working method

For each task:

1. Run `git status` and inspect the current branch.
2. Read the relevant existing implementation before editing.
3. Inspect the relevant Figma node when visual work is requested.
4. State the plan and intended files before a large or risky change.
5. Modify the smallest necessary set of files.
6. Preserve existing behaviour unless explicitly instructed otherwise.
7. Build with:

   ```powershell
   .\gradlew.bat :app:assembleDebug
   ```

8. Fix only errors introduced by the current task.
9. When an authorised Android device is available through ADB, install, launch, capture a screenshot, and compare it with the relevant Figma reference.
10. Perform at most three focused visual refinement passes before reporting back.
11. Report:
    - modified files;
    - build result;
    - preserved behaviour;
    - remaining visual differences;
    - possible regressions.
12. Stop and wait for Claudio's confirmation before commit, push, database changes, or starting another task.

## UI rules

- The Figma reference has priority for appearance.
- Preserve the app's calm aesthetic.
- Prefer reusable but simple composables.
- Avoid generic dashboard or task-manager styling.
- Avoid emojis as final UI icons.
- Keep animations short, subtle, and functional.
- Maintain usability with the keyboard open and on the Samsung SM-S948B in portrait orientation.

## Git safety

Before a meaningful implementation change, create a checkpoint when the working tree is clean.

Use small, coherent commits. Do not mix unrelated UI, database, and build-configuration changes.

Do not commit or push until Claudio explicitly confirms the result.

## Final principle

Waiting Room must feel like a small, quiet house where ideas can wait safely—not like a productivity system demanding action.
