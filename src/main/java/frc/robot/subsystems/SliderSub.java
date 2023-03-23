
package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SliderSub extends SubsystemBase {
  private final CANSparkMax sliderNeo = new CANSparkMax(Constants.CanIDs.SliderNeo, MotorType.kBrushless);
  public  DigitalInput s_switch = new DigitalInput(Constants.torso.magswitch_slider);
  public RelativeEncoder s_encoder;

  public SliderSub() {
    sliderNeo.setInverted(true);
    s_encoder = sliderNeo.getEncoder();
    s_encoder.setPosition(0);
  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Slider Encoder", s_encoder.getPosition());
  }

  public void runSlider(double speed){

    sliderNeo.set(speed);
  }

  public void stopSlider(){
    sliderNeo.set(0);
  }
}
