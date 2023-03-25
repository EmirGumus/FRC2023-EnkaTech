
package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.commands.RotationCubeAuto;
import frc.robot.autos.trajectory.TrajMinusX;
import frc.robot.autos.trajectory.Turn180Traj;
import frc.robot.autos.trajectory.Turn90Traj;
import frc.robot.commands.intake.IntakeBreake;
import frc.robot.commands.intake.intakethrow3;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.SliderSub;
import frc.robot.subsystems.Swerve;
import frc.robot.autos.commands.GyroAuto;
import frc.robot.autos.trajectory.TrajPlusX;
import frc.robot.autos.trajectory.TrajPlusY;
import frc.robot.commands.Rotation.RotationCube;
import frc.robot.commands.intake.intakethrow2;

public class CUBE_auto extends SequentialCommandGroup {
  /** Creates a new CUBE. */
  public CUBE_auto(RotationSub m_RotationSub, SliderSub m_SliderSub, ElevatorSub m_Elevator,IntakeSub m_IntakeSub, Swerve m_Swerve) {
    super(
      new IntakeBreake(-0.5, m_IntakeSub).withTimeout(0.1)
      .andThen(new RotationCubeAuto(m_RotationSub).withTimeout(0.7)
      .andThen(new intakethrow3(0.5,m_IntakeSub).withTimeout(0.3))
      .andThen(new TrajMinusX(m_Swerve)).withTimeout(5)
      .andThen(new
TrajPlusY(m_Swerve)).withTimeout(8)
      .andThen(new TrajPlusX(m_Swerve)).withTimeout(0.5)
      .andThen(new GyroAuto(m_Swerve)).withTimeout(0.1)
      )
    );
  }
}
