// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Levels;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Elevator.ElevatorUp;
import frc.robot.commands.Rotation.*;
import frc.robot.commands.Slider.SliderForward;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.SliderSub;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Level3Group extends SequentialCommandGroup {
  /** Creates a new CUBE. */
  public Level3Group(RotationSub m_RotationSub,SliderSub m_SliderSub,ElevatorSub m_ElevatorSub) {
    super(
      new ElevatorUp(m_ElevatorSub).withTimeout(4).alongWith(new SliderForward(m_SliderSub).withTimeout(2).andThen(new Level3RotationUp(m_RotationSub).withTimeout(2)))
      //new SliderForward(m_SliderSub).withTimeout(3)


    );
  }
}
