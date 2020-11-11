+ Langley {
	*floor_trigger_valveHID { | message_type, value |
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
			"R1", { Langley.pushfloortriggervalve(1, value) },
			"L1", { Langley.pushfloortriggervalve(2, value) },
			"R2", { Langley.pushfloortriggervalve(4, value) },
			"L2", { Langley.pushfloortriggervalve(8, value) },
			"SR1", { Langley.pushfloortriggervalve(16, value) },
			"SL1", { Langley.pushfloortriggervalve(32, value) },
			"SR2", { Langley.pushfloortriggervalve(64, value) },
			"SL2", { Langley.pushfloortriggervalve(1/2, value) },
			"RightHorizontal", { Langley.currentSynth.set(\rightHorizontal, value) },
			"RightVertical", { Langley.currentSynth.set(\rightVertical, value) },
			"LeftVertical", { Langley.currentSynth.set(\pitchbend, 2 ** (((0.50196081399918 - value)*2)/12)) },
			"LeftHorizontal", { Langley.currentSynth.set(\gain, ((2 ** (value - 1)) - (1/2))); "LH ".post; value.postln; },
			"A", { if ( value == 1.0, { Langley.changeSynth ( "up" ); } ); },
			"X", { if ( value == 1.0, { Langley.changeSynth ( "down" ); } ); },
			"O", { if ( value == 1.0, { Langley.changeSynth ( "right" ); } ); },
			"D", { if ( value == 1.0, { Langley.changeSynth ( "left" ); } ); },
			"FR", { if( (value==0),
				{
					var gain;
					Langley.setminustwo0(Langley.minusone0);
					Langley.setminustwo1(Langley.minusone1);
					//Langley.setminusone([0, Date.getDate.rawSeconds]);
					Langley.setminusone0(0);
					Langley.setminusone1(Date.getDate.rawSeconds);
					gain = Langley.minustwo0 / ( Langley.minusone1 - Langley.minustwo1 );
					gain = gain/10;
					gain.postln;
					Langley.currentSynth.set(\initgain, gain);
					Langley.currentSynth.set(\gate, 1);
				},
				{
					Langley.currentSynth.set(\gate, 0);
					//Langley.setminustwo(Langley.minusone);
					Langley.setminustwo0(Langley.minusone0);
					Langley.setminustwo1(Langley.minusone1);
					//Langley.setminusone([value, Date.getDate.rawSeconds]);
					Langley.setminusone0(value);
					Langley.setminusone1(Date.getDate.rawSeconds);
				}
			)
			}
		);

		}
}
