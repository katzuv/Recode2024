package frc.robot.commandGroups

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.ConditionalCommand
import frc.robot.ControllerInputs
import frc.robot.Robot
import frc.robot.RobotContainer
import frc.robot.subsystems.intake.Intake
import frc.robot.subsystems.gripper.Gripper

object IntakeCommands {
    private val intake = Intake.getInstance()
    private val gripper = Gripper.getInstance()

    fun stopIntake(): Command = Commands.parallel(
        intake.stop(), gripper.stop(),
        ControllerInputs.stopRumble())

    fun intake(): Command {
        return Commands.parallel(
            intake.intake(), gripper.setRollerPower(0.4)
        )
            .until { gripper.hasNote && gripper.useSensor }
            .andThen(
                Commands.parallel(
                    intake.stop(),
                    gripper.setRollerPower(0.0),
                    ControllerInputs.startRumble().onlyIf{Robot.isTeleop}
                ))
            .withName("intake")
    }

    fun outtake(): Command {
        return Commands.parallel(
            intake.outtake(),
            gripper.setRollerPower(-0.7)
        ).withName("outtake")
    }
}
