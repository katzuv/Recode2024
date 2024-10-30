package frc.robot

class Ports {
    object SwerveDrive {
        private const val FRONT_LEFT_DRIVE_MOTOR_ID = 3
        private const val FRONT_LEFT_ANGLE_MOTOR_ID = 4
        private const val FRONT_RIGHT_DRIVE_MOTOR_ID = 7
        private const val FRONT_RIGHT_ANGLE_MOTOR_ID = 8
        private const val REAR_LEFT_DRIVE_MOTOR_ID = 5
        private const val REAR_LEFT_ANGLE_MOTOR_ID = 2
        private const val REAR_RIGHT_DRIVE_MOTOR_ID = 1
        private const val REAR_RIGHT_ANGLE_MOTOR_ID = 6

        val DRIVE_IDS: IntArray =
            intArrayOf(
                FRONT_LEFT_DRIVE_MOTOR_ID,
                FRONT_RIGHT_DRIVE_MOTOR_ID,
                REAR_LEFT_DRIVE_MOTOR_ID,
                REAR_RIGHT_DRIVE_MOTOR_ID,
            )
        val ANGLE_IDS: IntArray =
            intArrayOf(
                FRONT_LEFT_ANGLE_MOTOR_ID,
                FRONT_RIGHT_ANGLE_MOTOR_ID,
                REAR_LEFT_ANGLE_MOTOR_ID,
                REAR_RIGHT_ANGLE_MOTOR_ID,
            )

        private const val FRONT_LEFT_ENCODER_ID = 2
        private const val FRONT_RIGHT_ENCODER_ID = 7
        private const val REAR_LEFT_ENCODER_ID = 3
        private const val REAR_RIGHT_ENCODER_ID = 8

        val ENCODER_IDS: IntArray =
            intArrayOf(
                FRONT_LEFT_ENCODER_ID,
                FRONT_RIGHT_ENCODER_ID,
                REAR_LEFT_ENCODER_ID,
                REAR_RIGHT_ENCODER_ID,
            )

        private const val FRONT_LEFT_DRIVE_INVERTED = true
        private const val FRONT_LEFT_ANGLE_INVERTED = true
        private const val FRONT_RIGHT_DRIVE_INVERTED = true
        private const val FRONT_RIGHT_ANGLE_INVERTED = true
        private const val REAR_LEFT_DRIVE_INVERTED = true
        private const val REAR_LEFT_ANGLE_INVERTED = true
        private const val REAR_RIGHT_DRIVE_INVERTED = true
        val DRIVE_INVERTED: BooleanArray =
            booleanArrayOf(
                FRONT_LEFT_DRIVE_INVERTED,
                FRONT_RIGHT_DRIVE_INVERTED,
                REAR_LEFT_DRIVE_INVERTED,
                REAR_RIGHT_DRIVE_INVERTED,
            )
        private const val REAR_RIGHT_ANGLE_INVERTED = true
    }

    object UI {
        const val JOYSTICK_TRIGGER = 1
        const val JOYSTICK_TOP_BOTTOM_BUTTON = 2
        const val JOYSTICK_TOP_LEFT_BUTTON = 3
        const val JOYSTICK_TOP_RIGHT_BUTTON = 4
        const val JOYSTICK_RIGHT_BIG_BUTTON = 16
    }
}
