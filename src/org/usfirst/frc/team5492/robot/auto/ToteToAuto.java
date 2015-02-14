package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.CloseClaw;
import org.usfirst.frc.team5492.robot.commands.DriveForward;
import org.usfirst.frc.team5492.robot.commands.SetElevatorSetpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves tote to auto
 * Assumes robot is facing PS & claw is OPEN & in front of tote
 */
public class ToteToAuto extends CommandGroup {
    
    public  ToteToAuto() {
    	addSequential(new DriveForward(2.5));																						//Drives forward 2.5 feet
    	addSequential(new CloseClaw());																								//Closes Claw
    	addParallel(new SetElevatorSetpoint(RobotMap.level_one + (RobotMap.level_one / 2)));		//Moves Elevator to 1.5
    	addSequential(new DriveForward(-7));																						//Drives back 7 feet
    }
}
