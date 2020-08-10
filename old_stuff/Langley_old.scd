
s.boot;

HID.findAvailable;
~myhid = HID.open( 121, 6 );

(
SynthDef(
	name: "Gelid",
	ugenGraphFunc: {
		arg
		    //arguments controlled by GUI
		    roomlevel,
		    mixlevel,
		    damplevel,
		    //arguments controlled by joystick
		    frequency,
		    modindex=(3000 * 0.50196081399918),
		    cutlevel=3000,
		    pitchbend = 1,
		    breath=0,
		    breathLevel = 0.50196081399918,
		    modmodlevel = 1;
		var modfreqratio=10.08;
		var modulator = LFTri.ar(freq: frequency*modfreqratio, iphase: 0, mul: 1, add: 0);
		var carrier = LFTri.ar(freq: (frequency + (modulator * modindex * modmodlevel))*pitchbend, iphase: 0, mul: breath * breathLevel, add: 0);
		var result = LPF.ar(in: carrier, freq: cutlevel, mul: 1, add: 0);
		var result2 = FreeVerb2.ar(result, result, mix: mixlevel, room: roomlevel, damp: damplevel, mul: 1, add: 0);
		Out.ar(0, result2);
	}
).add
);

(

~gelidSynth = Synth(
	defName: "Gelid",
	args: [
		'frequency', 55,
	],
	target: s,
	addAction: 'addToHead'
);


(
SynthDef(
	name: "Didg",
	ugenGraphFunc: {
		arg frequency,
		    breath=0,
		    breathLevel = 0.5,
		    pitchbend = 1,
		    widthlevel=0.5,
		    rightHorizontal = 0.50196081399918,
		    cutlevel = 4;
//		    rightVertical = 0.50196081399918;
//	    var cutlevel = 4 * (rightVertical + 1 - 0.50196081399918);
		var spreadlevel = 2 ** (((rightHorizontal - 0.50196081399918)*2)/12);
		var squarewave1 = LFPulse.ar(freq: (frequency * pitchbend)*spreadlevel, iphase: 0, width: widthlevel, mul: breath * breathLevel, add: 0);
		var clarsound1 = LPF.ar(in: [squarewave1, squarewave1], freq: cutlevel*frequency, mul: 1, add: 0);
		var squarewave2 = LFPulse.ar(freq: (frequency * pitchbend)/spreadlevel, iphase: 0, width: widthlevel, mul: breath * breathLevel, add: 0);
		var clarsound2 = LPF.ar(in: [squarewave2, squarewave2], freq: cutlevel*frequency, mul: 1, add: 0);
		var result = Mix.new([clarsound1, clarsound2]);
		Out.ar(0, result);
	}
).add
);

(
~didgSynth = Synth(
	defName: "Didg",
	args: [
		'frequency', 55,
	],
	target: s,
	addAction: 'addToHead'
)
);

~usedSynth = ~didgSynth;
~usedSynth = ~gelidSynth;
//Usage definitions

(

~aroundFreq = 55;
~numberOfPressedValves = 0;
~roomlevel=0.65,
~mixlevel=0.16,
~damplevel=0.5,

HIDdef.element(
	key: \RightHorizontal,
	func: { |value|
		//~usedSynth.set(\spreadlevel, 2 ** (((value - 0.50196081399918)*2)/12));
		~usedSynth.set(\rightHorizontal, value);
		//value.postln;
	},
	elID: 3
);

HIDdef.element(
	key: \RightVertical,
	func: { |value|
		~usedSynth.set(\cutlevel, 4 * (value + 1 - 0.50196081399918));
		//~usedSynth.set(\rightVertical, value);
		value.postln;
	},
	elID: 4
);

HIDdef.usage(
	key: \LeftVertical,
	func: { |value|
		~usedSynth.set(\pitchbend, 2 ** (((value - 0.50196081399918)*2)/12));
		},
	elUsageName: \X,

);

HIDdef.usage(
	key: \LeftHorizontal,
	func: { |value|
		~usedSynth.set(\breathLevel, value );
		},
	elUsageName: \Joystick,

);

HIDdef.usage(
	key: \Select,
	func: { |value|
		if (
			value == 1,
			{
				~aroundFreq = ~aroundFreq / 2;
				~aroundFreq.postln;
			}
		)
		},
	elUsageName: \b9,

);

HIDdef.usage(
	key: \Start,
	func: { |value|
		if (
			value == 1,
			{
				~aroundFreq = ~aroundFreq * 2;
				~aroundFreq.postln;
			}
		)
		},
	elUsageName: \b10,

);

HIDdef.usage(
	key: \R1,
	func: { |value|
		if (
			value == 1,
			{
				~usedSynth.set(\frequency, ~aroundFreq * (2 ** (1/12)) );
				~aroundFreq = ~aroundFreq * (2 ** (1/12));
				~numberOfPressedValves = ~numberOfPressedValves + 1;
				if (
					~numberOfPressedValves > 0,
					{
						~usedSynth.set(\breath, 1);
					}
				)

				},
			{
				~numberOfPressedValves = ~numberOfPressedValves - 1;
				if (
					~numberOfPressedValves == 0,
					{
						~usedSynth.set(\breath, 0);
					}
				);
				~usedSynth.set(\frequency, ~aroundFreq * (1/(2 ** (1/12))) );
				~aroundFreq = ~aroundFreq * (1/(2 ** (1/12)));
				},
		)
		},
	elUsageName: \b6,

);

HIDdef.usage(
	key: \R2,
	func: { |value|
		if (
			value == 1,
			{
				~usedSynth.set(\frequency, ~aroundFreq * (2 ** (4/12)) );
				~aroundFreq = ~aroundFreq * (2 ** (4/12));
				~numberOfPressedValves = ~numberOfPressedValves + 1;
				if (
					~numberOfPressedValves > 0,
					{
						~usedSynth.set(\breath, 1);
					}
				)

				},
			{
				~numberOfPressedValves = ~numberOfPressedValves - 1;
				if (
					~numberOfPressedValves == 0,
					{
						~usedSynth.set(\breath, 0);
					}
				);
				~usedSynth.set(\frequency, ~aroundFreq * (1/(2 ** (4/12))) );
				~aroundFreq = ~aroundFreq * (1/(2 ** (4/12)));
				},
		)
		},
	elUsageName: \b8,

);

HIDdef.usage(
	key: \L1,
	func: { |value|
		if (
			value == 1,
			{
				~usedSynth.set(\frequency, ~aroundFreq * (2 ** (2/12)) );
				~aroundFreq = ~aroundFreq * (2 ** (2/12));
				~numberOfPressedValves = ~numberOfPressedValves + 1;
				if (
					~numberOfPressedValves > 0,
					{
						~usedSynth.set(\breath, 1);
					}
				)

				},
			{
				~numberOfPressedValves = ~numberOfPressedValves - 1;
				if (
					~numberOfPressedValves == 0,
					{
						~usedSynth.set(\breath, 0);
					}
				);
				~usedSynth.set(\frequency, ~aroundFreq * (1/(2 ** (2/12))) );
				~aroundFreq = ~aroundFreq * (1/(2 ** (2/12)));
				},
		)
		},
	elUsageName: \b5,

);

HIDdef.usage(
	key: \L2,
	func: { |value|
		if (
			value == 1,
			{
				~usedSynth.set(\frequency, ~aroundFreq * (2 ** (8/12)) );
				~aroundFreq = ~aroundFreq * (2 ** (8/12));
				~numberOfPressedValves = ~numberOfPressedValves + 1;
				if (
					~numberOfPressedValves > 0,
					{
						~usedSynth.set(\breath, 1);
					}
				)

				},
			{
				~numberOfPressedValves = ~numberOfPressedValves - 1;
				if (
					~numberOfPressedValves == 0,
					{
						~usedSynth.set(\breath, 0);
					}
				);
				~usedSynth.set(\frequency, ~aroundFreq * (1/(2 ** (8/12))) );
				~aroundFreq = ~aroundFreq * (1/(2 ** (8/12)));
				},
		)
		},
	elUsageName: \b7,

);

);

w=Window("Langley", Rect(100, 200, 1000, 600));

w.view.decorator = FlowLayout(w.view.bounds);

~filterslider = EZSlider(w, 900@50, "LPF", ControlSpec(0, 20000, 'lin', 10, 3000), {|ez|  ~usedSynth.set(\cutlevel, ez.value); });

w.view.decorator.nextLine;

~roomslider = EZSlider(w, 300@50, "roomslider", ControlSpec(0, 1, 'lin', 0.01, 0.65), {|ez|  ~usedSynth.set(\roomlevel, ez.value); });

~mixslider = EZSlider(w, 300@50, "mixslider", ControlSpec(0, 1, 'lin', 0.01, 0.16), {|ez|  ~usedSynth.set(\mixlevel, ez.value); });

~dampslider = EZSlider(w, 300@50, "dampslider", ControlSpec(0, 1, 'lin', 0.01, 0.5), {|ez|  ~usedSynth.set(\damplevel, ez.value); });


w.front;

);

