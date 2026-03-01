// public class AirConditioner implements SmartClassroomDevice {
    
//     @Override public void setBrightness(int pct) { /* irrelevant */ }
//     @Override public int scanAttendance() { return 0; } // dummy
//     @Override public void connectInput(String port) { /* irrelevant */ }
// }
public class AirConditioner implements SmartClassroomDevice, PowerControllable, TemperatureControllable{
    @Override public void powerOn() { /* ok */ }
    @Override public void powerOff() { System.out.println("AC OFF"); }
    @Override public void setTemperatureC(int c) { System.out.println("AC set to " + c + "C"); }
        
}