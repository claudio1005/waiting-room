# Prompt Agente Android Studio — Reception, ricostruzione visiva

Allega all'Agente:

- il mockup completo;
- `reception_mock_reference.png`;
- `reception_room_reference.png` oppure l'asset definitivo `reception_room.webp`.

Incolla il prompt seguente senza accodare altri task.

```text
CONTEXT

You are working on the personal Android app “Waiting Room”, built with Kotlin, Jetpack Compose, Material 3 and Room.

Read these files before editing:
- PROJECT_BIBLE.md
- docs/RECEPTION_VISUAL_SPEC.md

The attached Reception mockup is the visual source of truth.
The current redesigned Reception is NOT accepted: it looks flat, generic and dated because the room was recreated with primitive Canvas shapes and the native components are oversized.

TASK

Rebuild ONLY the visual presentation of the Reception screen so that it follows docs/RECEPTION_VISUAL_SPEC.md and closely matches the attached mockup.

This task is VISUAL ONLY. Do not implement speech recognition yet.

ALLOWED FILES

- app/src/main/java/com/example/ui/screens/WaitingRoomScreen.kt
- app/src/main/java/com/example/ui/theme/Color.kt, only if a missing color token is genuinely required
- app/src/main/res/drawable-nodpi/reception_room.webp or the supplied equivalent image asset
- app/src/main/res/values/strings.xml, only for Reception strings

DO NOT MODIFY

- Room entities or database
- DAO files
- repository
- ViewModel logic
- MainActivity navigation
- Gradle files
- signing configuration
- Waiting Room / Studio screen
- idea detail bottom sheet
- Archive screen
- unrelated resources
- Task List, Walkthrough or generated planning artifacts unless required by the IDE itself

PRESERVE EXACTLY

- the existing idea text state;
- enabled/disabled behavior of “Conserva”;
- creation and Room persistence of an idea;
- clearing behavior after a successful save;
- dynamic waiting-idea count;
- navigation to the Waiting Room;
- keyboard usability.

ILLUSTRATION STRATEGY

- Remove the current primitive Canvas room illustration from the active layout.
- Use the supplied high-quality room image as a normal Android image resource.
- Do not redraw the room with Canvas rectangles, circles or simplified furniture.
- Render it full width near the top with the correct aspect ratio.
- Do not embed title, input, buttons, status-bar time or system icons in the image.
- Do not crop out the lamp, door, table or right-side plant.

LAYOUT TARGET

Use docs/RECEPTION_VISUAL_SPEC.md as the dimensional source.
For a roughly 360 dp wide portrait viewport:

- shared horizontal margin: 24–26 dp;
- room illustration: full width, approximately 290–305 dp high;
- title: 32–34 sp, warm near-black, Medium/Semibold, not heavy Bold;
- subtitle: 15–16 sp;
- idea field: 108–112 dp high;
- Conserva button: 52–54 dp high;
- Waiting Room card: 76–80 dp high;
- compact gaps matching the attached mockup;
- no oversized blank areas;
- no component should look like an untouched Material default.

INPUT FIELD

- warm cream surface, not pure white;
- 1 dp subtle border;
- 16–18 dp corner radius;
- very light shadow only;
- placeholder aligned top-left;
- microphone icon aligned bottom-right;
- keep the microphone as a clickable control hook, but do not implement speech recognition in this task;
- minimum 48 dp touch target;
- do not use a large default OutlinedTextField appearance.

CONSERVA BUTTON

- flat olive fill #A8B69A when enabled;
- restrained disabled state;
- no visible gradient;
- no heavy elevation;
- 12–14 dp corner radius.

WAITING ROOM CARD

- compact warm surface;
- subtle border and shadow;
- outline lightbulb on the left;
- title and dynamic count stacked tightly;
- chevron on the right;
- whole card clickable;
- avoid the current oversized card appearance.

RESPONSIVE BEHAVIOR

- Match the mockup proportions on Samsung SM-S948B portrait first.
- Use constraints rather than scaling the entire UI as a bitmap.
- Keep normal layout usable without scrolling when possible.
- With the IME open, allow controlled scrolling so the field and Conserva remain reachable.
- Do not distort or vertically squash the room image.

QUALITY BAR

The result must look like an illustrated, premium calm app—not a generic Material sample and not a 2014-style Android form.
The illustration must carry depth, light and texture; native controls must visually recede into the design.

VALIDATION

1. Build the app.
2. Fix only errors introduced by this task.
3. Confirm that Room persistence and navigation code were not changed.
4. List every modified file.
5. State whether the current Canvas illustration was removed from the active layout.
6. Stop after this task. Do not implement the microphone or redesign another screen.
```
