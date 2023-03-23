
package frc.robot.commands.Levels;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Rotation.RotationCube;
import frc.robot.subsystems.RotationSub;
//import frc.robot.commands.intake.IntakeBreake;
//import frc.robot.subsystems.IntakeSub;

public class CUBE extends SequentialCommandGroup {
  /** Creates a new CUBE. */
  public CUBE(RotationSub m_RotationSub) {
    super(
      new RotationCube(m_RotationSub).withTimeout(1)
      //new SliderForward(m_SliderSub).withTimeout(3)
    );
  }
}
