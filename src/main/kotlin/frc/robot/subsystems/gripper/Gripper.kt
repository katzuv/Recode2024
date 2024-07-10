package frc.robot.subsystems.gripper

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.Command
import org.littletonrobotics.junction.Logger


class Gripper private constructor(private val io: GripperIO): SubsystemBase() {
    private val inputs: LoggedGripperInputs = io.inputs
    private val timer = Timer()

    companion object {
        @Volatile
        private var instance: Gripper? = null

        fun initialize(io: GripperIO) {
            synchronized(this) {
                if (instance == null) {
                    instance = Gripper(io)
                }
            }
        }

        fun getInstance() : Gripper {
            return instance ?: throw IllegalArgumentException(
                "Gripper has not been initialized. Call initialize(io: GripperIO) first."
            )
        }
    }

    init {
        timer.start()
        timer.reset()
    }

    fun hasNote(): Boolean = inputs.hasNote

    fun setRollerPower(power: Double): Command {
        return run { io.setRollerMotorPower(power) }.withName("Set Roller Power")
    }

    override fun periodic() {
        io.updateInputs()
        if (timer.advanceIfElapsed(0.1)) Logger.processInputs(this::class.simpleName, inputs)
    }
}