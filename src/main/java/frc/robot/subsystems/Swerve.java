
package frc.robot.subsystems;

import frc.robot.SwerveModule;
import frc.robot.Constants;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.I2C;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] mSwerveMods;
    public AHRS gyro;

    public Swerve() {
        gyro = new AHRS(I2C.Port.kOnboard);
        gyro.reset();
        zeroGyro();
        
        mSwerveMods = new SwerveModule[] {
            new SwerveModule(0, Constants.Swerve.Mod0.constants),
            new SwerveModule(1, Constants.Swerve.Mod1.constants),
            new SwerveModule(2, Constants.Swerve.Mod2.constants),
            new SwerveModule(3, Constants.Swerve.Mod3.constants)
        };

        Timer.delay(1.0);
        resetModulesToAbsolute();

        swerveOdometry = new SwerveDriveOdometry(
            Constants.Swerve.swerveKinematics, 
            getYaw(), 
            getModulePositions()
        );
    }

    public void driveAuto(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        var swerveModuleStates =

        Constants.Swerve.swerveKinematics.toSwerveModuleStates(
            fieldRelative
                ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, getYaw())
                : new ChassisSpeeds(xSpeed, ySpeed, rot)
        );

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);
            for(SwerveModule mod : mSwerveMods){
                mod.setDesiredState(swerveModuleStates[mod.moduleNumber], false);
            }
    }
      
    public double getRoll() {
        return gyro.getRoll();
    }

    public boolean isAtAutoBalanceAngle() {
        return (getRoll() > -8);
    }

    public boolean isAtAutoBalanceAngleDown() {
        return (getRoll() < 11);//10
    }

    public boolean yaw1() {
        return (gyro.getYaw()  <-110);
    }

    public boolean yaw2() {
        return (gyro.getYaw()  > -20);
    }

    public void xWheels() {
        driveAuto(-0.4, -0.4, -90, true);
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates =
            Constants.Swerve.swerveKinematics.toSwerveModuleStates(
                fieldRelative 
                    ? ChassisSpeeds.fromFieldRelativeSpeeds(
                        translation.getX(), 
                        translation.getY(), 
                        rotation, 
                        getYaw().times(-1)
                    )

                    : new ChassisSpeeds(
                        translation.getX(), 
                        translation.getY(), 
                        rotation
                    )
            );
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);

        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber],isOpenLoop);
        }
    }    

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);
        
        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }    

    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    }

    public SwerveModuleState[] getModuleStates(){
        SwerveModuleState[] states = new SwerveModuleState[4];

        for(SwerveModule mod : mSwerveMods){
            states[mod.moduleNumber] = mod.getState();
        }
        
        return states;
    }

    public SwerveModulePosition[] getModulePositions(){
        SwerveModulePosition[] positions = new SwerveModulePosition[4];

        for(SwerveModule mod : mSwerveMods){
            positions[mod.moduleNumber] = mod.getPosition();
        }
        
        return positions;
    }

    public void zeroGyro(){
        gyro.zeroYaw();
    }

    public Rotation2d getYaw() {
        return (Constants.Swerve.invertGyro) 
            ? Rotation2d.fromDegrees(360 - gyro.getYaw()) 
            : Rotation2d.fromDegrees(gyro.getYaw());
    }

    public void resetModulesToAbsolute(){
        for(SwerveModule mod : mSwerveMods){
            mod.resetToAbsolute();
        }
    }

    @Override
    public void periodic(){
        swerveOdometry.update(getYaw(), getModulePositions());  
        SmartDashboard.putNumber("Gyropitch ", gyro.getPitch());    
        SmartDashboard.putNumber("GyroRool ", gyro.getRoll());   
        SmartDashboard.putNumber("GyroYaw ", gyro.getYaw()); 
    //for(SwerveModule mod : mSwerveMods){
    //SmartDashboard.putNumber("Mod" + mod.moduleNumber+"Cancoder", mod.getCanCoder().getDegrees());
    // SmartDashboard.putNumber("Mod" + mod.moduleNumber+"Integrated", mod.getPosition().angle.getDegrees());
    //SmartDashboard.putNumber("Mod" + mod.moduleNumber+"Velocity", mod.getState().speedMetersPerSecond);    
    //}
    }
}
