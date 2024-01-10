
package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RotationSub extends SubsystemBase {
  public final CANSparkMax rotationNeo = new CANSparkMax(Constants.CanIDs.RotationNeo, MotorType.kBrushless);
  public DigitalInput r_switch = new DigitalInput(Constants.torso.magswitch_gripper);
  public RelativeEncoder m_encoder;
  
  public RotationSub() {
    rotationNeo.setInverted(false);
    m_encoder = rotationNeo.getEncoder();
    m_encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putBoolean("rSwitch", !r_switch.get());
    SmartDashboard.putNumber("Rotation Encoder", m_encoder.getPosition());
  }

  public void runRotation(double speed){
    rotationNeo.set(speed);
  }

  public void stopRotation(){
    rotationNeo.set(0);
  }

}
