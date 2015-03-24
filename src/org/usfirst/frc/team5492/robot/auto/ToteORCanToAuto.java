package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves tote to auto
 * Assumes robot is facing PS & claw is OPEN & in front of tote
 */
public class ToteORCanToAuto extends CommandGroup {
    
    public  ToteORCanToAuto() {
    	addSequential(new DriveForward(3.5));																						//Drives forward 2.5 feet
    }
}