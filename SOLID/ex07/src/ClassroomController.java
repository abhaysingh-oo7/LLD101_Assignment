public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // SmartClassroomDevice pj = reg.getFirstOfType("Projector");
        // pj.powerOn();
        // pj.connectInput("HDMI-1");

        // SmartClassroomDevice lights = reg.getFirstOfType("LightsPanel");
        // lights.setBrightness(60);

        // SmartClassroomDevice ac = reg.getFirstOfType("AirConditioner");
        // ac.setTemperatureC(24);

        // SmartClassroomDevice scan = reg.getFirstOfType("AttendanceScanner");
        // System.out.println("Attendance scanned: present=" + scan.scanAttendance());

        // Projector
        PowerControllable projectorPower = reg.getPowerDevice();
        projectorPower.powerOn();

        InputConnectable projectorInput = reg.getInputDevice();
        projectorInput.connectInput("HDMI-1");

        // Lights
        BrightnessControllable lights = reg.getBrightnessDevice();
        lights.setBrightness(60);

        // AC
        TemperatureControllable ac = reg.getTemperatureDevice();
        ac.setTemperatureC(24);

        // Attendance
        AttendanceScannable scanner = reg.getScannerDevice();
        System.out.println("Attendance scanned: present=" + scanner.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        // reg.getFirstOfType("Projector").powerOff();
        // reg.getFirstOfType("LightsPanel").powerOff();
        // reg.getFirstOfType("AirConditioner").powerOff();
        for (PowerControllable device : reg.getAllPowerDevices()) {
            device.powerOff();
        }
    }
}
