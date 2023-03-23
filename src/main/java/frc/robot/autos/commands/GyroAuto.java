
package frc.robot.autos.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;

public class GyroAuto extends CommandBase{
    private final AHRS gyro;
    private final Swerve motor;
    double m_speed;

    public GyroAuto(Swerve s_sub){
        this.motor = s_sub;
        this.gyro = s_sub.gyro;
        addRequirements(motor);
    }

    @Override
    public void execute() {
        if(gyro.getPitch()>10){
            while(gyro.getPitch()>5){
                m_speed = 0.35;
                motor.mSwerveMods[0].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
                motor.mSwerveMods[1].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
                motor.mSwerveMods[2].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
                motor.mSwerveMods[3].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
            }
        }
        if(gyro.getPitch()<-10){
            while(gyro.getPitch()<-5){
                m_speed = -0.35;
                motor.mSwerveMods[0].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
                motor.mSwerveMods[1].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
                motor.mSwerveMods[2].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
                motor.mSwerveMods[3].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
            }    
        }
        if(gyro.getPitch()<=5 && gyro.getPitch()>=-5){
            m_speed = 0;
            motor.mSwerveMods[0].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
            motor.mSwerveMods[1].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
            motor.mSwerveMods[2].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
            motor.mSwerveMods[3].mDriveMotor.set(ControlMode.PercentOutput, m_speed);
        }
    }
    
    @Override
    public void end(boolean interrupted) {
      motor.mSwerveMods[0].mDriveMotor.set(ControlMode.PercentOutput, 0);
      motor.mSwerveMods[1].mDriveMotor.set(ControlMode.PercentOutput, 0);
      motor.mSwerveMods[2].mDriveMotor.set(ControlMode.PercentOutput, 0);
      motor.mSwerveMods[3].mDriveMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}