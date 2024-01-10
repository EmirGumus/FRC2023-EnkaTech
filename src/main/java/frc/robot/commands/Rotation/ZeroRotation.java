
package frc.robot.commands.Rotation;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RotationSub;

public class ZeroRotation extends CommandBase {
  private final RotationSub m_RotationSub;
  private double m_speed;
  private double r_encoder;
  double error = 30;

  public ZeroRotation(RotationSub _RotationSub) {
    this.m_RotationSub = _RotationSub;
    addRequirements(m_RotationSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    r_encoder = m_RotationSub.m_encoder.getPosition();

    m_speed = (error-r_encoder)* Constants.torso.gripperKP;
    m_RotationSub.runRotation(m_speed);

    if(!m_RotationSub.r_switch.get()){  
      m_RotationSub.stopRotation();
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    m_RotationSub.stopRotation();
    m_RotationSub.m_encoder.setPosition(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
