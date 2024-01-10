
package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorDown extends CommandBase {
  private final ElevatorSub m_elevatorSub;
  private double m_speed;
  private double e_encoder;

  public ElevatorDown(ElevatorSub _ElevatorSub) {
    this.m_elevatorSub = _ElevatorSub;
    this.e_encoder = _ElevatorSub.e_encoder.getPosition();
    addRequirements(m_elevatorSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(e_encoder >=0){
      m_elevatorSub.stopElevator();
    }

    //if(m_elevatorSub.e_switch.get()){
    //  m_elevatorSub.stopElevator();
    //}

    e_encoder = m_elevatorSub.e_encoder.getPosition();
    m_speed = (0-e_encoder)* Constants.torso.elevatorKP;
    m_elevatorSub.runElevator(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_elevatorSub.stopElevator();
    m_elevatorSub.e_encoder.setPosition(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
