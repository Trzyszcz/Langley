+ Langley {
	*setHID {

		HIDdef.element(
			key: \LeftPush,
			func: { |value|
				Langley.intHID("LeftPush", value);
			},
			elID: 16,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.element(
			key: \RightPush,
			func: { |value|
				Langley.intHID("RightPush", value);
			},
			elID: 17,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.element(
			key: \RightHorizontal,
			func: { |value|
				Langley.intHID("RightHorizontal", value);
			},
			elID: 3,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.element(
			key: \RightVertical,
			func: { |value|
				Langley.intHID("RightVertical", value);
			},
			elID: 4,
			deviceName: "Generic   USB  Joystick  "
		);

		HIDdef.element(
			key: \LeftVertical,
			func: { |value|
				Langley.intHID("LeftVertical", value);
			},
			elID: 1,
			deviceName: "Generic   USB  Joystick  "
		);

		HIDdef.element(
			key: \LeftHorizontal,
			func: { |value|
				Langley.intHID("LeftHorizontal", value);
			},
			elID: 0,
			deviceName: "Generic   USB  Joystick  "
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
			//deviceName: "Trust Gamepad",
			deviceName: "USB Joystick          ",
		);

		HIDdef.device(
			key: \SR2,
			func: { |value|
				Langley.intHID("SR2", value);
			},
			elUsageName: \b8,
			//deviceName: "Trust Gamepad",
			deviceName: "USB Joystick          ",
		);

		HIDdef.device(
			key: \SL1,
			func: { |value|
				Langley.intHID("SL1", value);
			},
			elUsageName: \b5,
			//deviceName: "Trust Gamepad",
			deviceName: "USB Joystick          ",
		);

		HIDdef.device(
			key: \SL2,
			func: { |value|
				Langley.intHID("SL2", value);
			},
			elUsageName: \b7,
			//deviceName: "Trust Gamepad",
			deviceName: "USB Joystick          ",
		);

		HIDdef.usage(
			key: \A,
			func: { |value|
				Langley.intHID("A", value);
			},
			elUsageName: \b1,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.usage(
			key: \X,
			func: { |value|
				Langley.intHID("X", value);
			},
			elUsageName: \b3,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.usage(
			key: \O,
			func: { |value|
				Langley.intHID("O", value);
			},
			elUsageName: \b2,
			deviceName: "Generic   USB  Joystick  ",
		);

		HIDdef.usage(
			key: \D,
			func: { |value|
				Langley.intHID("D", value);
			},
			elUsageName: \b4,
			deviceName: "Generic   USB  Joystick  ",
		);

		if( (vintage==false), {
			HIDdef.device(
				key: \RF,
				func: { |value|
					Langley.intHID("FR", value);
				},
				elUsageName: \Y,
				deviceName: "Saitek R220 ",
			);
		}
		);

	}
}
