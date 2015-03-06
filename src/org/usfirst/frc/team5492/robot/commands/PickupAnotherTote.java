package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * Assumes driver is positioned with claw above tote they want to pickup
 */
public class PickupAnotherTote extends CommandGroup {
    
    public  PickupAnotherTote() {
    	addSequential(new SetElevatorSetpoint(RobotMap.level_two));	//Moves to level 2
    	addSequential(new OpenClaw());													//Opens Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_one));	//Moves to level 1
    	addSequential(new CloseClaw());													//Closes Claw
    	addSequential(new SetElevatorSetpoint(RobotMap.level_two));	//Moves to level 2
    }
}
