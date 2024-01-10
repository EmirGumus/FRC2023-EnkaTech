
package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorUpDeneme extends CommandBase {
  private final ElevatorSub m_elevatorSub;
  private double m_speed;

  public ElevatorUpDeneme(double speed ,ElevatorSub _ElevatorSub) {
    this.m_elevatorSub = _ElevatorSub;
    this.m_speed = speed;
  
    addRequirements(m_elevatorSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    m_elevatorSub.runElevator(m_speed);

  }

  @Override
  public void end(boolean interrupted) {
    m_elevatorSub.stopElevator();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
