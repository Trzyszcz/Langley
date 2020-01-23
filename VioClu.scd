s.boot;

HID.findAvailable;
~myhid = HID.open( 121, 6 );

//HID.debug = true;
//HID.debug = false;

//8 * 1.25992 ~~ 10.08

(
SynthDef(
	name: "VioClu",
	ugenGraphFunc: {
		arg frequency, spread=0.50196081399918, vibrato = 0.50196081399918, cutlevel=800, roomlevel=0.65, mixlevel=0.16, damplevel=0.5, pitchbend = 1, breath=0, breathLevel = 0.50196081399918, amp=0.5,force=1, gate=1,pos=0.07,c1=0.25,c3=31;
		var vibr = ((1/0.50196081399918)*vibrato)**3;
		var spr = (1/0.50196081399918)*spread;
		var banda = Array.fill(12, { arg i;
			var vib = Gendy1.kr(1,1,1,1,0.1, 4,mul:0.003*vibr,add:1);
			var son = DWGBowed.ar(freq: frequency*vib*pitchbend*(2 ** (i*spr/12)), velb: breath*breathLevel, force: force, gate: 1, pos: pos, release: 0.1, c1: c1, c3: c3);
			son = DWGSoundBoard.ar(son);
			son = BPF.ar(son,118,1)+son;
			son = BPF.ar(son,430,1)+son;
			son = BPF.ar(son,490,1)+son;
			son = LPF.ar(son,6000);
			son*0.01
		});
		var arrchannels = SplayAz.ar(numChans: 2, inArray: banda, spread: 1, level: 1, width: 2, center: 0.0, orientation: 0.5, levelComp: true);
		Out.ar(0, arrchannels);

	}
).add
);

(

~usedSynth = Synth(
	defName: "VioClu",
	args: [
		'frequency', 220,
	],
	target: s,
	addAction: 'addToHead'
);

(

~aroundFreq = 220;
~numberOfPressedValves = 0;

HIDdef.element(
	key: \RightHorizontal,
	func: { |value|
		(value*0.1).postln;
		~usedSynth.set(\spread, value);
	},
	elID: 3
);

HIDdef.element(
	key: \RightVertical,
	func: { |value|
		~usedSynth.set(\vibrato, value);
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

w=Window("VioClu", Rect(100, 200, 1000, 600));

w.view.decorator = FlowLayout(w.view.bounds);

~filterslider = EZSlider(w, 900@50, "LPF", ControlSpec(0, 20000, 'lin', 10, 800), {|ez|  ~usedSynth.set(\cutlevel, ez.value); });

w.view.decorator.nextLine;

~roomslider = EZSlider(w, 300@50, "roomslider", ControlSpec(0, 1, 'lin', 0.01, 0.65), {|ez|  ~usedSynth.set(\roomlevel, ez.value); });

~mixslider = EZSlider(w, 300@50, "mixslider", ControlSpec(0, 1, 'lin', 0.01, 0.16), {|ez|  ~usedSynth.set(\mixlevel, ez.value); });

~dampslider = EZSlider(w, 300@50, "dampslider", ControlSpec(0, 1, 'lin', 0.01, 0.5), {|ez|  ~usedSynth.set(\damplevel, ez.value); });


w.front;

);

s.quit