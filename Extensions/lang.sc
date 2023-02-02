Langley {

	classvar typeHIDint = "vintage_valve"; //vintage_valve or floor_trigger_valve
	classvar currentSynth = "null";
	classvar currentSynthX = 0;
	classvar currentSynthY = 0;
	classvar synthsTableSize = 3;
	classvar numberOfPressedValves = 0;
	classvar aroundFreq = 13.75;
	classvar learningMode = false;
	classvar tempo = 120;
	classvar meter = 8;
	classvar vintage = true; //says if floor pedals for triggers are NOT used
	classvar minusone0 = 1;
	classvar minusone1 = 0;
	classvar minustwo0 = 1;
	classvar minustwo1 = 0;
	classvar is_Rpedal_pressed = false; //says if floor pedal is IN PROCESS of pressing
	classvar is_cur_synth_silent = true;
	//~memorylist = List.newClear;

	const <ccen = 0.50196081399918;
	const <rcen = 0.56470590829849;
	const <lcen = 0.43529412150383;

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
	*vintage { ^vintage }
	*minusone0 { ^minusone0 }
	*minustwo0 { ^minustwo0 }
	*minusone1 { ^minusone1 }
	*minustwo1 { ^minustwo1 }
	*is_Rpedal_pressed { ^is_Rpedal_pressed }
	*is_cur_synth_silent { ^is_cur_synth_silent }

	*settypeHIDint { |value| typeHIDint = value; }
	*setcurrentSynth { |value| currentSynth = value; "setcursynth was called! With ".post; value.postln; }
	*setcurrentSynthX { |value| currentSynthX = value; }
	*setcurrentSynthY { |value| currentSynthY = value; }
	*setnumberOfPressedValves { |value| numberOfPressedValves = value; }
	*setaroundFreq { |value| aroundFreq = value; }
	*setlearningMode { |value| learningMode = value; }
	*settempo { |value| tempo = value; }
	*setmeter { |value| meter = value; }
	*setvintage { |value| vintage = value; }
	*setminusone0 { |value| minusone0 = value; }
	*setminustwo0 { |value| minustwo0 = value; }
	*setminusone1 { |value| minusone1 = value; }
	*setminustwo1 { |value| minustwo1 = value; }
	*setis_Rpedal_pressed { |value| is_Rpedal_pressed = value; }
	*setis_cur_synth_silent { |value| is_cur_synth_silent = value; }

	*start {
		HID.findAvailable;
		~myhid   = HID.open( 121, 6, "/dev/hidraw0" );
		~myhid2  = HID.open( 121, 6, "/dev/hidraw1" );
		//~myhid2 = HID.open( 5215, 453 );
		if( (vintage == false), {~myhid  = HID.open( 1699, 1286 ) } );
		Langley.makesynths;
		Langley.setHID;
	}
}
