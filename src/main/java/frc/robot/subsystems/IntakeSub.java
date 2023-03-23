
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSub extends SubsystemBase {
  private final CANSparkMax intakeRNeo = new CANSparkMax(Constants.CanIDs.IntakeRNeo, MotorType.kBrushless);
  private final CANSparkMax intakeLNeo = new CANSparkMax(Constants.CanIDs.IntakeLNeo, MotorType.kBrushless);

  public IntakeSub() {
    intakeRNeo.setInverted(false);
    intakeLNeo.setInverted(true);
  }

  @Override
  public void periodic() {}

  public void runIntake(double speed){
    intakeRNeo.set(speed);
    intakeLNeo.set(speed);
  }

  public void stopIntake(){
    intakeRNeo.set(0);
    intakeLNeo.set(0);
  }
  public void breakeintake(){
    intakeRNeo.set(-0.1);
    intakeLNeo.set(-0.1);
  }
}
