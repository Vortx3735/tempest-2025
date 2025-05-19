package frc.robot.util;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class VorTXControllerXbox extends CommandXboxController {
  public Trigger aButton,
      bButton,
      xButton,
      yButton,
      view,
      menu,
      ls,
      rs,
      lb,
      rb,
      lt,
      rt,
      povUp,
      povUpRight,
      povRight,
      povDownRight,
      povDown,
      povDownLeft,
      povLeft,
      povUpLeft;

  public VorTXControllerXbox(int port) {
    super(port);

    aButton = this.a();
    bButton = this.b();
    xButton = this.x();
    yButton = this.y();
    lb = this.leftBumper();
    rb = this.rightBumper();
    view = this.back();
    menu = this.start();
    ls = this.leftStick();
    rs = this.rightStick();
    lt = this.leftTrigger();
    rt = this.rightTrigger();

    povUp = this.povUp();
    povUpRight = this.povUpRight();
    povRight = this.povRight();
    povDownRight = this.povDownRight();
    povDown = this.povDown();
    povDownLeft = this.povDownLeft();
    povLeft = this.povLeft();
    povUpLeft = this.povUpLeft();
  }

  /**
   * This method is to fix the flawed input of an XBOX controller. The input is a box, both X and Y
   * axis can go from -1 to 1. However, since the controller build physically forces the joystick
   * into a circle, its impossible to reach the the corner of the box. This method fixes that issue.
   *
   * <p>This was originally made for driving swerve, as you don't want to slow down when moving
   * diagonally.
   *
   * @param isLeft - Are you asking for the left joystick?
   * @param isX - Are you asking for the X Axis?
   * @return The value of whichever joystick and axis you asked for.
   */
  public double joystickFix(boolean isLeft, boolean isX) {
    double root2 = Math.sqrt(2);
    double x = isLeft ? getTrueLeftX() : getTrueRightX();
    double y = isLeft ? getTrueLeftY() : getTrueRightY();
    double magnitude = Math.sqrt(x * x + y * y);
    double[] joystick = {
      Math.signum(x) * Math.min(Math.abs(x * root2), magnitude),
      Math.signum(y) * Math.min(Math.abs(y * root2), magnitude)
    };
    return isX ? joystick[0] : joystick[1];
  }

  /**
   * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean)
   * joystickFix(isLeft, isX)} method To get the true value of the joystick, please use the {@link
   * #getTrueLeftX() getTrueLeftX()} method
   */
  @Override
  public double getLeftX() {
    return joystickFix(true, true);
  }

  /**
   * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean)
   * joystickFix(isLeft, isX)} method To get the true value of the joystick, please use the {@link
   * #getTrueLeftY() getTrueLeftY()} method
   */
  @Override
  public double getLeftY() {
    return joystickFix(true, false);
  }

  /**
   * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean)
   * joystickFix(isLeft, isX)} method To get the true value of the joystick, please use the {@link
   * #getTrueRightX() getTrueRightX()} method
   */
  @Override
  public double getRightX() {
    return joystickFix(false, true);
  }

  /**
   * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean)
   * joystickFix(isLeft, isX)} method To get the true value of the joystick, please use the {@link
   * #getTrueRightY() getTrueRightY()} method
   */
  @Override
  public double getRightY() {
    return joystickFix(false, false);
  }

  public double getTrueLeftX() {
    return getHID().getLeftX();
  }

  public double getTrueLeftY() {
    return getHID().getLeftY();
  }

  public double getTrueRightX() {
    return getHID().getRightX();
  }

  public double getTrueRightY() {
    return getHID().getRightY();
  }

  public void setRumble(RumbleType rumble, double intensity) {
    getHID().setRumble(rumble, intensity);
  }
}