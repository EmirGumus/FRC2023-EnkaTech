// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Zero;
//import frc.robot.commands.Levels.Level3Group;
import frc.robot.commands.intake.IntakeBreake;
import frc.robot.commands.intake.intakethrow3;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.PneumaticSub;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.SliderSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.Swerve;

public class AutoBalanceCUBE extends SequentialCommandGroup {
  /** Creates a new AutoBalance. */
  public AutoBalanceCUBE(Swerve driveSubsystem ,IntakeSub m_IntakeSub,RotationSub m_RotationSub,SliderSub m_SliderSub,ElevatorSub m_ElevatorSub, PneumaticSub m_PneumaticSub) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

    new IntakeBreake(-0.5, m_IntakeSub).withTimeout(0.1)
      .andThen(new RotationCubeAuto(m_RotationSub)).withTimeout(0.5)
      .andThen(new intakethrow3(0.5,m_IntakeSub)).withTimeout(1)
      .andThen(new Zero(m_RotationSub, m_SliderSub, m_ElevatorSub)).withTimeout(1.7),
  
    new RunCommand(() -> driveSubsystem.driveAuto(-0.7, 0.0, 0.0, true), driveSubsystem).withTimeout(3),
      new RunCommand(() -> driveSubsystem.driveAuto(-0.3, 0.0, 0.0, true), driveSubsystem)
            .until(() -> driveSubsystem.isAtAutoBalanceAngle()),
         //.withTimeout(3),
        new RunCommand(driveSubsystem::xWheels, driveSubsystem).withTimeout(0.2));
        
  }
}