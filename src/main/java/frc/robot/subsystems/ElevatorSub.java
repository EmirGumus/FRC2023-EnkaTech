
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
  public RelativeEncoder e_encoder;
  public  DigitalInput e_switch = new DigitalInput(Constants.torso.switch_elevator);
  public ElevatorSub() {
    elevatorNeo.setInverted(false);
    e_encoder = elevatorNeo.getEncoder();
    e_encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Encoder", e_encoder.getPosition());
    SmartDashboard.putNumber("Elevator Hız", e_encoder.getVelocity());
  }

  public void runElevator(double speed){
    elevatorNeo.set(speed);
  }

  public void stopElevator(){
    elevatorNeo.set(0);
  }

}
