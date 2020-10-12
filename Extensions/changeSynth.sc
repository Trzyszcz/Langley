+ Langley {
	*changeSynth { | message_type |
		switch( message_type,
			"up",    { Langley.setcurrentSynthY( Langley.currentSynthY - 1 ) },
			"down",  { Langley.setcurrentSynthY( Langley.currentSynthY + 1 ) },
			"left",  { Langley.setcurrentSynthX( Langley.currentSynthX - 1 ) },
			"right", { Langley.setcurrentSynthX( Langley.currentSynthX + 1 ) },
		);

		Langley.setcurrentSynth( ~synthsTable[Langley.currentSynthY][Langley.currentSynthX] );

	}
}