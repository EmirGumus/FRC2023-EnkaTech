
package frc.robot.commands.Rotation;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RotationSub;

public class Level2RotationUp extends CommandBase {
  private final RotationSub m_RotationSub;
  private double m_speed;
  private double r_encoder;
  double error = -25;

  public Level2RotationUp(RotationSub _RotationSub) {
    this.m_RotationSub = _RotationSub;
    this.r_encoder = _RotationSub.m_encoder.getPosition();
    addRequirements(m_RotationSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    r_encoder = m_RotationSub.m_encoder.getPosition();

    m_speed = (Constants.torso.m_distance_rotationLevel2+error-r_encoder)* Constants.torso.gripperKP;
    m_RotationSub.runRotation(m_speed);
 
    if(r_encoder <= Constants.torso.m_distance_rotationLevel2){
    m_RotationSub.stopRotation();
    }


    
   /* while(r_encoder >= Constants.torso.m_distance_rotationCUBE){
      r_encoder = m_RotationSub.m_encoder.getPosition();
      m_speed = (Constants.torso.m_distance_rotationCUBE+error-r_encoder)* Constants.torso.gripperKPCUBE;
      m_RotationSub.runRotation(m_speed);
 
      //if(m_speed >= -0.3)  break;
  
    }*/
   // m_RotationSub.stopRotation();
  }

  @Override
  public void end(boolean interrupted) {
    m_RotationSub.stopRotation();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
