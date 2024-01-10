// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Elevator.ElevatorDown;
import frc.robot.commands.Rotation.ZeroRotation;
import frc.robot.commands.Slider.SliderBackward;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.SliderSub;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Zero extends SequentialCommandGroup {
  /** Creates a new CUBE. */
  public Zero(RotationSub m_RotationSub,SliderSub m_SliderSub,ElevatorSub m_ElevatorSub) {
    super(
      new ElevatorDown(m_ElevatorSub).withTimeout(1.8).alongWith(new SliderBackward(m_SliderSub).withTimeout(1.5).alongWith(new ZeroRotation(m_RotationSub).withTimeout(1.7)))
      //new SliderForward(m_SliderSub).withTimeout(3)


    );
  }
}
