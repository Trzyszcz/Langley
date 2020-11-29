//TO DO time after initial point of starting of the movement must change, stop using half of the values

+ Langley {
	*pressfloortrigger { | value |
		if( (Langley.minusone0 == 0), { Langley.currentSynth.set(\gate, 0) } );
		if( Langley.is_Rpedal_pressed,
			//minustwo0 is value when pressing was started
			//minustwo1 is time when pressing was started
			//minusone0 is last received value
			{
				if ( (value > minusone0), { Langley.setis_Rpedal_pressed(false) } );
				if ( (value == 0),
					{
						var gain = Langley.minustwo0 / ( Date.getDate.rawSeconds - Langley.minustwo1 );
						gain = gain/10;
						gain.postln;
						Langley.currentSynth.set(\initgain, gain);
						Langley.currentSynth.set(\gate, 1);
					}
				);
			}, //end true
			{
				if ( (value < minusone0),
					{
						Langley.setis_Rpedal_pressed(true);
						Langley.setminustwo0(value);
						Langley.setminustwo1(Date.getDate.rawSeconds);
					}
				)
			}  //end false
		); //end if
		Langley.setminusone0(value);
	} //end pressfloortrigger
} //end Langley

/* Older version
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
*/