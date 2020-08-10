Langley {

	classvar typeHIDint = "valve";
	classvar currentSynth = "null";
	classvar numberOfPressedValves = 0;
	classvar aroundFreq = 13.75;
	

	*typeHIDint { ^typeHIDint }
	*currentSynth { ^currentSynth }
	*numberOfPressedValves { ^numberOfPressedValves }
	*aroundFreq { ^aroundFreq }

	*settypeHIDint { |value| typeHIDint = value; }	
	*setcurrentSynth { |value| currentSynth = value; "setcursynth was called! With ".post; value.postln; }	
	*setnumberOfPressedValves { |value| numberOfPressedValves = value; }	
	*setaroundFreq { |value| aroundFreq = value; }

	*start {
		HID.findAvailable;
		~myhid = HID.open( 121, 6 );
		Langley.makesynths;
		Langley.setHID;
	}
}
