package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves can & tote to auto
 * Assumes Robot claw is around tote
 */
public class CanAndToteToAuto extends CommandGroup {
    
    public  CanAndToteToAuto() {
    	addSequential(new SetClawSetpoint(RobotMap.can));																//Closes Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_two + (RobotMap.level_two / 2)));	//Moves Elevator to Level 2.5
    	addSequential(new StrafeRight(2));										     														//Strafes Left 2 feet
    	addParallel(new OpenClaw());																											//Opens Claw
    	addSequential(new PickupTote());																									//Pickups tote/claw to level two
    	addSequential(new DriveForward(-7));																							//Drives backwards 7 feet
    }
}
