import java.util.*;

// public class DeviceRegistry {
//     private final java.util.List<SmartClassroomDevice> devices = new ArrayList<>();

//     public void add(SmartClassroomDevice d) { devices.add(d); }

//     public SmartClassroomDevice getFirstOfType(String simpleName) {
//         for (SmartClassroomDevice d : devices) {
//             if (d.getClass().getSimpleName().equals(simpleName)) return d;
//         }
//         throw new IllegalStateException("Missing: " + simpleName);
//     }
// }
public class DeviceRegistry {

    private final List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) {
        devices.add(d);
    }

    public PowerControllable getPowerDevice() {
        for (SmartClassroomDevice d : devices) {
            if (d instanceof PowerControllable) {
                return (PowerControllable) d;
            }
        }
        throw new IllegalStateException("No Power device found");
    }

    public TemperatureControllable getTemperatureDevice() {
        for (SmartClassroomDevice d : devices) {
            if (d instanceof TemperatureControllable) {
                return (TemperatureControllable) d;
            }
        }
        throw new IllegalStateException("No Temperature device found");
    }

    public BrightnessControllable getBrightnessDevice() {
        for (SmartClassroomDevice d : devices) {
            if (d instanceof BrightnessControllable) {
                return (BrightnessControllable) d;
            }
        }
        throw new IllegalStateException("No Brightness device found");
    }

    public AttendanceScannable getScannerDevice() {
        for (SmartClassroomDevice d : devices) {
            if (d instanceof AttendanceScannable) {
                return (AttendanceScannable) d;
            }
        }
        throw new IllegalStateException("No Scanner device found");
    }

    public InputConnectable getInputDevice() {
        for (SmartClassroomDevice d : devices) {
            if (d instanceof InputConnectable) {
                return (InputConnectable) d;
            }
        }
        throw new IllegalStateException("No Input device found");
    }

    public List<PowerControllable> getAllPowerDevices() {
        List<PowerControllable> result = new ArrayList<>();

        for (SmartClassroomDevice d : devices) {
            if (d instanceof PowerControllable) {
                result.add((PowerControllable) d);
            }
        }

        return result;
    }
}