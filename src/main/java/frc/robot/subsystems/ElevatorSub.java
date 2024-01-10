
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSub extends SubsystemBase {
  private final CANSparkMax elevatorNeo = new CANSparkMax(Constants.CanIDs.ElevatorNeo, MotorType.kBrushless);
  public  DigitalInput e_switch = new DigitalInput(Constants.torso.switch_elevator);
  public RelativeEncoder e_encoder;

  public ElevatorSub() {
    elevatorNeo.setInverted(false);
    e_encoder = elevatorNeo.getEncoder();
    e_encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putBoolean("eSwitch", e_switch.get());
    SmartDashboard.putNumber("Elevator Encoder", e_encoder.getPosition());
  }

  public void runElevator(double speed){
    elevatorNeo.set(speed);
  }

  public void stopElevator(){
    elevatorNeo.set(0);
  }
  
  public void stopElevatorUP(){
    elevatorNeo.set(-0.05);
  }

}
