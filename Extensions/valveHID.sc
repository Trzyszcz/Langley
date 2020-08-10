+ Langley {
	*valveHID { | message_type, value |
		message_type.postln;
		value.postln;
		/*
		if( message_type == "R1",
			{ Langley.pushvalve(1, value) },
		);
		*/

		switch( message_type,
			"R1", { Langley.pushvalve(1, value) },
			"L1", { Langley.pushvalve(2, value) },
			"R2", { Langley.pushvalve(4, value) },
			"L2", { Langley.pushvalve(8, value) },
			"SR1", { Langley.pushvalve(16, value) },
			"SL1", { Langley.pushvalve(32, value) },
			"SR2", { Langley.pushvalve(64, value) },
			"SL2", { Langley.pushvalve(1/2, value) },
		);

		}
}
