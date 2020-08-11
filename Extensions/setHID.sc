+ Langley {
	*setHID {
		HIDdef.element(
			key: \RightHorizontal,
			func: { |value|
				Langley.intHID("RightHorizontal", value);
			},
			elID: 3
		);

		HIDdef.element(
			key: \RightVertical,
			func: { |value|
				Langley.intHID("RightVertical", value);
			},
			elID: 4
		);

		HIDdef.element(
			key: \LeftVertical,
			func: { |value|
				Langley.intHID("LeftVertical", value);
			},
			elID: 1
		);

		HIDdef.element(
			key: \LeftHorizontal,
			func: { |value|
				Langley.intHID("LeftHorizontal", value);
			},
			elID: 2
		);

		HIDdef.device(
			key: \R1,
			func: { |value|
				Langley.intHID("R1", value);
			},
			elUsageName: \b6,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.device(
			key: \R2,
			func: { |value|
				Langley.intHID("R2", value);
			},
			elUsageName: \b8,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.device(
			key: \L1,
			func: { |value|
				Langley.intHID("L1", value);
			},
			elUsageName: \b5,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.device(
			key: \L2,
			func: { |value|
				Langley.intHID("L2", value);
			},
			elUsageName: \b7,
			deviceName: "Generic   USB  Joystick  ",
		);
		HIDdef.device(
			key: \SR1,
			func: { |value|
				Langley.intHID("SR1", value);
			},
			elUsageName: \b6,
			deviceName: "Trust Gamepad",
		);

		HIDdef.device(
			key: \SR2,
			func: { |value|
				Langley.intHID("SR2", value);
			},
			elUsageName: \b8,
			deviceName: "Trust Gamepad",
		);

		HIDdef.device(
			key: \SL1,
			func: { |value|
				Langley.intHID("SL1", value);
			},
			elUsageName: \b5,
			deviceName: "Trust Gamepad",
		);

		HIDdef.device(
			key: \SL2,
			func: { |value|
				Langley.intHID("SL2", value);
			},
			elUsageName: \b7,
			deviceName: "Trust Gamepad",
		);
	}
}
