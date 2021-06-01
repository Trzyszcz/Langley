+ Langley {
	*vintage_valveHID { | message_type, value |
		if ( message_type != "LeftHorizontal",
			{message_type.postln; value.postln}
		);

		if ( Langley.learningMode == true,
			{
				switch( message_type,
					"R1", { ~memorylist.add("R1" + value + Date.getDate.hourStamp ++ "\n"); },
					"L1", { ~memorylist.add("L1" + value + Date.getDate.hourStamp ++ "\n"); },
					"R2", { ~memorylist.add("R2" + value + Date.getDate.hourStamp ++ "\n"); },
					"L2", { ~memorylist.add("L2" + value + Date.getDate.hourStamp ++ "\n"); },
					"SR1", { ~memorylist.add("SR1" + value + Date.getDate.hourStamp ++ "\n"); },
					"SL1", { ~memorylist.add("SL1" + value + Date.getDate.hourStamp ++ "\n"); },
					"SR2", { ~memorylist.add("SR2" + value + Date.getDate.hourStamp ++ "\n"); },
					"SL2", { ~memorylist.add("SL2" + value + Date.getDate.hourStamp ++ "\n"); },
				);
			}
		);

		switch( message_type,
			"R1", { Langley.pushvintagevalve(1, value) },
			"L1", { Langley.pushvintagevalve(2, value) },
			"R2", { Langley.pushvintagevalve(4, value) },
			"L2", { Langley.pushvintagevalve(8, value) },
			"SR1", { Langley.pushvintagevalve(16, value) },
			"SL1", { Langley.pushvintagevalve(32, value) },
			"SR2", { Langley.pushvintagevalve(64, value) },
			"SL2", { Langley.pushvintagevalve(1/2, value) },
			"RightHorizontal", { Langley.currentSynth.set(\rightHorizontal, value) },
			"RightVertical", { Langley.currentSynth.set(\rightVertical, value) },
			"LeftVertical", { Langley.currentSynth.set(\pitchbend, 2 ** (((0.50196081399918 - value)*2)/12)) },
			"LeftHorizontal", { Langley.currentSynth.set(\gain, ((2 ** (value - 1)) - (1/2))); "LH ".post; value.postln; },
			"A", { if ( value == 1.0, { Langley.changeSynth ( "up" ); } ); },
			"X", { if ( value == 1.0, { Langley.changeSynth ( "down" ); } ); },
			"O", { if ( value == 1.0, { Langley.changeSynth ( "right" ); } ); },
			"D", { if ( value == 1.0, { Langley.changeSynth ( "left" ); } ); },
			"LeftPush", { Langley.currentSynth.set(\leftPush, value) },
			"RightPush", { Langley.currentSynth.set(\rightPush, value) },
		);

		}
}
