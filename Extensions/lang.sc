Langley {

	classvar typeHIDint = "valve";
	classvar currentSynth = "null";
	classvar numberOfPressedValves = 0;
	classvar aroundFreq = 13.75;
	classvar learningMode = false;
	classvar tempo = 120;
	//~memorylist = List.newClear;


	*typeHIDint { ^typeHIDint }
	*currentSynth { ^currentSynth }
	*numberOfPressedValves { ^numberOfPressedValves }
	*aroundFreq { ^aroundFreq }
	*learningMode { ^learningMode }
	*tempo { ^tempo }

	*settypeHIDint { |value| typeHIDint = value; }
	*setcurrentSynth { |value| currentSynth = value; "setcursynth was called! With ".post; value.postln; }
	*setnumberOfPressedValves { |value| numberOfPressedValves = value; }
	*setaroundFreq { |value| aroundFreq = value; }
	*setlearningMode { |value| learningMode = value; }
	*settempo { |value| tempo = value;}

	*start {
		HID.findAvailable;
		~myhid  = HID.open( 121, 6 );
		~myhid2 = HID.open( 5215, 453 );
		Langley.makesynths;
		Langley.setHID;
	}
}
