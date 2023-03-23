// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Levels;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Elevator.ElevatorHalfUp;
import frc.robot.commands.Rotation.*;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.RotationSub;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Level2Group extends SequentialCommandGroup {
  /** Creates a new CUBE. */
  public Level2Group(RotationSub m_RotationSub,ElevatorSub m_ElevatorSub) {
    super(
      new ElevatorHalfUp(m_ElevatorSub).withTimeout(2).alongWith(new Level2RotationUp(m_RotationSub).withTimeout(2))
      //new SliderForward(m_SliderSub).withTimeout(3)


    );
  }
}
