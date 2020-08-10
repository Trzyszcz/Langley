s.boot;

HID.findAvailable;
~myhid = HID.open( 121, 6 );

//HID.debug = true;
//HID.debug = false;

//8 * 1.25992 ~~ 10.08

~qi16 = { arg value;
	if ( value <= 0.50196081399918,
		{ 11.953 * value },
		{ (209.83 * value * value) + ((-198.7)*value) + 52.87}
	)
};

(
SynthDef(
	name: "PTWE",
	ugenGraphFunc: {
		arg frequency, pitchbend = 1, breath=0, breathLevel = 0.50196081399918, rightVertical = 0.50196081399918,
		//Specific for the instrument
		modfreqratio=10.08,
		//modfreqratio=1.61803,
		modindex= 11.953 * 0.50196081399918,
		//GUI
		cutlevel=3000,
		roomlevel=0.65,
		mixlevel=0.16,
		damplevel=0.5;
		var spreadlevel = 2 ** (((rightVertical - 0.50196081399918)*2)/12);
		var modulator = LFTri.ar(freq: frequency*modfreqratio*pitchbend, iphase: 0, mul: 1, add: 0);
		var carrierA = LFTri.ar(freq: ((frequency + (modulator * modindex * frequency))*pitchbend)*spreadlevel, iphase: 0, mul: breath * breathLevel, add: 0);
		var carrierB = LFTri.ar(freq: ((frequency + (modulator * modindex * frequency))*pitchbend)/spreadlevel, iphase: 0, mul: breath * breathLevel, add: 0);

		var resultA = LPF.ar(in: carrierA, freq: cutlevel, mul: 1, add: 0);
		var result2A = FreeVerb2.ar(resultA, resultA, mix: mixlevel, room: roomlevel, damp: damplevel, mul: 0.5, add: 0);

		var resultB = LPF.ar(in: carrierB, freq: cutlevel, mul: 1, add: 0);
		var result2B = FreeVerb2.ar(resultB, resultB, mix: mixlevel, room: roomlevel, damp: damplevel, mul: 0.5, add: 0);

		var resultfin = Mix.new([result2A, result2B]);

		Out.ar(0, resultfin);
	}
).add
);

(

~usedSynth = Synth(
	defName: "PTWE",
	args: [
		'frequency', 55,
	],
	target: s,
	addAction: 'addToHead'
);

(

~aroundFreq = 55;
~numberOfPressedValves = 0;

HIDdef.element(
	key: \RightHorizontal,
	func: { |value|
		"modindex ".post;
		~qi16.value(value).postln;
		//~usedSynth.set(\modindex, value*700);
		~usedSynth.set(\modindex, ~qi16.value(value));
	},
	elID: 3
);

HIDdef.element(
	key: \RightVertical,
	func: { |value|
		//"modmodlevel".post;
		//((6 * (value * value)) - value).postln;
		//~usedSynth.set(\modmodlevel, (6 * (value * value)) - value);
		~usedSynth.set(\rightVertical, value);
	},
	elID: 4
);

HIDdef.element(
	key: \LeftVertical,
	func: { |value|
		~usedSynth.set(\pitchbend, 2 ** (((value - 0.50196081399918)*2)/12));
		},
	elID: 1

);

HIDdef.element(
	key: \LeftHorizontal,
	func: { |value|
		~usedSynth.set(\breathLevel, value );
		},
	elID: 2

);

~switch = 0;

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
	key: \LPush,
	func: { |value|
		if (
			value == 1,
			{
				if (
					~switch == 0,
					{
						~usedSynth.set(\modfreqratio, 1.61803 );
						~switch = 1;
					},
					{
						~usedSynth.set(\modfreqratio, 10.08 );
						~switch = 0;
					}
				)
			}
		)
		},
	elUsageName: \b11,

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

w=Window("Gelid", Rect(100, 200, 1000, 600));

w.view.decorator = FlowLayout(w.view.bounds);

~filterslider = EZSlider(w, 900@50, "LPF", ControlSpec(0, 20000, 'lin', 10, 3000), {|ez|  ~usedSynth.set(\cutlevel, ez.value); });

w.view.decorator.nextLine;

~roomslider = EZSlider(w, 300@50, "roomslider", ControlSpec(0, 1, 'lin', 0.01, 0.65), {|ez|  ~usedSynth.set(\roomlevel, ez.value); });

~mixslider = EZSlider(w, 300@50, "mixslider", ControlSpec(0, 1, 'lin', 0.01, 0.16), {|ez|  ~usedSynth.set(\mixlevel, ez.value); });

~dampslider = EZSlider(w, 300@50, "dampslider", ControlSpec(0, 1, 'lin', 0.01, 0.5), {|ez|  ~usedSynth.set(\damplevel, ez.value); });


w.front;

);

s.quit