
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.commands.Levels.HangarDown;
import frc.robot.commands.Levels.HangarUp;
import frc.robot.commands.Levels.*;
import frc.robot.commands.Pneumatic.*;
import frc.robot.commands.intake.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driver = new CommandXboxController(0);
    private final CommandXboxController operator = new CommandXboxController(1);
    //private final int var = 1; // TODO Add manual/automatic control

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver.getHID(), XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver.getHID(), XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ElevatorSub m_Elevator = new ElevatorSub();
    private final IntakeSub m_Intake = new IntakeSub();
    private final RotationSub m_Rotation = new RotationSub();
    private final SliderSub m_Slider = new SliderSub();
    private final PneumaticSub m_Pneumatic = new PneumaticSub();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> driver.getRawAxis(translationAxis)*0.80, 
                () -> driver.getRawAxis(strafeAxis)*0.80, 
                () -> -driver.getRawAxis(rotationAxis)*0.80, 
                () -> robotCentric.getAsBoolean()
            )
        );
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        //Driver Kumanda
        driver.button(5).whileTrue(new intakethrow3(0.3, m_Intake));
        driver.button(6).whileTrue(new intakethrow2(0.1, m_Intake));
        driver.a().whileTrue(new GripperOpen(m_Pneumatic));
        driver.b().whileTrue(new GripperClose(m_Pneumatic));
        driver.x().onTrue(new HangarDown(m_Elevator,m_Rotation));
        driver.y().onTrue(new HangarUp(m_Elevator,m_Rotation));
        driver.button(8).onTrue(new Zero(m_Rotation,m_Slider,m_Elevator));
        driver.button(7).whileTrue(new IntakeBreake(-0.5, m_Intake));
        
        // Operatör Kumanda
        operator.x().onTrue(new Zero(m_Rotation,m_Slider,m_Elevator));
        operator.a().onTrue(new Level1Group(m_Rotation));
        operator.b().onTrue(new Level2Group(m_Rotation,m_Elevator));
        operator.y().onTrue(new Level3Group(m_Rotation,m_Slider,m_Elevator));
        operator.button(5).whileTrue(new IntakeBreake(-0.5, m_Intake));
        operator.button(6).onTrue(new CUBE(m_Rotation));
    }
    public Command getAutonomousCommand() {
        return new CUBE_auto(m_Rotation, m_Slider, m_Elevator, m_Intake, s_Swerve);
    }
}
