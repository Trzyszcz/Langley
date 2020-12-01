+ Langley {
	*changeSynth { | message_type |
		var current_synth_type, cur_index;

		switch( message_type,
			"up",    { if( Langley.currentSynthY == 0,
				{ Langley.setcurrentSynthY( Langley.synthsTableSize - 1 ) },
				{ Langley.setcurrentSynthY( Langley.currentSynthY - 1 ) }
			)
			},

			"down",  { if( Langley.currentSynthY == (Langley.synthsTableSize - 1),
				{ Langley.setcurrentSynthY( 0 ) },
				{ Langley.setcurrentSynthY( Langley.currentSynthY + 1 ) }
			)
			},

			"left",  { if( Langley.currentSynthX == 0,
				{ Langley.setcurrentSynthX( Langley.synthsTableSize - 1 ) },
				{ Langley.setcurrentSynthX( Langley.currentSynthX - 1 ) }
			)
			},

			"right", { if( Langley.currentSynthX == (Langley.synthsTableSize - 1),
				{ Langley.setcurrentSynthX( 0 ) },
				{ Langley.setcurrentSynthX( Langley.currentSynthX + 1 ) }
			)
			},
		);

		current_synth_type = ~synthsTable[Langley.currentSynthY][Langley.currentSynthX];
		cur_index = current_synth_type[0];
		Langley.setcurrentSynth( current_synth_type[1][cur_index] );

	}
}