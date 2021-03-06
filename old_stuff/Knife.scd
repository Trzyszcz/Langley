s.boot;

HID.findAvailable;
~myhid = HID.open( 121, 6 );

//HID.debug = true;
//HID.debug = false;

//8 * 1.25992 ~~ 10.08

(
SynthDef(
	name: "Knife",
	ugenGraphFunc: {
		arg frequency, basewidth=0.50196081399918, cutlevel=3000, roomlevel=0.65, mixlevel=0.16, damplevel=0.5, pitchbend = 1, breath=0, breathLevel = 0.50196081399918, modmodlevel = 1;

		var rq = (0.08*basewidth) + 0.005;
		var banda = Array.fill(6, { arg i;
			var infreq = frequency * (i + 1);
			var result = WhiteNoise.ar(mul: (breath * breathLevel) / (i + 1), add: 0.0);
			result = BPF.ar(in: result, freq: infreq * pitchbend, rq: rq, mul: 1.0, add: 0.0 );
			result = BPF.ar(in: result, freq: infreq * pitchbend, rq: rq, mul: 1.0, add: 0.0 );
			result = result * 2;
			result
		});

		//result2 = FreeVerb2.ar(result, result, mix: mixlevel, room: roomlevel, damp: damplevel, mul: 1, add: 0);
		//Out.ar(0, result2);
		var arrchannels = SplayAz.ar(numChans: 2, inArray: banda, spread: 1, level: 1, width: 2, center: 0.0, orientation: 0.5, levelComp: true);
		Out.ar(0, arrchannels);
	}
).add
);

(

~usedSynth = Synth(
	defName: "Knife",
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
		(value*0.08).postln;
		~usedSynth.set(\basewidth, value);
	},
	elID: 3
);

HIDdef.element(
	key: \RightVertical,
	func: { |value|
		~usedSynth.set(\modmodlevel, (6 * (value * value)) - value);
	},
	elID: 4
);

HIDdef.element(
	key: \LeftVertical,
	func: { |value|
		~usedSynth.set(\pitchbend, 2 ** (((value - 0.50196081399918)*2)/12));
		//value.postln;
		},
	elID: 1

);

HIDdef.element(
	key: \LeftHorizontal,
	func: { |value|
		~usedSynth.set(\breathLevel, value );
		//value.postln;
		},
	//elUsageName: \Joystick,
	elID: 2

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