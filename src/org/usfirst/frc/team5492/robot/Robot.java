
package org.usfirst.frc.team5492.robot;

import org.usfirst.frc.team5492.robot.commands.*;
import org.usfirst.frc.team5492.robot.auto.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.CameraServer;

import org.usfirst.frc.team5492.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	CameraServer server;
	
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static Claw claw;
	public static OI oi;

    Command autonomousCommand;
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {		
    	 server = CameraServer.getInstance();
         server.setQuality(50);
         //the camera name (ex "cam0") can be found through the roborio web interface
         //server.startAutomaticCapture("cam0");
    	
		drivetrain = new DriveTrain();
		elevator = new Elevator();
		claw = new Claw();
		oi = new OI();
        // instantiate the command used for the autonomous period
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Default program(Do Nothing)", new TimeDrive(0, 0));
		autoChooser.addObject("Drive Forward from LandFill to Auto Zone", new RobotToAuto());
		autoChooser.addObject("Drive Forward with Tote to Auto Zone", new ToteToAuto());
		autoChooser.addObject("Drive Forward with Tote to Auto Zone *BUMP*", new ToteToAutoBump());
		//autoChooser.addObject("Move Backwards with Can to Auto Zone", new CanToAuto());
		//autoChooser.addObject("Move Can + Tote to Auto Zone", new CanAndToteToAuto());
		//autoChooser.addObject("Stacked Tote Set", new StackedToteSet());
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	 autonomousCommand = (Command) autoChooser.getSelected();
    	 autonomousCommand.start();
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
        elevator.log();
        //drivetrain.log();
        claw.log();
    }
}