package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.*;

/**
 * Attempts to do stacked tote set
 * From PS perspective:
 * Assumes robot is positioned on the RIGHT side, facing left side with claw around tote on end
 */
public class StackedToteSet extends CommandGroup {
    
    public  StackedToteSet() {
    	addParallel(new CloseClaw());																//Pickups first tote
    	for(int x = 0; x < 2; x++){																		//Repeats for each set of totes
    		addSequential(new StrafeRight(2.5));												//Strafes right 2.5 feet
        	addSequential(new DriveForward(6));												//Drive Forward 6 feet
        	addSequential(new StrafeRight(-2.5));											//Strafes Left 2.5 feet
        	addSequential(new SetElevatorSetpoint(RobotMap.level_two));	//Moves to level 2
        	addSequential(new DriveForward(3));												//Drives forward 3 feet
        	addSequential(new PickupAnotherTote());										//Pickups Another Tote
    	}    	
    	addSequential(new StrafeRight(10));													//Moves robot to auto zone
    }
}
