HID.findAvailable;
~myhid = HID.open( 121, 6 );

s.boot;

(
SynthDef(
	name: "Normie",
	ugenGraphFunc: {
		arg frequency, breath=0, breathLevel = 0.5, pitchbend = 1;
		var simpleSin = SinOsc.ar(
			freq: frequency * pitchbend,
			phase: 0,
			mul: breath * breathLevel,
			add: 0
		);
		Out.ar(0, simpleSin);
	}
).add
)

(
SynthDef(
	name: "Didg",
	ugenGraphFunc: {
		arg frequency, breath=0, breathLevel = 0.5, pitchbend = 1, widthlevel=0.5, cutlevel=4, spreadlevel=1;
		var squarewave1 = LFPulse.ar(freq: (frequency * pitchbend)*spreadlevel, iphase: 0, width: widthlevel, mul: breath * breathLevel, add: 0);
		var clarsound1 = LPF.ar(in: [squarewave1, squarewave1], freq: cutlevel*frequency, mul: 1, add: 0);
		var squarewave2 = LFPulse.ar(freq: (frequency * pitchbend)/spreadlevel, iphase: 0, width: widthlevel, mul: breath * breathLevel, add: 0);
		var clarsound2 = LPF.ar(in: [squarewave2, squarewave2], freq: cutlevel*frequency, mul: 1, add: 0);
		var result = Mix.new([clarsound1, clarsound2]);
		Out.ar(0, result);
	}
).add
)

(
~usedSynth = Synth(
	defName: "Didg",
	args: [
		'frequency', 110,
	],
	target: s,
	addAction: 'addToHead'
)
)

(
~usedSynth = Synth(
	defName: "Normie",
	args: [
		'frequency', 440,
	],
	target: s,
	addAction: 'addToHead'
)
)

//Usage definitions

(

~aroundFreq = 110;
~numberOfPressedValves = 0;

HIDdef.element(
	key: \RightHorizontal,
	func: { |value|
		~usedSynth.set(\spreadlevel, 2 ** (((value - 0.50196081399918)*2)/12));
	},
	elID: 3
);

HIDdef.element(
	key: \RightVertical,
	func: { |value|
		~usedSynth.set(\cutlevel, 4 * (value + 1 - 0.50196081399918));
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

)