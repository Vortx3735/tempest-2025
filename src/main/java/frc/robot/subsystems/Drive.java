package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Drive extends SubsystemBase {
    private TalonSRX l1;
	private TalonSRX l2;
	private TalonSRX l3;
	
	private TalonSRX r1;
	private TalonSRX r2;
	private TalonSRX r3;

    public Drive() {
        l1 = new TalonSRX(3);
        l2 = new TalonSRX(4);
        l3 = new TalonSRX(5);

        r1 = new TalonSRX(10);
        r2 = new TalonSRX(11);
        r3 = new TalonSRX(12);

        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = 40; // the peak current, in amps
        config.peakCurrentDuration = 1500; // the time at the peak current before the limit triggers, in ms
        config.continuousCurrentLimit = 30; // the current to maintain if the peak limit is triggered
        l1.configAllSettings(config);
        l2.configAllSettings(config);
        l3.configAllSettings(config);
        r1.configAllSettings(config);
        r2.configAllSettings(config);
        r3.configAllSettings(config);

        l1.setInverted(true);
        l2.follow(l1);
        l3.follow(l1);
        r2.follow(r1);
        r3.follow(r1);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
    private void setLeft(double speed) {
        l1.set(TalonSRXControlMode.PercentOutput, speed);
    }
    private void setRight(double speed) {
        r1.set(TalonSRXControlMode.PercentOutput, speed);
    }
    public void tankDrive(double leftSpeed, double rightSpeed) {
        setLeft(leftSpeed);
        setRight(rightSpeed);
    }
    public void stop() {
        setLeft(0);
        setRight(0);
    }
}
