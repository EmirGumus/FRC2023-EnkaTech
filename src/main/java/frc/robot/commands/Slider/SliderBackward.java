
package frc.robot.commands.Slider;

import frc.robot.Constants;
import frc.robot.subsystems.SliderSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SliderBackward extends CommandBase {
  private final SliderSub m_slider;
  double m_speed;
  private double s_encoder;

  public SliderBackward(SliderSub _slider) {
    this.m_slider = _slider;
    addRequirements(m_slider);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    if(!m_slider.s_switch.get()){
      m_slider.stopSlider();
    }
    s_encoder = m_slider.s_encoder.getPosition();

    m_speed = (6-s_encoder)* Constants.torso.sliderKP;
    m_slider.runSlider(m_speed);

   
 
  }
  
  @Override
  public void end(boolean interrupted) {
    m_slider.stopSlider();
    m_slider.s_encoder.setPosition(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
