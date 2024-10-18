package frc.robot.commandGroups

import edu.wpi.first.units.Units
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import frc.robot.ControllerInputs
import frc.robot.lib.finallyDo
import frc.robot.lib.getRotationToTranslation
import frc.robot.subsystems.hood.Hood
import frc.robot.subsystems.shooter.Shooter
import frc.robot.subsystems.conveyor.Conveyor
import frc.robot.subsystems.gripper.Gripper
import frc.robot.subsystems.swerve.SwerveDrive
import org.littletonrobotics.junction.Logger

object ShootingCommands {
    private val swerveDrive = SwerveDrive.getInstance()
    private val shooter = Shooter.getInstance()
    private val hood = Hood.getInstance()
    private val conveyor = Conveyor.getInstance()

    fun closeShoot(): Command {
        return WarmupCommands.warmup(
            { Units.Degrees.of(97.0) },
            { Units.RotationsPerSecond.of(45.0) },
            { Units.RotationsPerSecond.of(45.0) }
        )
    }

    fun trussSetpoint(): Command {
        return WarmupCommands.warmup(
            { Units.Degrees.of(77.0) },
            { Units.RotationsPerSecond.of(45.0) },
            { Units.RotationsPerSecond.of(45.0) }
        )
    }

    fun finishScore(): Command {
        return Gripper.getInstance().feed().alongWith(Commands.waitSeconds(0.5)).andThen(WarmupCommands.stopWarmup())
    }

    private fun shootOverStageInit(): Command {
        return Commands.parallel(
            WarmupCommands.warmup(
                { ShootOverStageConstants.HOOD_ANGLE_SUPER_POOP },
                { ShootOverStageConstants.SHOOTER_VELOCITY_SUPER_POOP },
                { ShootOverStageConstants.CONVEYOR_VELOCITY_SUPER_POOP }
            ),
            swerveDrive.driveAndAdjust(
                {Units.Degrees.of(swerveDrive.estimator.estimatedPosition.translation.getRotationToTranslation(
                    ShootOverStageConstants.SUPER_POOP_TRANSLATION).degrees)},
                {-ControllerInputs.driverController().leftY},
                {-ControllerInputs.driverController().leftX},
                ShootOverStageConstants.SUPER_POOP_TURN_TOLERANCE,
                0.1,
                true
            )
        ).until { shooterConveyorHoodAtSetpoint() }
    }

    private fun shootOverStageEnd(): Command {
        return WarmupCommands.stopWarmup().alongWith(Gripper.getInstance().feed())
    }

    fun shootOverStage(): Command = shootOverStageInit().finallyDo(shootOverStageEnd())

    fun shooterConveyorHoodAtSetpoint(): Boolean {
        return (shooter.atSetpoint() && conveyor.atSetPoint() && hood.atSetpoint()).also {
            Logger.recordOutput(
                "ShootingCommands/atScoringSetpoint",
                it
            )
        }
    }
}
