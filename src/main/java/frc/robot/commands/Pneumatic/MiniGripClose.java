
package frc.robot.commands.Pneumatic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticSub;

public class MiniGripClose extends CommandBase {
  private final PneumaticSub m_Pneumatic;

  public MiniGripClose(PneumaticSub _Pneumatic) {
    this.m_Pneumatic = _Pneumatic;
    addRequirements(m_Pneumatic);
  }

  @Override
  public void initialize() {
    m_Pneumatic.MiniGripClose();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
