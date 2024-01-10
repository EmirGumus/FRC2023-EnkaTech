// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Levels;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Elevator.*;
import frc.robot.commands.Rotation.*;
import frc.robot.commands.Slider.SliderForwardHalf;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.SliderSub;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HangarDown extends SequentialCommandGroup {
  /** Creates a new CUBE. */
  public HangarDown(ElevatorSub m_ElevatorSub,SliderSub m_SliderSub,RotationSub m_RotationSub) {
    super(
      new ElevatorHalfUpHangar(m_ElevatorSub).withTimeout(1.5).alongWith(new SliderForwardHalf(m_SliderSub).withTimeout(1.5).alongWith(new HangarDownRotation(m_RotationSub).withTimeout(2)))
      //new SliderForward(m_SliderSub).withTimeout(3)


    );
  }
}
