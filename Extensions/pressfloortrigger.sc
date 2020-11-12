+ Langley {
	*pressfloortrigger { | value |
		if( (value==0),
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
			) //end if
	} //end pressfloortrigger
} //end Langley