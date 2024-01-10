
package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorHalfUp extends CommandBase {
  private final ElevatorSub m_elevatorSub;
  private double m_speed;
  private double e_encoder;
  double error=-32;

  public ElevatorHalfUp(ElevatorSub _ElevatorSub) {
    this.m_elevatorSub = _ElevatorSub;
    this.e_encoder = _ElevatorSub.e_encoder.getPosition();
    addRequirements(m_elevatorSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    e_encoder = m_elevatorSub.e_encoder.getPosition();

    m_speed = (Constants.torso.m_distance_elevatorHalf+error -e_encoder)* Constants.torso.elevatorKP;
    m_elevatorSub.runElevator(m_speed);
    
   if(e_encoder <= Constants.torso.m_distance_elevatorHalf){
    m_elevatorSub.stopElevator();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_elevatorSub.stopElevatorUP();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
