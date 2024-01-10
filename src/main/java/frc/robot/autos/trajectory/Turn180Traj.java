
package frc.robot.autos.trajectory;
import java.util.List;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.Swerve;

public class Turn180Traj extends SequentialCommandGroup {
  /** Creates a new EmirAuto. 
   * @return */
  public Turn180Traj(Swerve SwerveSub) {
    TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
      AutoConstants.kMaxSpeedMetersPerSecond,
      AutoConstants.kMaxAccelerationMetersPerSecondSquared)
      .setKinematics(Constants.Swerve.swerveKinematics);
      trajectoryConfig.setReversed(false);

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
      List.of(
        new Pose2d(0,0,new Rotation2d(0)),
        new Pose2d(0,-1,new Rotation2d(12)) // 4->180derece (Süre veya x arttırılmalı)
      ), trajectoryConfig);

        // 3. Define PID controllers for tracking trajectory
    PIDController xController = new PIDController(AutoConstants.kPXController, 0, 0);
    PIDController yController = new PIDController(AutoConstants.kPYController, 0, 0);
    ProfiledPIDController thetaController = new ProfiledPIDController(
      AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

        // 4. Construct command to follow trajectory
    SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
      trajectory, 
      SwerveSub::getPose, 
      Constants.Swerve.swerveKinematics, 
      xController,
      yController,
      thetaController,
      SwerveSub::setModuleStates, 
      SwerveSub
    );

        // 5. Add some init and wrap-up, and return everything
    addCommands(
      new InstantCommand(() -> SwerveSub.resetOdometry(trajectory.getInitialPose())),
      swerveControllerCommand
    );
  }
}