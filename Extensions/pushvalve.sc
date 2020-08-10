+ Langley {
	*pushvalve { | numberOfSemitones, value |

		"pushvalve was called!".postln;

		if ( value == 1,
		{

			"value is 1!".postln;

			Langley.currentSynth.set( \freq, Langley.aroundFreq * (2 ** (numberOfSemitones/12)) );
			Langley.setaroundFreq ( Langley.aroundFreq * (2 ** (numberOfSemitones/12)) );
			Langley.setnumberOfPressedValves ( Langley.numberOfPressedValves + 1 );

			Langley.numberOfPressedValves.postln;

			if ( Langley.numberOfPressedValves > 0,
				{ Langley.currentSynth.set(\gate, 1); "We are trying to open the gate!".postln; }
			)
		},
		{
			Langley.setnumberOfPressedValves ( Langley.numberOfPressedValves - 1 );
			if ( Langley.numberOfPressedValves == 0,
				{ Langley.currentSynth.set(\gate, 0); }
			);
			Langley.currentSynth.set( \freq, Langley.aroundFreq * (1/(2 ** (numberOfSemitones/12))) );
			Langley.setaroundFreq ( Langley.aroundFreq * (1/(2 ** (numberOfSemitones/12))) );
		},
	)
	}
}		
