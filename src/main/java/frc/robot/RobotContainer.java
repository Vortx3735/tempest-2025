// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.util.VorTXControllerXbox;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drive;

public class RobotContainer {
  private final Drive m_drive = new Drive();
  private final VorTXControllerXbox driver = new VorTXControllerXbox(0);

  public RobotContainer() {
    configureBindings();
  }


  private void configureBindings() {
    m_drive.setDefaultCommand(new RunCommand(()-> m_drive.tankDrive(driver.getLeftY(), driver.getRightY()), m_drive));
  }

  
  public Command getAutonomousCommand() {
    return new WaitCommand(0);
  }
}
