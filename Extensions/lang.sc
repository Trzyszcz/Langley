Langley {

	classvar typeHIDint = "valve";
	classvar currentSynth = "null";
	classvar currentSynthX = 0;
	classvar currentSynthY = 0;
	classvar synthsTableSize = 3;
	classvar numberOfPressedValves = 0;
	classvar aroundFreq = 13.75;
	classvar learningMode = false;
	classvar tempo = 120;
	classvar meter = 8;
	//~memorylist = List.newClear;


	*typeHIDint { ^typeHIDint }
	*currentSynth { ^currentSynth }
	*currentSynthX { ^currentSynthX }
	*currentSynthY { ^currentSynthY }
	*synthsTableSize { ^synthsTableSize }
	//*synthsTable { ^synthsTable }
	*numberOfPressedValves { ^numberOfPressedValves }
	*aroundFreq { ^aroundFreq }
	*learningMode { ^learningMode }
	*tempo { ^tempo }
	*meter { ^meter }

	*settypeHIDint { |value| typeHIDint = value; }
	*setcurrentSynth { |value| currentSynth = value; "setcursynth was called! With ".post; value.postln; }
	*setcurrentSynthX { |value| currentSynthX = value; }
	*setcurrentSynthY { |value| currentSynthY = value; }
	*setnumberOfPressedValves { |value| numberOfPressedValves = value; }
	*setaroundFreq { |value| aroundFreq = value; }
	*setlearningMode { |value| learningMode = value; }
	*settempo { |value| tempo = value; }
	*setmeter { |value| meter = value; }

	*start {
		HID.findAvailable;
		~myhid  = HID.open( 121, 6 );
		~myhid2 = HID.open( 5215, 453 );
		Langley.makesynths;
		Langley.setHID;
	}
}
