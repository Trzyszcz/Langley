+ Langley {
	*setHID {
		HIDdef.element(
			key: \RightHorizontal,
			func: { |value|
				Langley.intHID("RightHorizontal", value);
			},
			elID: 3
		);
		
		HIDdef.usage(
			key: \R1,
			func: { |value|
				Langley.intHID("R1", value);
			},
			elUsageName: \b6,
		);
	}
}
