package frc.robot.subsystems.gripper

import edu.wpi.first.units.Units
import edu.wpi.first.wpilibj.Timer
import frc.robot.lib.motors.SparkMaxSim

class GripperIOSim : GripperIO {
    override val inputs = LoggedGripperInputs()
    private val rollerMotor = SparkMaxSim(1, 1.0, 0.5, 1.0)

    override fun setRollerMotorPower(power: Double) {
        rollerMotor.set(power)
    }

    override fun updateInputs() {
        rollerMotor.update(Timer.getFPGATimestamp())
        inputs.rollerMotorVoltage.mut_replace(rollerMotor.busVoltage, Units.Volts)
    }
}