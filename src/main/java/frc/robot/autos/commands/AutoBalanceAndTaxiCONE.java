// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.PneumaticSub;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.SliderSub;
import frc.robot.subsystems.Swerve;
import frc.robot.commands.*;
import frc.robot.commands.Levels.Level3Group;

public class AutoBalanceAndTaxiCONE extends SequentialCommandGroup {
  /** Creates a new AutoBalance. */
  public AutoBalanceAndTaxiCONE(Swerve driveSubsystem ,RotationSub m_RotationSub,SliderSub m_SliderSub,ElevatorSub m_ElevatorSub, PneumaticSub m_PneumaticSub) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(

      new Level3Group(m_RotationSub, m_SliderSub, m_ElevatorSub).withTimeout(1.7),
      new RunCommand(() ->  m_PneumaticSub.GripperClose(),m_PneumaticSub).withTimeout(0.1),
      new Zero(m_RotationSub, m_SliderSub, m_ElevatorSub),

      new RunCommand(() -> driveSubsystem.driveAuto(-1.3, 0.0, 0.0, true), driveSubsystem).withTimeout(1.8),
      new RunCommand(() -> driveSubsystem.driveAuto(-0.7, 0.0, 0.0, true), driveSubsystem).until(() -> driveSubsystem.isAtAutoBalanceAngle()),
         //.withTimeout(3),
         new RunCommand(() -> driveSubsystem.driveAuto(-0.8, 0.0, 0.0, true), driveSubsystem).withTimeout(2),
         
         new RunCommand(() -> driveSubsystem.driveAuto(1, 0.0, 0.0, true), driveSubsystem).withTimeout(2),
         new RunCommand(() -> driveSubsystem.driveAuto(0.3, 0.0, 0.0, true), driveSubsystem)
         .until(() -> driveSubsystem.isAtAutoBalanceAngleDown()),
        new RunCommand(driveSubsystem::xWheels, driveSubsystem).withTimeout(0.3)//0.1
        );
          
  }

}