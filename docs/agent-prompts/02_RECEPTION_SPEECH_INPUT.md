# Prompt Agente Android Studio — Microfono funzionante

Usare solo dopo l'approvazione visiva della Reception.

```text
CONTEXT

You are working on the personal Android app “Waiting Room”, built with Kotlin, Jetpack Compose, Material 3 and Room.

Read:
- PROJECT_BIBLE.md
- docs/RECEPTION_VISUAL_SPEC.md

The Reception microphone is currently decorative. It must become a real speech-to-text control without changing the approved visual layout.

TASK

Implement Italian speech-to-text for ONLY the microphone button inside the Reception idea field.

ALLOWED FILES

- app/src/main/AndroidManifest.xml
- app/src/main/java/com/example/ui/screens/WaitingRoomScreen.kt
- app/src/main/java/com/example/ui/components/SpeechInputController.kt (preferred new helper if it keeps the screen simpler)
- app/src/main/res/values/strings.xml

DO NOT MODIFY

- database entities
- DAOs
- repository
- ViewModel behavior
- Gradle files
- signing
- visual dimensions of the approved Reception
- Waiting Room / Studio
- bottom sheet
- Archive

REQUIRED PLATFORM IMPLEMENTATION

- Use Android SpeechRecognizer; do not add a third-party speech library.
- Request android.permission.RECORD_AUDIO at runtime.
- Add the required manifest permission.
- Add the speech RecognitionService query required for modern Android package visibility.
- Prefer on-device recognition when the platform reports it available; otherwise use the default system recognizer.
- Configure recognition language as Italian (it-IT).
- Do not implement continuous background listening.
- Create and call the SpeechRecognizer only from the main thread.
- Always destroy the recognizer when it is no longer needed.

USER FLOW

1. User taps the microphone.
2. If permission is missing, request it.
3. After permission is granted, start listening.
4. Show a restrained listening state by tinting or subtly animating only the microphone icon.
5. Put the final recognized text into the existing idea text field.
6. If the field already contains text, append one space and then the new transcription.
7. A second tap while listening stops listening.
8. Stop after a final result or error.
9. Show short localized feedback for:
   - permission denied;
   - recognizer unavailable;
   - no speech detected;
   - generic recognition error.
10. Never save the idea automatically; the user must still press Conserva.

ACCESSIBILITY

- The microphone must be a real clickable semantic button.
- Use localized content descriptions:
  - “Detta un’idea” when idle;
  - “Interrompi dettatura” when listening.
- Keep a minimum 48 × 48 dp touch target.

STATE AND LIFECYCLE

- Avoid leaking Activity or Context references.
- The recognizer must survive ordinary recompositions without being recreated repeatedly.
- Clean it up with DisposableEffect or an equivalent lifecycle-safe mechanism.
- Do not let a result overwrite newer text typed by the user after recognition started; append the final result to the current field value.
- Prevent simultaneous recognition sessions.

VALIDATION

1. Build the app.
2. Test on Samsung SM-S948B.
3. Test first permission request.
4. Test permission denial.
5. Test Italian transcription into an empty field.
6. Test appending transcription to existing text.
7. Test stopping with a second tap.
8. Test that Conserva still saves exactly once through the existing logic.
9. List modified files and summarize lifecycle cleanup.
10. Stop after this task.
```
