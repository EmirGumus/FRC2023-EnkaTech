
package frc.robot.commands.Slider;

import frc.robot.Constants;
import frc.robot.subsystems.SliderSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SliderForwardHalf extends CommandBase {
  private final SliderSub m_slider;
  double m_speed;
  private double s_encoder;

  public SliderForwardHalf(SliderSub _slider) {
    this.m_slider = _slider;
    this.s_encoder = m_slider.s_encoder.getPosition();
    addRequirements(m_slider);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.s_encoder = m_slider.s_encoder.getPosition();

    m_speed = (Constants.torso.m_distance_slider_half-s_encoder)* Constants.torso.sliderKP;
    m_slider.runSlider(m_speed);

    if(s_encoder <= Constants.torso.m_distance_slider){
    m_slider.stopSlider();
    }
 

  }
  
  @Override
  public void end(boolean interrupted) {
    m_slider.stopSlider();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
