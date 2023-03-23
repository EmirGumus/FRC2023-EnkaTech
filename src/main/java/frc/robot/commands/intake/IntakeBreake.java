
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSub;

public class IntakeBreake extends CommandBase {
  private final IntakeSub m_intake;
  double m_speed;

  public IntakeBreake(double speed ,IntakeSub _intake) {
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
    m_intake.breakeintake();
  }

  @Override
  public boolean isFinished() {
    return  false;
  }
}
