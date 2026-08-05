# Implementation Plan - Reception Redesign

Redesign the Reception screen (`WaitingRoomScreen.kt`) to match the provided mockup. This includes updating the illustration, layout, typography, and specific UI components while preserving existing functionality.

## Proposed Changes

### [Theme & Colors]
#### [MODIFY] [Color.kt](file:///C:/Users/claud/AndroidStudioProjects/waiting-room/app/src/main/java/com/example/ui/theme/Color.kt)
- Add mockup-specific color constants:
    - `ReceptionGreen`: `#A8B69A`
    - `WarmSand`: `#EDE6D5`
    - `ReceptionBackground`: `#F7F3EA`
    - `MutedBeige`: `#D9D4C7`
    - `SoftBlue`: `#AEC6D8`
    - `SageText`: `#6B7E6E`

### [Reception UI]
#### [MODIFY] [WaitingRoomScreen.kt](file:///C:/Users/claud/AndroidStudioProjects/waiting-room/app/src/main/java/com/example/ui/screens/WaitingRoomScreen.kt)
- **`ReceptionRoomIllustration`**: Replace `WaitingRoomIllustration` with a new `Canvas`-based illustration that includes:
    - Hanging lamp.
    - Botanical print frame.
    - Console table with a plant.
    - Olive green door with a handle.
    - Floor baseline.
    - Another plant on the floor.
- **`WaitingRoomScreen`**:
    - Update `Scaffold` background to `ReceptionBackground`.
    - Centered "Waiting Room" title and "Le idee possono aspettare." subtitle.
    - **Idea Input**: Redesign as a card-like input with a microphone icon at the bottom-right.
    - **"Conserva" Button**: Update to use `ReceptionGreen` color and mockup styling.
    - **Bottom Navigation Card**: Add a lightbulb icon, label, dynamic idea counter, and a chevron icon.
    - Ensure balanced vertical spacing and remove oversized gaps.

## Verification Plan

### Automated Tests
- Run Gradle sync.
- Run `app:assembleDebug` to ensure compilation.

### Manual Verification
- Deploy to a device (Samsung SM-S948B equivalent if possible, or emulator).
- Verify visual fidelity against the mockup.
- Test "Conserva" button enable/disable state.
- Test saving an idea and observing the counter update.
- Test navigation to the Waiting Room list.
- Verify usability with keyboard open.
