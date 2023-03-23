
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSub;

public class intakethrow2 extends CommandBase {
  private final IntakeSub m_intake;
  double m_speed;

  public intakethrow2(double speed ,IntakeSub _intake) {
    this.m_speed = speed;
    this.m_intake = _intake;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_intake.runIntake(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return  false;
  }
}
