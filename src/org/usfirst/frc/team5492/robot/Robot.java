
package org.usfirst.frc.team5492.robot;

import org.usfirst.frc.team5492.robot.auto.*;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team5492.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static WheelArm wheelarm;
	public static Claw claw;
	public static OI oi;

    Command autonomousCommand;
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		drivetrain = new DriveTrain();
		claw = new Claw();
		wheelarm = new WheelArm();
        // instantiate the command used for the autonomous period
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Default program(Move to Auto Zone)", new DriveToAuto());
		autoChooser.addObject("Move Can to Auto Zone", new CanToAuto());
		autoChooser.addObject("Move Tote to Auto Zone", new ToteToAuto());
		autoChooser.addObject("Move Can + Tote to Auto Zone", new CanAndToteToAuto());
		autoChooser.addObject("Stacked Tote Set", new StackedToteSet());
		autoChooser.addObject("Get Cans from Step", new CansFromStep());
		autoChooser.addObject("Start Stacking Grey Totes", new StackGreyTotes());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void log() {
    	//wheelarm.log();
        elevator.log();
        drivetrain.log();
        claw.log();
    }}
