+ Langley {
	*floor_drums {
		//HID.findAvailable;

		//~myhid  = HID.open( 1699, 1286 );
		~gam = false;
		~gong[1][0].set(\freq, 220);
		~gong[1][1].run(true);
		~gong[1][1].set(\freq, 110);
		~gong[1][2].run(true);
		~gong[1][2].set(\freq, 55);
		/*
		HIDdef.device(
			key: \RF,
			func: { |value|
				//"RF ".post;
				//value.postln;
				//if( (value==0), {~snare.set(\gate, 1) }, {~snare.set(\gate, 0) });
				//if( (~gam), { if( (value==0), {~snare.set(\gate, 1) }, {~snare.set(\gate, 0) }) } );
				if( (~gam),
					{ if( (value==0), {~gong[1][0].set(\gate, 1) }, {~gong[1][0].set(\gate, 0) }) } ,
					{ if( (value==0), {~snare.set(\gate, 1) }, {~snare.set(\gate, 0) }) },
				);
			},
			elUsageName: \Y,
			deviceName: "Saitek R220 ",
		);

		HIDdef.device(
			key: \LF,
			func: { |value|

				if( (~gam),
					{ if( (value==0), {~gong[1][1].set(\gate, 1) }, {~gong[1][1].set(\gate, 0) }) },
					{ if( (value==0), {~bassdrum.set(\gate, 1) }, {~bassdrum.set(\gate, 0) }) },
				);
				/*if( (value==0),
					{
						//~hihat.set(\gain, gain);
						~bassdrum.set(\gate, 1);
							//"dum".postln;
					},
					{
						~bassdrum.set(\gate, 0);
					}
				);*/
			},
			elUsageName: \Slider,
			deviceName: "Saitek R220 ",
		);
		*/
		Routine {
		SynthDef(
			name: "Snare",
			ugenGraphFunc: {
				arg freq = 220,
				    gate = 0,
				    gain = 1,
					rightpush = 0;
				var result = FaustSnare.ar(rightpush, freq, gain, gate);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "GhostSnare",
			ugenGraphFunc: {
				arg freq = 220,
				    gate = 0,
				    gain = 0.6,
					rightpush = 0,
					lowpassfreq = 2500,
					resonq = 12;
				var result = FaustGhostsnare.ar(rightpush, freq, gain, gate, lowpassfreq, resonq);
				Out.ar(0, result);
			}
		).add;

		SynthDef(
			name: "Bassdrum",
			ugenGraphFunc: {
				arg //freq = 80,
				    gate = 0,
				    gain = 1,
		            attack = 0.001,
		            decay = 0.15;
		            //freq_decay = 0.1,
		            //freq_slide = 0.0;
				var result = FaustBassdrum.ar(attack, decay, gain, gate);
				Out.ar(0, result);
			}
		).add;

		2.wait;


		~snare = Synth(
				defName: "Snare",
				//target: s,
				addAction: 'addToHead'
			);

		~ghostsnare = Synth(
				defName: "GhostSnare",
				addAction: 'addToHead'
			);

		~bassdrum = Synth(
				defName: "Bassdrum",
				//target: s,
				addAction: 'addToHead'
			);
		}.play;

		(
~window = Window.new("I catch keystrokes");
~window.view.keyDownAction = { arg view, char, modifiers, unicode, keycode;
	switch( keycode,
		65461, { ~snare.set(\gate, 1) }, //5
		65462, { ~bassdrum.set(\gate, 1); ~hihat.set(\gate, 1); }, //6
		65465, { ~hihat.set(\gate, 1) }, //9
		65460, { ~bassdrum.set(\gate, 1) }, //4
		65464, { ~ghostsnare.set(\gate, 1) }, //8
		65450, { ~gong[1][1].set(\gate, 1) }, //*
		65453, { ~gong[1][0].set(\gate, 1) }, //-
		65455, { ~gong[1][2].set(\gate, 1) }, ///
				); };
~window.view.keyUpAction = { arg view, char, modifiers, unicode, keycode;
	 switch( keycode,
		65461, { ~snare.set(\gate, 0) },
		65462, { ~bassdrum.set(\gate, 0); ~hihat.set(\gate, 0); },
		65465, { ~hihat.set(\gate, 0) },
		65460, { ~bassdrum.set(\gate, 0) }, //4
		65464, { ~ghostsnare.set(\gate, 0) }, //8
		65450, { ~gong[1][1].set(\gate, 0) }, //*
		65453, { ~gong[1][0].set(\gate, 0) }, //-
		65455, { ~gong[1][2].set(\gate, 0) }, ///
				); };
~window.front;
)

		}
}
