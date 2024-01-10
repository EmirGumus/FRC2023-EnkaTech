
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.autos.commands.AutoBalanceCONE;
import frc.robot.autos.commands.AutoBalanceCUBE;
import frc.robot.autos.commands.AutoBalanceAndTaxiCONE;
import frc.robot.autos.commands.AutoBalanceAndTaxiCUBE;
import frc.robot.autos.commands.CubeandTaxi;
import frc.robot.commands.*;
import frc.robot.commands.Levels.HangarDown;
import frc.robot.commands.Levels.HangarUp;
import frc.robot.commands.Levels.*;
import frc.robot.commands.Pneumatic.*;
import frc.robot.commands.intake.*;
import frc.robot.subsystems.*;
//import frc.robot.commands.Slider.SliderBackward;
//import frc.robot.commands.Slider.SliderForward;

public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driver = new CommandXboxController(0);
    private final CommandXboxController operator = new CommandXboxController(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    //private final JoystickButton zeroGyro = new JoystickButton(driver.getHID(), XboxController.Button.kY.value);
    //private final JoystickButton robotCentric = new JoystickButton(driver.getHID(), XboxController.Button.kX.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ElevatorSub m_Elevator = new ElevatorSub();
    private final IntakeSub m_Intake = new IntakeSub();
    private final RotationSub m_Rotation = new RotationSub();
    private final SliderSub m_Slider = new SliderSub();
    private final PneumaticSub m_Pneumatic = new PneumaticSub();

    /* Dashboard */
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        m_chooser.setDefaultOption(
            "Cone-Taxi-Station", 
            new AutoBalanceAndTaxiCONE(s_Swerve,m_Rotation,m_Slider,m_Elevator,m_Pneumatic)
        );

        m_chooser.addOption(
            "Cone-Station", 
            new AutoBalanceCONE(s_Swerve,m_Rotation,m_Slider,m_Elevator,m_Pneumatic)
        );

        m_chooser.addOption(
            "Cube-Taxi-Station", 
            new AutoBalanceAndTaxiCUBE(s_Swerve,m_Intake,m_Rotation,m_Slider,m_Elevator,m_Pneumatic)
        );

        m_chooser.addOption(
            "Cube-Station", 
            new AutoBalanceCUBE(s_Swerve,m_Intake,m_Rotation,m_Slider,m_Elevator,m_Pneumatic)
        );

        m_chooser.addOption(
            "Cube-Taxi", 
            new CubeandTaxi(s_Swerve,m_Intake, m_Rotation,m_Slider,m_Elevator,m_Pneumatic)
        );

        SmartDashboard.putData("Autonomous", m_chooser);
        
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> driver.getRawAxis(translationAxis)*0.7,
                () -> driver.getRawAxis(strafeAxis)*0.7,
                () -> -driver.getRawAxis(rotationAxis)*0.4, 
                () -> false //!robotCentric.getAsBoolean()
            )
        ); 
        configureButtonBindings();
    }
 
    private void configureButtonBindings() {
        /* Driver Buttons */
        //zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        //Driver Kumanda
        driver.a().whileTrue(new GripperOpen(m_Pneumatic));
        driver.b().whileTrue(new GripperClose(m_Pneumatic));
        driver.x().onTrue(new HangarDown(m_Elevator,m_Slider,m_Rotation));
        driver.y().onTrue(new HangarUp(m_Elevator,m_Rotation));
        driver.button(5).whileTrue(new intakethrow3(0.5, m_Intake)); 
        driver.button(6).whileTrue(new intakethrow2(0.15, m_Intake));

        // Operatör Kumanda
        operator.a().onTrue(new Level1Group(m_Rotation));
        operator.b().onTrue(new Level2Group(m_Rotation,m_Elevator));
        operator.x().onTrue(new Zero(m_Rotation,m_Slider,m_Elevator));
        operator.y().onTrue(new Level3Group(m_Rotation,m_Slider,m_Elevator));
        operator.button(5).whileTrue(new IntakeBreake(-0.35, m_Intake));
        operator.button(6).onTrue(new CUBE(m_Rotation));
        operator.button(7).onTrue(new IntakeBreake(0, m_Intake));
        operator.button(9).onTrue(new MiniGripOpen(m_Pneumatic));
        operator.button(10).onTrue(new MiniGripClose(m_Pneumatic));
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();

        /* 
        return new AutoBalanceCONE(s_Swerve,m_Rotation,m_Slider,m_Elevator,m_Pneumatic);
        return new AutoBalanceCUBE(s_Swerve,m_Intake,m_Rotation,m_Slider,m_Elevator,m_Pneumatic);
        return new AutoBalanceAndTaxiCONE(s_Swerve,m_Rotation,m_Slider,m_Elevator,m_Pneumatic);
        return new AutoBalanceAndTaxiCUBE(s_Swerve,,m_Intake,m_Rotation,m_Slider,m_Elevator,m_Pneumatic);
        return new CubeandTaxi(s_Swerve,m_Intake, m_Rotation,m_Slider,m_Elevator,m_Pneumatic);
        */
    }
}
