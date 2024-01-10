
package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticSub extends SubsystemBase {
  PneumaticHub m_pH = new PneumaticHub(Constants.CanIDs.PH);

  private final DoubleSolenoid G1 = m_pH.makeDoubleSolenoid(Constants.Pneumatic.G1U,Constants.Pneumatic.G1A);
  private final DoubleSolenoid G2 = m_pH.makeDoubleSolenoid(Constants.Pneumatic.G2A,Constants.Pneumatic.G2U);
  private final DoubleSolenoid G3 = m_pH.makeDoubleSolenoid(Constants.Pneumatic.GM1,Constants.Pneumatic.GM2);
  
  public PneumaticSub() {}

  @Override
  public void periodic() {
    double minPressure =  SmartDashboard.getNumber("Minimum Pressure (PSI)", 90);
    double maxPressure = SmartDashboard.getNumber("Maximum Pressure (PSI)", 115);
    m_pH.enableCompressorAnalog(minPressure,maxPressure);

    SmartDashboard.putNumber("Pressure",m_pH.getPressure(0));
  }

  public void GripperOpen() {
   G1.set(Value.kForward);
   G2.set(Value.kForward);
  }

  public void GripperClose() {
    G1.set(Value.kReverse);
    G2.set(Value.kReverse);
  }
  
  public void stopCyclinders() {
    G1.set(Value.kOff);
    G2.set(Value.kOff);
  }

  public void MiniGripOpen(){
    G3.set(Value.kReverse);
  }
  
  public void MiniGripClose(){
    G3.set(Value.kForward);
  }

  public void openCompressor() {
    m_pH.enableCompressorAnalog(55, 60);
  }

  public void closeCompressor() {
    m_pH.disableCompressor();
  }
}
